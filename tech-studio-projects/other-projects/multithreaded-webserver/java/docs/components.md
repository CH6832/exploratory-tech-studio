#### **Components**

This section describes the main components of the system.

1. **Client:**
   - The client sends requests to the server and processes the response.
   - It includes:
     - **CommandLineClient:** Handles interaction with the user.
     - **FileTransferClient:** Sends file data and echo messages.
   
2. **Server:**
   - The server handles requests from the client and responds accordingly.
   - It includes:
     - **ServerApplication:** Main Spring Boot application.
     - **CommandController:** REST controller handling requests.
     - **FileTransferService:** Logic for handling file transfers and echo messages.
   
3. **Logging:**
   - Log4J is used to log server activity, including request handling, errors, and server events.

4. **API:**
   - The server exposes a REST API for communication:
     - `POST /api/echo` – Receives a message and echoes it back.
     - `POST /api/file-transfer` – Receives a file and stores it on the server.
