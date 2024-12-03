# Repository Overview

## Overview

The repository for the **e-filing-us-gaap** project is organized into **separate microservices** for backend (Spring Boot) and frontend (Micro-Frontends) development, adhering to modern software architecture principles. Each microservice is independent, with its own codebase and configuration, enabling modularity and scalability.

### Repository Structure

```bash
e-filing-us-gaap/
├── backend/
│   ├── user-service/
│   │   ├── src/
│   │   │   ├── main/
│   │   │   │   ├── java/
│   │   │   │   │   └── com/
│   │   │   │   │       └── efilingusgaap/
│   │   │   │   │           ├── UserServiceApplication.java
│   │   │   │   │           ├── controller/
│   │   │   │   │           │   └── UserController.java
│   │   │   │   │           ├── service/
│   │   │   │   │           │   └── UserService.java
│   │   │   │   │           └── repository/
│   │   │   │   │               └── UserRepository.java
│   │   │   │   ├── resources/
│   │   │   │   │   └── application.yml
│   │   │   │   └── test/
│   │   │   │       └── java/
│   │   │   │           └── com/
│   │   │   │               └── efilingusgaap/
│   │   │   │                   └── UserServiceTests.java
│   │   └── pom.xml
│   ├── financial-reporting-service/
│   │   ├── src/
│   │   │   └── main/
│   │   │       ├── java/
│   │   │       │   └── com/
│   │   │       │       └── efilingusgaap/
│   │   │       │           ├── FinancialReportingServiceApplication.java
│   │   │       │           ├── controller/
│   │   │       │           │   └── FinancialReportController.java
│   │   │       │           ├── service/
│   │   │       │           │   └── FinancialReportService.java
│   │   │       │           └── repository/
│   │   │       │               └── FinancialReportRepository.java
│   │   │       ├── resources/
│   │   │       │   └── application.yml
│   │   │       └── test/
│   │   │           └── java/
│   │   │               └── com/
│   │   │                   └── efilingusgaap/
│   │   │                       └── FinancialReportServiceTests.java
│   │   └── pom.xml
│   ├── filing-service/
│   │   ├── src/
│   │   │   └── main/
│   │   │       ├── java/
│   │   │       │   └── com/
│   │   │       │       └── efilingusgaap/
│   │   │       │           ├── FilingServiceApplication.java
│   │   │       │           ├── controller/
│   │   │       │           │   └── FilingController.java
│   │   │       │           ├── service/
│   │   │       │           │   └── FilingService.java
│   │   │       │           └── repository/
│   │   │       │               └── FilingRepository.java
│   │   │       ├── resources/
│   │   │       │   └── application.yml
│   │   │       └── test/
│   │   │           └── java/
│   │   │               └── com/
│   │   │                   └── efilingusgaap/
│   │   │                       └── FilingServiceTests.java
│   │   └── pom.xml
│   └── pom.xml (Parent Pom for all backend services)
├── frontend/
│   ├── container-app/                 # Main container frontend app
|   │   ├── src/
|   │   │   ├── app/
|   │   │   │   ├── components/
|   │   │   │   │   ├── header/           # Header component
|   │   │   │   │   ├── sidebar/          # Sidebar component
|   │   │   │   │   ├── footer/           # Footer component
|   │   │   │   │   └── dashboard/        # Main dashboard container
|   │   │   │   ├── services/             # Shared services (e.g., user authentication)
|   │   │   │   ├── models/               # Data models (e.g., user, reports)
|   │   │   │   ├── app.module.ts         # Root module for container app
|   │   │   │   ├── app.component.ts      # Main component for layout
|   │   │   │   └── app-routing.module.ts # Routing configuration
|   │   │   ├── assets/                  # Static assets (images, icons, etc.)
|   │   │   ├── environments/            # Environment configurations (dev, prod)
|   │   │   ├── styles/                  # Global CSS/SCSS files
|   │   │   └── main.ts                  # Entry point for Angular app
|   │   ├── angular.json                 # Angular configuration
|   │   ├── package.json                 # Dependencies and scripts for container app
|   │   ├── tsconfig.json                # TypeScript configuration
|   │   └── README.md                    # Container app documentation
│   ├── user-profile-microfrontend/
│   │   ├── src/
│   │   │   ├── app/
│   │   │   │   ├── components/
│   │   │   │   │   └── user-profile/
│   │   │   │   ├── services/
│   │   │   │   └── app.module.ts
│   │   │   ├── assets/
│   │   │   └── environments/
│   │   │       └── environment.ts
│   │   ├── angular.json
│   │   ├── package.json
│   │   └── tsconfig.json
│   ├── financial-reporting-microfrontend/
│   │   ├── src/
│   │   │   ├── components/
│   │   │   │   └── financial-report/
│   │   │   ├── services/
│   │   │   └── app.module.ts
│   │   ├── angular.json
│   │   ├── package.json
│   │   └── tsconfig.json
│   ├── filing-microfrontend/
│   │   ├── src/
│   │   │   ├── components/
│   │   │   │   └── filing-form/
│   │   │   ├── services/
│   │   │   └── app.module.ts
│   │   ├── angular.json
│   │   ├── package.json
│   │   └── tsconfig.json
│   ├── compliance-microfrontend/
│   │   ├── src/
│   │   │   ├── components/
│   │   │   │   └── compliance-dashboard/
│   │   │   ├── services/
│   │   │   └── app.module.ts
│   │   ├── angular.json
│   │   ├── package.json
│   │   └── tsconfig.json
│   └── package.json (Frontend root package)
├── docker/
│   ├── backend/
│   │   ├── Dockerfile (for backend microservices)
│   ├── frontend/
│   │   ├── Dockerfile (for frontend microservices)
│   └── docker-compose.yml (for local deployment)
├── scripts/
│   ├── build.sh (for building backend services)
│   ├── deploy.sh (for deploying to a cloud environment)
│   └── test.sh (for running tests across the project)
└── README.md
```

