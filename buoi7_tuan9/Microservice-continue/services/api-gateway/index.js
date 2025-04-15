const express = require('express');
const axios = require('axios');
const { default: axiosRetry } = require('axios-retry');  // Alternative import
const Bottleneck = require('bottleneck');
const CircuitBreaker = require('opossum');

const app = express();
app.use(express.json());

// === Rate Limiter (Client Side) using Bottleneck ===
const limiter = new Bottleneck({
    maxConcurrent: 2, // Limit concurrent requests
    minTime: 500 // 500ms between requests
});
// === Circuit Breaker ===
// === Circuit Breaker ===
function wrapWithBreaker(fn, name = "Service") {
    const breaker = new CircuitBreaker(async (...args) => {
        try {
            const result = await fn(...args);
            return result;
        } catch (error) {
            console.error(`❌ [${name}] Service failed: ${error.message}`);
            throw error;
        }
    }, {
        timeout: 3000, // Timeout for each request
        errorThresholdPercentage: 50, // Error threshold before circuit breaker trips
        resetTimeout: 10000, // Time to wait before resetting the breaker after opening
    });

    breaker.fallback(() => {
        console.warn(`⚠️ [${name}] Fallback triggered`);
        return { data: { message: `${name} Service temporarily unavailable (fallback)` } };
    });

    breaker.on('open', () => console.warn(`🔴 [${name}] Circuit opened`));
    breaker.on('close', () => console.log(`🟢 [${name}] Circuit closed`));
    breaker.on('halfOpen', () => console.log(`🟡 [${name}] Circuit half-open`));

    return async (...args) => {
        return breaker.fire(...args); // No retry logic here
    };
}
// === Wrapped functions with Circuit Breaker ===
const callPaymentService = wrapWithBreaker(async (data) => {
    return await axios.post('http://localhost:3004/api/payments', data);
}, "Payment");

const callShippingService = wrapWithBreaker(async (data) => {
    return await axios.post('http://localhost:3003/api/shipments', data);
}, "Shipping");

// === New Route for Circuit Breaker Test ===
app.post('/test-circuit-breaker', async (req, res) => {
    const paymentData = {
        orderId: 'breaker-test',
        amount: 1000
    };

    const shippingData = {
        orderId: 'breaker-test',
        address: '123 Main St.'
    };

    try {
        console.log('test-circuit-breaker');
        console.log('🧾 Calling Payment Service...');
        const paymentResponse = await callPaymentService(paymentData);

        console.log('🚚 Calling Shipping Service...');
        const shippingResponse = await callShippingService(shippingData); 
        res.json({
            payment: paymentResponse.data,
            shipment: shippingResponse.data
        });
    } catch (error) {
        console.error('❌ Final error after breaker handling:', error.message);
        res.status(500).json({ error: error.message || 'Something went wrong' });
    }
});


// === Retry Configuration ===
// === Cấu hình retry ===
let retryCounter = 0;
axiosRetry(axios, {
    retries: 3,
    retryDelay: (retryCount) => {
        retryCounter = retryCount;
        console.log(`🔁 Retry attempt ${retryCount}/3`);
        return retryCount * 1000;
    },
    retryCondition: (error) => {
        return error.code === 'ECONNREFUSED' || error.code === 'ENOTFOUND' || error.response?.status >= 500;
    }
});


// === Route test riêng rẽ từng lần ===
app.post('/test-retry', async (req, res) => {
    const paymentData = {
        orderId: 'retry-test',
        amount: 1000
    };

    const shippingData = {
        orderId: 'retry-test',
        address: '123 Retry St.'
    };

    try {
        console.log('test-retry');
        console.log('🧾 [1] Calling Payment Service...');
        const paymentResponse = await axios.post('http://localhost:3001/api/payments', paymentData);
        console.log('✅ Payment service success');

        console.log('🚚 [2] Calling Shipping Service...');
        const shippingResponse = await axios.post('http://localhost:3004/api/shipments', shippingData);
        console.log('✅ Shipping service success');

        res.json({
            payment: paymentResponse.data,
            shipment: shippingResponse.data
        });
    } catch (error) {
        console.error('❌ Final error after retries: ', error.message);
        res.status(500).json({ error: error.message });
    }
});


// === Circuit Breaker Wrapper ===
function wrapWithBreakerAndLimiter(fn, name = "Service") {
    const breaker = new CircuitBreaker(fn, {
        timeout: 3000,
        errorThresholdPercentage: 50,
        resetTimeout: 10000,
    });

    breaker.fallback(() => {
        return { data: { message: `${name} Service temporarily unavailable (fallback)` } };
    });

    breaker.on('open', () => console.log(`${name} Circuit opened`));
    breaker.on('close', () => console.log(`${name} Circuit closed`));
    breaker.on('halfOpen', () => console.log(`${name} Circuit half-open`));

    return async (...args) => {
        return limiter.schedule(() => breaker.fire(...args));
    };
}



