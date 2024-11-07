# Microservices Architecture for Product Recommendations

## Overview
This project is a microservices-based architecture for a product recommendation system. It includes the following services:

- **API Gateway**: FastAPI-based service that handles routing and rate limiting.
- **Product Catalog**: FastAPI-based service that manages product data.
- **Recommendation Engine**: Django-based service that generates product recommendations.
- **User Profile**: Flask-based service that manages user profiles.
- **Redis Cache**: Redis caching layer for performance optimization.
- **MongoDB**: MongoDB service for storing product information.

## Prerequisites

- Docker
- Docker Compose

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/product-recommendation-microservices.git
   cd product-recommendation-microservices
   ```

2. Build the Docker images:
   ```bash
   ./scripts/build_services.sh
   ```

3. Start the services:
   ```bash
   ./scripts/start_services.sh
   ```

4. Access the API Gateway at `http://localhost:8000`.

## Service Details

- **API Gateway**: Exposes a unified API to interact with all services.
- **Product Catalog**: Manages and serves product data.
- **Recommendation Engine**: Provides personalized product recommendations.
- **User Profile**: Stores and manages user data.
- **Redis Cache**: Caches frequently requested data to improve performance.

## Running Tests

To run unit tests for a service, navigate to the service directory and run the tests using `pytest`:

```bash
cd api_gateway
pytest tests/
```

## License
This project is licensed under the MIT License.
```

#### Explanation:
- **Overview**: Describes the project and its services.
- **Prerequisites**: Lists dependencies (Docker and Docker Compose).
- **Setup Instructions**: Step-by-step guide to setting up and running the services.
- **Service Details**: Brief descriptions of each microservice in the architecture.
- **Running Tests**: Instructions for running unit tests.
- **License**: The project uses the MIT License.

---

### **4. `LICENSE`**

This is the MIT license for the project.

```text

```

#### Explanation:
- **MIT License**: A permissive open-source license that allows users to freely use, modify, and distribute the software with no warranty.
