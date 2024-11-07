#### **Architecture and Design**

This section outlines the overall architecture and design decisions for the project.

**System Architecture:**

1. **Client-Server Model:**
   - The system follows a client-server architecture, where the client sends messages or files to the server, and the server responds accordingly.
   - The client can either use the command line or a GUI to interact with the server.

2. **Multi-threading:**
   - The server uses multi-threading to handle concurrent requests efficiently.
   - Each client request is handled by a separate thread.

3. **REST API:**
   - The server exposes REST APIs for the client to interact with, including:
     - `POST /api/echo` for sending echo messages.
     - `POST /api/file-transfer` for sending files.

4. **File Storage:**
   - Uploaded files are saved to a predefined directory `/uploads` on the server.
   - Files are named based on the original file name or some unique identifier.

**Design Patterns Used:**
1. **Singleton Pattern:**
   - Used for managing the instance of the file storage service.
   
2. **Factory Pattern:**
   - Used for creating instances of different types of requests (e.g., Echo and File Transfer).

**Key Technologies Used:**
- **Spring Boot:** For building the server application.
- **Spring MVC:** For handling HTTP requests.
- **Log4J:** For logging events and errors.
- **JUnit:** For testing the application.
- **Mockito:** For mocking dependencies in unit tests.
