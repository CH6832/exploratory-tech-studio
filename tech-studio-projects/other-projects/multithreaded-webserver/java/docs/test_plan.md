#### **Test Plan**

This section describes the testing strategy for the project.

**Unit Testing:**
1. **FileTransferService Test:**
   - Test the `saveFile` method to ensure it correctly saves a file or throws an error when appropriate.
   
2. **CommandController Test:**
   - Test the REST API endpoints to ensure they return correct responses for both valid and invalid requests.

**Integration Testing:**
1. **Echo Command Test:**
   - Test if the server correctly handles echo messages and returns the same message back to the client.

2. **File Transfer Test:**
   - Test if the server can correctly handle file uploads and store them in the correct directory.

**Error Handling:**
- Ensure proper error messages are returned when:
  - A file does not exist.
  - An invalid command is issued.
  - File upload fails due to server issues.

**Performance Testing:**
- Use load testing tools to simulate multiple client connections and ensure the server can handle concurrent requests without significant performance degradation.