const callInventoryService = wrapWithBreakerAndLimiter(async (data) => {
    return await axios.post('http://localhost:3002/api/inventory/update', data);
}, "Inventory");

// const callShippingService = wrapWithBreakerAndLimiter(async (data) => {
//     return await axios.post('http://shipping-service:3003/api/shipments', data);
// }, "Shipping");

// === Route ===
app.post('/api/orders', async (req, res) => {
    try {
        const payment = await callPaymentService({
            orderId: req.body.orderId,
            amount: req.body.amount
        });

        await callInventoryService({
            productId: req.body.productId,
            quantity: req.body.quantity
        });


        res.json({
            payment: payment.data,
        });
    } catch (error) {
        res.status(500).json({ error: error.message || 'Something went wrong' });
    }
});
// app.post('/test-retry', async (req, res) => {
//     const paymentData = {
//         orderId: 'retry-test',
//         amount: 1000
//     };

//     const shippingData = {
//         orderId: 'retry-test',
//         address: '123 Retry St.'
//     };

//     try {
//         const [paymentResponse, shippingResponse] = await Promise.all([
//             axios.post('http://payment-service:3001/api/payments', paymentData),
//             axios.post('http://shipping-service:3003/api/shipments', shippingData)
//         ]);

//         res.json({
//             payment: paymentResponse.data,
//             shipment: shippingResponse.data
//         });
//     } catch (error) {
//         console.error('Error during retry test:', error.message);
//         res.status(500).json({ error: error.message });
//     }
// });

// === Cấu hình Rate Limiter chi tiết ===
const rateLimiter = new Bottleneck({
    maxConcurrent: 2,     // Số lượng request đồng thời tối đa
    minTime: 1000,        // Thời gian tối thiểu giữa các request (1 giây)
    highWater: 5,         // Số request tối đa trong hàng đợi
    strategy: Bottleneck.strategy.BLOCK // Chặn request khi đạt giới hạn
});

// === Cấu hình Time Limiter ===
const TIME_LIMIT = 2000; // Giới hạn thời gian 2 giây cho mỗi request

// === Hàm wrapper cho Time Limiter ===
const timeoutPromise = (promise, time) => {
    return Promise.race([
        promise,
        new Promise((_, reject) => 
            setTimeout(() => reject(new Error('Request timeout')), time)
        )
    ]);
};

// === Route test Rate Limiter ===
app.post('/test-rate-limiter', async (req, res) => {

    try {
        // Thực hiện request thông qua rate limiter
        const result = await rateLimiter.schedule(async () => {
            console.log('test-rate-limiter');
            console.log('🚀 Đang xử lý request...' + new Date().toISOString());
            
            // Giả lập xử lý trong 500ms
            await new Promise(resolve => setTimeout(resolve, 500));
            
            return {
                timestamp: new Date().toISOString(),
                message: 'Request được xử lý thành công'
            };
        });

        res.json(result);
    } catch (error) {
        console.error('❌ Lỗi Rate Limiter:', error.message);
        res.status(429).json({ 
            error: 'Quá nhiều request, vui lòng thử lại sau',
            details: error.message 
        });
    }
});

// === Route test Time Limiter ===
app.post('/test-time-limiter', async (req, res) => {
    try {
        // Giả lập một service call với time limit
        const serviceCall = new Promise((resolve) => {
            // Giả lập xử lý mất 3 giây (sẽ vượt quá time limit 2 giây)
            setTimeout(() => {
                resolve({ data: 'Dữ liệu từ service' });
            }, 3000);
        });

        // Áp dụng time limit
        console.log('test-time-limiter');
        const result = await timeoutPromise(serviceCall, TIME_LIMIT);
        res.json(result);
    } catch (error) {
        console.error('❌ Lỗi Time Limiter:', error.message);
        res.status(408).json({ 
            error: 'Request vượt quá thời gian cho phép',
            details: error.message 
        });
    }
});

// === Route test kết hợp cả Rate và Time Limiter ===
app.post('/test-combined-limiters', async (req, res) => {
    try {
        const result = await rateLimiter.schedule(async () => {
            console.log('🎯 Bắt đầu xử lý request kết hợp...');
            
            // Gọi service với time limit
            return await timeoutPromise(
                axios.post('http://localhost:3001/api/payments', {
                    orderId: 'test-combined',
                    amount: 1000
                }),
                TIME_LIMIT
            );
        });

        res.json({
            success: true,
            data: result.data,
            message: 'Request thành công qua cả rate limit và time limit'
        });
    } catch (error) {
        console.error('❌ Lỗi khi xử lý request kết hợp:', error.message);
        res.status(500).json({
            error: 'Lỗi khi xử lý request',
            type: error.message.includes('timeout') ? 'TIME_LIMIT' : 'RATE_LIMIT',
            details: error.message
        });
    }
});

const PORT = 3000;
app.listen(PORT, () => {
    console.log(`API Gateway running on port ${PORT}`);
});