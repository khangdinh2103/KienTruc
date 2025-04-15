const express = require('express');
const app = express();
app.use(express.json());

const inventory = new Map();

// Initialize some sample inventory
inventory.set('1', { productId: '1', quantity: 100 });
inventory.set('2', { productId: '2', quantity: 150 });

// Get all products
app.get('/api/inventory', (req, res) => {
    const products = Array.from(inventory.values());
    res.json(products);
});

// Get specific product
app.get('/api/inventory/:productId', (req, res) => {
    const product = inventory.get(req.params.productId);
    if (!product) {
        return res.status(404).json({ message: 'Product not found' });
    }
    res.json(product);
});

// Create new product
app.post('/api/inventory', (req, res) => {
    const { productId, quantity } = req.body;
    
    if (inventory.has(productId)) {
        return res.status(400).json({ message: 'Product already exists' });
    }
    
    if (!productId || quantity === undefined) {
        return res.status(400).json({ message: 'ProductId and quantity are required' });
    }

    const newProduct = { productId, quantity };
    inventory.set(productId, newProduct);
    res.status(201).json(newProduct);
});

// Update product quantity
app.post('/api/inventory/update', (req, res) => {
    const { productId, quantity } = req.body;
    const product = inventory.get(productId);
    if (!product) {
        return res.status(404).json({ message: 'Product not found' });
    }
    product.quantity -= quantity;
    res.json(product);
});

// Delete product
app.delete('/api/inventory/:productId', (req, res) => {
    const productId = req.params.productId;
    if (!inventory.has(productId)) {
        return res.status(404).json({ message: 'Product not found' });
    }
    
    inventory.delete(productId);
    res.json({ message: 'Product deleted successfully' });
});

const PORT = 3002;
app.listen(PORT, () => {
    console.log(`Inventory Service running on port ${PORT}`);
});