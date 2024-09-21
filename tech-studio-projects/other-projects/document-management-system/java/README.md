# Document Management System (DMS)

This Java project is a simple Document Management System (DMS) designed to store, retrieve, and manage documents. It includes basic features such as user authentication, document storage, and retrieval.

## Features

- **User Authentication**: Secure user login using a simple authentication mechanism.
- **Document Storage**: Add and store documents with titles and content.
- **Document Retrieval**: Search for documents by title.
- **Console-Based Interface**: A basic text-based interface for interacting with the system.

## Prerequisites

- **Java 11 or later**: Make sure you have Java installed on your system.
- **Maven**: Used for building and managing project dependencies.

## Usage

0. **Navigate to the project directory**:
   ```bash
   cd DocumentManagementSystem
   ```
1. **Build the project using Maven**:
   ```bash
   mvn clean install
   ```
2. **Run the application**:
   ```bash
   mvn exec:java -Dexec.mainClass="com.dms.Main"
   ```

## Running Tests

To run the unit tests included in the project:

```bash
mvn test
```

This will execute the tests for the `AuthenticationService`, `DocumentManager`, and `UserManager` classes.

## Project Components

- **Main.java**: The entry point of the application.
- **User.java**: Represents a user in the system.
- **Document.java**: Represents a document stored in the system.
- **DocumentManager.java**: Handles document storage and retrieval.
- **UserManager.java**: Manages user-related operations.
- **AuthenticationService.java**: Provides authentication services.
