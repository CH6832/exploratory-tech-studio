#### **API Documentation**

This file provides detailed documentation for the API endpoints.

**API Endpoints:**

1. **Echo Command:**
   - **Endpoint:** `POST /api/echo`
   - **Request Body:**
     ```json
     "message": "Hello, Server!"
     ```
   - **Response:**
     ```json
     "Echo: Hello, Server!"
     ```
   - **Description:** This endpoint receives a message from the client and echoes it back.

2. **File Transfer:**
   - **Endpoint:** `POST /api/file-transfer`
   - **Request Body:**
     - The file should be sent as raw binary data (Content-Type: `application/octet-stream`).
   - **Response:**
     - **Success:** `200 OK` - The file was uploaded successfully.
     - **Error:** `500 Internal Server Error` - If the file could not be saved.
   - **Description:** This endpoint receives a file from the client and saves it to the server.
  