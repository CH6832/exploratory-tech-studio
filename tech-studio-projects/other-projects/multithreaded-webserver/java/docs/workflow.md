#### **Workflow**

This document explains the general workflow of the client-server communication.

1. **Client Request:**
   - The client initiates communication by either sending an echo message or a file.
   
2. **Server Receives Request:**
   - The server listens for requests on predefined API endpoints.
   - It processes the request depending on its type (echo or file upload).
   
3. **Message Echo (Echo Command):**
   - If the client sends an echo command, the server responds with the same message.
   
4. **File Upload:**
   - If the client sends a file, the server receives the file and saves it to the file system.
   - The server responds with a success message or an error if something goes wrong.

5. **Client Response:**
   - The client receives the server's response (echoed message or file upload success).
