const express = require("express");
const mongoose = require("mongoose");
const productRoutes = require("./routers/productRoutes");

const app = express();
app.use(express.json());

// ❗ Kết nối tới database dùng chung: sales-management
mongoose.connect("mongodb://localhost:27017/sales-management", {
  useNewUrlParser: true,
  useUnifiedTopology: true,
})
.then(() => console.log("Connected to sales-management DB"))
.catch((err) => console.error("DB connection error:", err));

app.use("/products", productRoutes);

app.listen(3001, () => console.log("Product service running on port 3001"));
