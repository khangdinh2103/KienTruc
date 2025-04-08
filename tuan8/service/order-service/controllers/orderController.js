const Order = require("../models/Order");

exports.createOrder = async (req, res) => {
  const order = new Order(req.body);
  const saved = await order.save();
  res.status(201).json(saved);
};

exports.getOrders = async (req, res) => {
  const orders = await Order.find();
  res.json(orders);
};
