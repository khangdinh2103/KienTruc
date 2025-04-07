# Sử dụng Node.js image chính thức làm base image
FROM node:18

# Thiết lập thư mục làm việc trong container
WORKDIR /usr/src/app

# Copy các file cần thiết vào container
COPY package*.json ./
RUN npm install

# Copy toàn bộ mã nguồn vào container
COPY . .

# Mở cổng 3000 cho ứng dụng
EXPOSE 3000

# Chạy ứng dụng khi container khởi động
CMD ["node", "app.js"]
