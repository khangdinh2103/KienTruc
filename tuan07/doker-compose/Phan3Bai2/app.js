const express = require('express');
const mongoose = require('mongoose');
const app = express();
const port = 3000;

// Cấu hình kết nối MongoDB
const mongoURI = process.env.MONGO_URI || 'mongodb://root:rootpassword@mongodb:27017/mydb';

mongoose.connect(mongoURI, { useNewUrlParser: true, useUnifiedTopology: true })
  .then(() => console.log('Connected to MongoDB'))
  .catch((err) => console.error('Failed to connect to MongoDB', err));

// Middleware để xử lý JSON
app.use(express.json());

// Định nghĩa một schema đơn giản cho dữ liệu
const ItemSchema = new mongoose.Schema({
  name: String,
  price: Number,
});

const Item = mongoose.model('Item', ItemSchema);

// API để thêm một item vào MongoDB
app.post('/items', async (req, res) => {
  const { name, price } = req.body;
  const newItem = new Item({ name, price });
  try {
    await newItem.save();
    res.status(201).send(newItem);
  } catch (err) {
    res.status(500).send('Failed to save item');
  }
});

// Endpoint healthcheck
app.get('/health', (req, res) => {
  res.status(200).send('OK');
});

// Lắng nghe cổng 3000
app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});