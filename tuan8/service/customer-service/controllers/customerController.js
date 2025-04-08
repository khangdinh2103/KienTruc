const Customer = require("../models/Customer");

exports.createCustomer = async (req, res) => {
  const customer = new Customer(req.body);
  const saved = await customer.save();
  res.status(201).json(saved);
};

exports.getCustomers = async (req, res) => {
  const customers = await Customer.find();
  res.json(customers);
};
