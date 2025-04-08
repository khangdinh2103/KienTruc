const express = require("express");
const mongoose = require("mongoose");
const orderRoutes = require("./routers/orderRoutes");

const app = express();
app.use(express.json());

mongoose.connect("mongodb://localhost:27017/order-service")
  .then(() => console.log("Order DB connected"));

app.use("/orders", orderRoutes);

app.listen(3002, () => console.log("Order service on port 3002"));
