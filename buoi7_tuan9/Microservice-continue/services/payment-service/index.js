const express = require('express');
const app = express();
app.use(express.json());

const payments = new Map();

// Get all payments
app.get('/api/payments', (req, res) => {
    const allPayments = Array.from(payments.values());
    res.json(allPayments);
});

// Get specific payment
app.get('/api/payments/:id', (req, res) => {
    const payment = payments.get(req.params.id);
    if (!payment) {
        return res.status(404).json({ message: 'Payment not found' });
    }
    res.json(payment);
});

// Create new payment
app.post('/api/payments', (req, res) => {
    const { orderId, amount } = req.body;
    
    if (!orderId || !amount) {
        return res.status(400).json({ message: 'OrderId and amount are required' });
    }

    const payment = {
        id: Date.now().toString(),
        orderId,
        amount,
        status: 'COMPLETED',
        createdAt: new Date().toISOString()
    };
    
    payments.set(payment.id, payment);
    res.status(201).json(payment);
});

// Update payment status
app.put('/api/payments/:id/status', (req, res) => {
    const { status } = req.body;
    const payment = payments.get(req.params.id);
    
    if (!payment) {
        return res.status(404).json({ message: 'Payment not found' });
    }
    
    payment.status = status;
    payment.updatedAt = new Date().toISOString();
    res.json(payment);
});

const PORT = 3001;
app.listen(PORT, () => {
    console.log(`Payment Service running on port ${PORT}`);
});