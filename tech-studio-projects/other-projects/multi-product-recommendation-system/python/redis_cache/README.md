# Redis Caching Service

This service sets up Redis as a caching layer for the microservices architecture. Redis is a powerful in-memory data store used for caching, and it helps to improve the performance and scalability of your application by providing fast data retrieval.

## Overview

The **Redis Caching Service** is responsible for providing caching functionalities across various microservices. Redis stores frequently accessed data in memory, making it accessible at a much faster speed than traditional databases. It helps reduce the load on backend systems, accelerates data access, and improves overall application performance.

### Key Features
- **In-memory data store**: Redis caches data in memory for extremely fast access.
- **Eviction policies**: Redis supports several eviction policies like LRU (Least Recently Used), LFU (Least Frequently Used), etc., for managing memory.
- **Pub/Sub messaging**: Redis supports message brokering through its publish/subscribe mechanism.
- **Persistence**: Redis offers optional persistence features to save data to disk (though primarily an in-memory store).
  
This Redis cache service can be integrated with other microservices (such as the `product_catalog_service`, `recommendation_engine_service`, etc.) to store and retrieve data quickly.

## Setup Instructions

Follow these steps to set up the Redis cache service.

### Prerequisites
- Docker installed on your local machine or in the cloud.
  
### Build the Redis Docker Image

```bash
docker build -t redis-cache-service .
