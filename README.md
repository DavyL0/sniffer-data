# Sniffer Data

## 📖 Introduction
**Sniffer Data** is a Spring Boot application designed to capture, store, and retrieve log data. It exposes RESTful APIs that allow clients to submit log entries and query stored logs. The logs are persisted in a MongoDB collection.

## 📂 Table of Contents
- [Introduction](#-introduction)  
- [Features](#-features)  
- [Requirements](#-requirements)  
- [Installation](#-installation)  
- [Configuration](#-configuration)  
- [Usage](#-usage)  
- [API Endpoints](#-api-endpoints)  
- [Example](#-example)  
- [Docker Support](#-docker-support)  
- [Troubleshooting](#-troubleshooting)  
- [Contributors](#-contributors)  
- [License](#-license)  

## ✨ Features
- RESTful API for log management.  
- Store logs with attributes:
  - `source` (log origin)  
  - `message` (log content)  
  - `createdAt` (timestamp)  
- Backed by MongoDB for persistence.  
- Simple service/repository layer for separation of concerns.  

## 🛠 Requirements
- Java 17+  
- Maven 3.x  
- MongoDB (running locally or via Docker)  

## ⚙️ Installation
Clone the repository:
```bash
git clone https://github.com/DavyL0/sniffer-data.git
cd sniffer-data
```

Build the project:
```bash
mvn clean install
```

Run the application:
```bash
mvn spring-boot:run
```

## 🔧 Configuration
The application expects a running MongoDB instance. Configure your connection in `application.properties` (or `application.yml`):

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/snifferdata
```

You may also configure:
- Database name  
- Authentication credentials (if needed)  

## 🚀 Usage
Once running, the app exposes its REST endpoints at:

```
http://localhost:8080/api
```

## 📡 API Endpoints

### Save a log  
**POST** `/api`  
```json
{
  "source": "sensor-1",
  "message": "Temperature threshold exceeded"
}
```

Response:
```json
{
  "id": "64f1a82b9d1c2b5d...",
  "source": "sensor-1",
  "message": "Temperature threshold exceeded",
  "createdAt": "2025-09-05T12:34:56.789+00:00"
}
```

### Retrieve all logs  
**GET** `/api`  

Response:
```json
[
  {
    "id": "64f1a82b9d1c2b5d...",
    "source": "sensor-1",
    "message": "Temperature threshold exceeded",
    "createdAt": "2025-09-05T12:34:56.789+00:00"
  }
]
```

## 📝 Example
Use `curl` to save a log:
```bash
curl -X POST http://localhost:8080/api   -H "Content-Type: application/json"   -d '{"source":"app-server","message":"CPU usage high"}'
```

Retrieve logs:
```bash
curl http://localhost:8080/api
```

## 🐳 Docker Support

### Run MongoDB with Docker
```bash
docker run -d --name mongo -p 27017:27017 mongo:latest
```

### Build and Run the App with Docker
```bash
docker build -t sniffer-data .
docker run -p 8080:8080 --link mongo:mongo sniffer-data
```

### Docker Compose (Recommended)
Create a `docker-compose.yml`:

```yaml
version: "3.8"
services:
  mongo:
    image: mongo:latest
    container_name: mongo
    ports:
      - "27017:27017"
  sniffer-data:
    build: .
    container_name: sniffer-data
    ports:
      - "8080:8080"
    depends_on:
      - mongo
```

Then run:
```bash
docker-compose up --build
```

## 🛠 Troubleshooting
- **MongoDB connection refused** → Ensure MongoDB is running and accessible.  
- **Port conflicts on 8080** → Change `server.port` in `application.properties`.  

## 👥 Contributors
- [DavyL0](https://github.com/DavyL0)  

## 📄 License
This project is licensed under the MIT License.  