---

## Detailed Description of Folders and Files

### **Backend Microservices (Spring Boot)**

The **backend** folder contains multiple **Spring Boot** microservices, each responsible for a specific domain of the project (user management, financial reporting, filing). Each microservice has its own directory, following a typical **Spring Boot project structure**.

#### Key Backend Microservices:

- **user-service/**: Manages user authentication, authorization, and profile data.
  - `UserServiceApplication.java`: Main application class for the microservice.
  - `UserController.java`: API controller for user-related actions (login, registration).
  - `UserService.java`: Service layer that handles user-related business logic.
  - `UserRepository.java`: JPA repository for user data persistence.
  
- **financial-reporting-service/**: Handles the creation and management of financial reports based on US GAAP standards.
  - `FinancialReportController.java`: Handles HTTP requests for generating reports.
  - `FinancialReportService.java`: Business logic for generating and calculating financial reports.
  - `FinancialReportRepository.java`: Repository interface for handling data persistence.

- **filing-service/**: Manages the creation of SEC filing forms (10-K, 10-Q) and the validation of data before submission.
  - `FilingController.java`: API controller for filing-related actions.
  - `FilingService.java`: Service layer for generating SEC filings.
  - `FilingRepository.java`: Handles storage of SEC filing data.

#### Parent **pom.xml**:
- This file is located in the root of the `backend/` folder and manages the dependencies and versions for all backend microservices.

---

### **Frontend Microservices (Micro-Frontends)**

The **frontend** folder contains separate directories for each micro-frontend, each built with a different **frontend framework** (e.g., Angular, React, Vue.js, and Svelte).

#### Key Frontend Microservices:

- **user-profile-microfrontend/**: Built with **Angular**, handles user profile management (e.g., login, registration, profile editing).
  - `app.module.ts`: The root module for the Angular application.


  - `user-profile/`: Contains components related to user profile, such as the registration form, login form, and profile page.

- **financial-reporting-microfrontend/**: Built with **React**, handles the display and interactions related to financial reports.
  - `app.module.ts`: Main entry file for Angular apps; for React, this is typically `index.js`.
  - `financial-report/`: React components for visualizing financial reports and graphs.

- **filing-microfrontend/**: Built with **Vue.js**, allows users to interact with forms related to SEC filings (e.g., 10-K, 10-Q).
  - `app.module.ts`: Main entry file for the Vue.js micro-frontend.
  - `filing-form/`: Vue components for displaying and submitting SEC filing forms.

- **compliance-microfrontend/**: Built with **Svelte**, provides a dashboard for compliance tracking and audit logs.
  - `compliance-dashboard/`: Svelte components for showing audit logs, compliance status, etc.

#### Key Shared Files:
- `angular.json`, `package.json`, `tsconfig.json`: These files are specific to each frontend microservice's technology stack (Angular, React, etc.) and are used to manage dependencies, build configurations, and TypeScript settings.

---

### **Docker Configuration**

The **docker/** folder contains Dockerfiles for both the backend and frontend services and a `docker-compose.yml` file to manage local deployment of the entire project stack.

- `backend/Dockerfile`: A Dockerfile to containerize the backend microservices.
- `frontend/Dockerfile`: A Dockerfile for building and containerizing the frontend microservices.
- `docker-compose.yml`: Defines how all backend and frontend microservices work together in a local development environment (using Docker containers).

---

### **Scripts**

The **scripts/** folder contains useful shell scripts to automate common tasks such as building, testing, and deploying the project.

- `build.sh`: Automates building the entire backend and frontend codebases.
- `deploy.sh`: Automates deployment of the system to a cloud environment or Kubernetes cluster.
- `test.sh`: Runs all unit and integration tests across the project.

---

### **README.md**

The **README.md** file provides an overview of the entire project, setup instructions, and details on how to contribute to or run the project.

---
