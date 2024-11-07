# File Transfer System

## Overview

The **File Transfer System** is a client-server application that allows the user to send files and messages between a **client** and a **server** over a network. This project demonstrates how to use RESTful APIs, file handling, and integration between a client-side application and a Spring Boot-based server application. The system includes features such as:
- **Echo Message Service**: The client can send messages to the server, which will respond with an echoed version of the message.
- **File Transfer Service**: The client can send files to the server, which will save them in a specified directory.
- **Logging**: Both the client and server applications log important actions, errors, and warnings.

## Features

- **Echo Command**: Send a message from the client to the server and receive the echoed message back.
- **File Upload**: Transfer files from the client to the server for storage.
- **Logging**: Using Log4J2, the server logs each action, error, and file transfer operation for debugging and traceability.
- **Command-Line Interface**: The client runs in a terminal where users can input commands to send messages and files to the server.

## Technology Stack

- **Backend (Server)**:
  - **Spring Boot**: A Java framework used to create RESTful APIs and manage application components.
  - **Log4J2**: Logging framework for efficient logging of application activities.
  - **Maven**: Build automation tool to manage dependencies and build processes.
  - **JUnit & Mockito**: Testing frameworks for unit and integration tests.
  - **REST APIs**: Used for communication between the client and server.

- **Frontend (Client)**:
  - **Java**: The client is implemented in Java with basic file transfer capabilities.
  - **JCommander**: Command-line parser to process user inputs and execute commands.
  - **JUnit & Mockito**: For testing client-side functionalities.

## Prerequisites

To run this project locally, you will need:

- Java 17 or higher
- Maven
- An IDE like **Eclipse** or **IntelliJ IDEA**
- An active internet connection (for dependencies)

## How to Use

### Running the Server

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/file-transfer-system.git
   cd file-transfer-system/server
   ```

2. **Install Dependencies**:
   Make sure you have the required dependencies installed by running Maven:
   ```bash
   mvn clean install
   ```

3. **Run the Server**:
   Start the Spring Boot server:
   ```bash
   mvn spring-boot:run
   ```
   The server will start on `http://localhost:8080`.

4. **Logging**: Logs are stored in `logs/server.log` by default, and Log4J2 is used for handling different log levels (INFO, WARN, ERROR).

### Running the Client

1. **Clone the repository**:
   Navigate to the client directory:
   ```bash
   git clone https://github.com/your-username/file-transfer-system.git
   cd file-transfer-system/client
   ```

2. **Install Dependencies**:
   If using **Eclipse**, make sure to set up your project and add the necessary dependencies in `pom.xml`.

3. **Run the Client**:
   After building the client project:
   ```bash
   mvn exec:java
   ```

4. **Commands**:
   - **echo [message]**: Sends a message to the server and prints the echoed response.
   - **send [file_path]**: Sends a file to the server for storage.
   - **exit**: Exits the client application.

## API Documentation

### 1. **Echo API**:
   - **URL**: `/api/echo`
   - **Method**: `POST`
   - **Request Body**: A string message to be echoed.
     ```json
     "Hello Server"
     ```
   - **Response**: Returns the echoed message.
     ```json
     "Echo: Hello Server"
     ```

   - **Example**:
     ```bash
     curl -X POST http://localhost:8080/api/echo -H "Content-Type: application/json" -d "\"Hello Server\""
     ```

### 2. **File Transfer API**:
   - **URL**: `/api/file-transfer`
   - **Method**: `POST`
   - **Request Body**: A binary file to be transferred.
   - **Response**: Success message if file was saved, or error if there was an issue.

   - **Example**:
     ```bash
     curl -X POST --data-binary @/path/to/file.txt http://localhost:8080/api/file-transfer
     ```

## Architecture and Flow

### Server-Side Architecture

- **Controller** (`CommandController`):
  - Handles incoming requests (e.g., file upload, echo message).
  - Uses the **FileTransferService** for logic.
  
- **Service** (`FileTransferService`):
  - Contains the logic for saving files and echoing messages.
  - Handles file storage and validation.
  
- **Logging**:
  - **Log4J2** logs all activities and errors in the server-side application.
  - Logs are written to both the console and a rolling log file (`logs/server.log`).

### Client-Side Architecture

- **CommandLineClient**:
  - The main class for handling user inputs and processing commands (echo, send file, etc.).
  
- **FileTransferClient**:
  - Handles HTTP requests to the server using `RestTemplate`.
  - Sends file data or messages to the server and receives the response.

### Use Cases

1. **Echo Command**:
   - The client can send a simple message (e.g., "Hello Server") to the server, and the server responds with "Echo: Hello Server". This demonstrates basic message communication between client and server.

2. **File Transfer**:
   - The client can upload a file to the server. The server saves the file in a predefined location on the server filesystem.
   - Logs any issues encountered during file uploads (e.g., file not found, failure to save).

3. **Logging**:
   - All interactions (e.g., file upload, echo responses) are logged using Log4J2, allowing developers to track user actions, errors, and system activities.

### Error Handling

- **Invalid Commands**: If the client enters an invalid command, the server returns a `400 Bad Request` or `500 Internal Server Error` depending on the case.
  
- **File Not Found**: If the file does not exist on the server or cannot be accessed, the server returns a `500 Internal Server Error` with a descriptive error message.

### Example Flow

1. **Client sends an echo message**: 
   - Input: `echo Hello Server!`
   - Client sends the request to the server.
   - Server processes the message and responds with `Echo: Hello Server!`.

2. **Client uploads a file**:
   - Input: `send /path/to/file.txt`
   - Client sends the file data to the server.
   - Server saves the file and returns a success message or error if the file cannot be saved.

3. **Client exits**:
   - Input: `exit`
   - Client exits the application.

## Tests

The project includes both unit tests and integration tests to ensure correctness and reliability:

- **Unit Tests**:
  - Tests for individual components (e.g., `FileTransferService`).
  - Mocking of external dependencies (e.g., `RestTemplate` for HTTP requests).
  
- **Integration Tests**:
  - Tests the interaction between client and server.
  - Validates end-to-end behavior using mock requests.

## Contributing

We welcome contributions to the project! If you'd like to contribute, please fork the repository and submit a pull request with the following:

1. A clear explanation of the issue you're addressing.
2. Code changes with unit tests (if applicable).
3. Documentation updates, if necessary.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
