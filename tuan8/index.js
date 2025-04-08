const express = require("express");
const app = express();

app.use(express.json());

app.get("/", (req, res) => {
  res.send("Product Service is running!");
});

app.listen(3001, () => {
  console.log("Product Service is running on port 3001");
});
