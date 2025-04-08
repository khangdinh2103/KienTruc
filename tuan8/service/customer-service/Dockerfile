# Base image
FROM node:18

# App directory
WORKDIR /app

# Copy dependencies
COPY package*.json ./
RUN npm install

# Copy all source code
COPY . .

# Expose port
EXPOSE 3003

# Run app
CMD ["node", "index.js"]
