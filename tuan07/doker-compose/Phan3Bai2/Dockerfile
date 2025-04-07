# Chọn Node.js version 18 làm base image
FROM node:18

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép package.json và package-lock.json
COPY package*.json ./

# Cài đặt các dependencies
RUN npm install

# Sao chép toàn bộ mã nguồn vào container
COPY . .

# Cung cấp thông tin cổng mà ứng dụng sẽ chạy trên container
EXPOSE 3000

# Lệnh để chạy ứng dụng Node.js
CMD ["node", "app.js"]
