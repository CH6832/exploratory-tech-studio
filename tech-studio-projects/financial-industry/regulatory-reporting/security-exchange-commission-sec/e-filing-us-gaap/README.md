# US GAAP E-FILING

### A Modern, Scalable, and Modular Microservices-based Web Application for Financial Filings under U.S. GAAP

**"e-filing-us-gaap"** is a cutting-edge web application designed to handle and streamline **financial filings** in compliance with **U.S. GAAP (Generally Accepted Accounting Principles)** and related **SEC (Securities and Exchange Commission)** guidelines. The project is built using a **Microservices Architecture** for the backend and **Micro-Frontend Architecture** for the frontend, providing modularity, scalability, and flexibility.

---

## Table of Contents

- [US GAAP E-FILING](#us-gaap-e-filing)
    - [A Modern, Scalable, and Modular Microservices-based Web Application for Financial Filings under U.S. GAAP](#a-modern-scalable-and-modular-microservices-based-web-application-for-financial-filings-under-us-gaap)
  - [Table of Contents](#table-of-contents)
  - [Project Overview](#project-overview)
  - [Key Features](#key-features)
  - [Architecture](#architecture)
    - [Backend:](#backend)
    - [Frontend:](#frontend)
  - [Frontend](#frontend-1)
    - [Main Container App](#main-container-app)
    - [Micro-Frontend Integration](#micro-frontend-integration)
  - [Backend](#backend-1)
  - [Setup and Installation](#setup-and-installation)
    - [Frontend Setup](#frontend-setup)
    - [Backend Setup](#backend-setup)
    - [Running with Docker](#running-with-docker)
  - [Impressions](#impressions)
  - [Technologies Used](#technologies-used)
  - [Testing](#testing)
  - [Contributing](#contributing)
  - [License](#license)

---

## Project Overview

This project aims to create a modern web application to help organizations comply with **U.S. GAAP** requirements by allowing users to interact with the **SEC filing system** through various micro-frontends and backend services.

The application is modular and scalable, built using the latest web technologies, and follows best practices in **Microservices** and **Micro-Frontends** architecture. It leverages independent components for each core functionality and ensures a smooth, seamless user experience. The goal is to improve efficiency in preparing and submitting financial reports, maintaining compliance with regulatory requirements, and delivering real-time insights into the status of filings.

---

## Key Features

- **Microservices Architecture** for backend services, allowing each service to be independently deployed and scaled.
- **Micro-Frontends** for the frontend, enabling independent development of UI components.
- **User Profile Management**: User authentication, registration, and profile management.
- **Financial Reporting**: View, generate, and manage financial reports based on U.S. GAAP standards.
- **SEC Filings**: Seamless interaction with SEC filing forms (e.g., 10-K, 10-Q).
- **Compliance Dashboard**: Track compliance, audit logs, and real-time filing status.
- **Modular Design**: Independent microservices and frontend components that can be developed, updated, and deployed independently.

---

## Architecture

### Backend:

- **Spring Boot Microservices**: The backend is built with **Spring Boot** to handle each service independently.
  - **User Service**: Manages user profiles, authentication, and user-specific data.
  - **Financial Reporting Service**: Aggregates and generates financial reports in compliance with U.S. GAAP.
  - **Filing Service**: Handles the creation, validation, and submission of SEC filings (e.g., 10-K, 10-Q forms).
  - **Compliance Service**: Monitors and logs compliance with regulatory standards, tracking all filings.

### Frontend:

- **Main Container App (Angular)**: Acts as the central orchestrator for integrating all micro-frontends, providing routing, and shared UI components.
- **Micro-Frontends**:
  - **User Profile (Angular)**: Handles user registration, login, and profile management.
  - **Financial Reporting (Angular)**: Displays financial reports and generates charts for users.
  - **Filing (Angular)**: Manages SEC filing forms for submission to the SEC.
  - **Compliance Dashboard (Angular)**: Displays compliance status, audit logs, and real-time filing status.

---

## Frontend

### Main Container App

The **Main Container App** is built using **Angular** and integrates various micro-frontends using **single-spa**. It handles routing and serves as the entry point for the application. Each micro-frontend (e.g., financial reports, filings, compliance) is loaded dynamically based on the route.

**Key Features of the Container App**:
- **Header**: Displays the app name, user profile, and notifications.
- **Sidebar**: Provides links to different sections like user profile, financial reports, filings, and compliance.
- **Main Dashboard**: A landing page with key statistics, recent activity, and shortcuts to other sections.
- **Footer**: Contains copyright information, terms of service, and privacy policy links.

### Micro-Frontend Integration

Each micro-frontend is developed independently using different frontend technologies (Angular, React, Vue.js, Svelte) but integrated seamlessly within the main container app.

**Key Features**:
- **single-spa**: Manages the integration of micro-frontends.
- **Dynamic Loading**: Each micro-frontend is loaded dynamically based on user interaction, minimizing load times and improving performance.
- **Cross-Frontend Communication**: Micro-frontends communicate through a shared state or event-based system, ensuring smooth integration.

---

## Backend

The backend is built using **Spring Boot** and follows a **Microservices Architecture**. Each backend service is independent and can be developed, tested, and deployed separately.

**Key Services**:
- **User Service**: Handles user management (login, registration, and profile updates).
- **Financial Reporting Service**: Generates and provides U.S. GAAP-compliant financial reports.
- **Filing Service**: Handles the creation, validation, and submission of SEC filing forms.
- **Compliance Service**: Monitors regulatory compliance and provides audit logs for all financial filings.

---

## Setup and Installation

### Frontend Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-repository/e-filing-us-gaap.git
   cd e-filing-us-gaap/frontend/container-app
   ```

2. **Install dependencies**:
   ```bash
   npm install
   ```

3. **Run the development server**:
   ```bash
   ng serve
   ```

   The container app will be accessible at `http://localhost:4200`.

### Backend Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-repository/e-filing-us-gaap.git
   cd e-filing-us-gaap/backend
   ```

2. **Install dependencies**:
   - Make sure **Maven** is installed. Run:
   ```bash
   mvn clean install
   ```

3. **Run the Spring Boot application**:
   ```bash
   mvn spring-boot:run
   ```

   The backend will be running at `http://localhost:8080`.

### Running with Docker

To run the entire project using **Docker**, you can use the provided **docker-compose.yml** file to set up all backend and frontend services in containers.

1. **Build the Docker images**:
   ```bash
   docker-compose build
   ```

2. **Run the containers**:
   ```bash
   docker-compose up
   ```

   All services (frontend and backend) will be running locally in Docker containers.

---

## Impressions

![Home](./docs/user/images/Homejpeg)

![Home](./docs/user/images/Homejpeg)

![Home](./docs/user/images/Homejpeg)

![Home](./docs/user/images/Homejpeg)

![Home](./docs/user/images/Homejpeg)

![Home](./docs/user/images/Homejpeg)

---

## Technologies Used

- **Backend**:
  - Spring Boot
  - Spring Security
  - Hibernate
  - MySQL (or other relational databases)
  - Docker
  - Maven

- **Frontend**:
  - Angular (Main Container App)
  - React (Financial Reporting Micro-Frontend)
  - Vue.js (Filing Micro-Frontend)
  - Svelte (Compliance Dashboard Micro-Frontend)
  - Single-SPA (Micro-Frontend Integration)
  - Angular Material, Bootstrap (for UI/UX)

- **Others**:
  - Docker
  - Git
  - Nginx (for production server)
  - Jenkins (for CI/CD)

---

## Testing

Unit and integration tests are provided for both the backend and frontend components.

- **Backend**: Use **JUnit** for unit tests and **Mockito** for mocking dependencies.
- **Frontend**: Use **Jasmine** and **Karma** for Angular, **Jest** for React, **Vue Test Utils** for Vue, and **Svelte Testing Library** for Svelte.

Run tests with:
```bash
mvn test   # For backend tests
ng test    # For frontend (Angular) tests
```

---

## Contributing

We welcome contributions to improve the project. Here's how you can get involved:

1. **Fork the repository** and create your branch.
2. **Make changes** and commit them.
3. **Open a pull request** for review.

Please make sure to follow the **code style** and write unit tests for new features or bug fixes.

---

## License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

---
