const express = require("express");
const mongoose = require("mongoose");
const customerRoutes = require("./routers/customerRoutes");

const app = express();
app.use(express.json());

mongoose.connect("mongodb://localhost:27017/customer-service")
  .then(() => console.log("Customer DB connected"));

app.use("/customers", customerRoutes);

app.listen(3003, () => console.log("Customer service on port 3003"));
