const Product = require("../models/Product");

exports.createProduct = async (req, res) => {
  const newProduct = new Product(req.body);
  const saved = await newProduct.save();
  res.status(201).json(saved);
};

exports.getAllProducts = async (req, res) => {
  const products = await Product.find();
  res.json(products);
};