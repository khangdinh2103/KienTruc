const express = require('express');
const app = express();
app.use(express.json());

const shipments = new Map();

// Get all shipments
app.get('/api/shipments', (req, res) => {
    const allShipments = Array.from(shipments.values());
    res.json(allShipments);
});

// Get specific shipment
app.get('/api/shipments/:id', (req, res) => {
    const shipment = shipments.get(req.params.id);
    if (!shipment) {
        return res.status(404).json({ message: 'Shipment not found' });
    }
    res.json(shipment);
});

// Create new shipment
app.post('/api/shipments', (req, res) => {
    const { orderId, address } = req.body;
    
    if (!orderId || !address) {
        return res.status(400).json({ message: 'OrderId and address are required' });
    }

    const shipment = {
        id: Date.now().toString(),
        orderId,
        address,
        status: 'PROCESSING',
        createdAt: new Date().toISOString(),
        trackingNumber: 'TN' + Math.random().toString(36).substr(2, 9).toUpperCase()
    };
    
    shipments.set(shipment.id, shipment);
    res.status(201).json(shipment);
});

// Update shipment status
app.put('/api/shipments/:id/status', (req, res) => {
    const { status } = req.body;
    const shipment = shipments.get(req.params.id);
    
    if (!shipment) {
        return res.status(404).json({ message: 'Shipment not found' });
    }
    
    shipment.status = status;
    shipment.updatedAt = new Date().toISOString();
    res.json(shipment);
});

// Delete shipment
app.delete('/api/shipments/:id', (req, res) => {
    if (!shipments.has(req.params.id)) {
        return res.status(404).json({ message: 'Shipment not found' });
    }
    
    shipments.delete(req.params.id);
    res.json({ message: 'Shipment deleted successfully' });
});

const PORT = 3003;
app.listen(PORT, () => {
    console.log(`Shipping Service running on port ${PORT}`);
});