# Project Name

## Overview

This project is a full-stack web application with a Vue.js frontend and a Python Flask backend. 

## Setup

### Backend (Flask)

1. Navigate to the `backend` directory:
    ```bash
    cd backend
    ```

2. Create a virtual environment and activate it:
    ```bash
    python -m venv venv
    source venv/bin/activate  # On Windows use `venv\Scripts\activate`
    ```

3. Install dependencies:
    ```bash
    pip install -r requirements.txt
    ```

4. Run the Flask server:
    ```bash
    python app.py
    ```

   The backend will be available at `http://localhost:5000`.

### Frontend (Vue.js)

1. Navigate to the `frontend` directory:
    ```bash
    cd frontend
    ```

2. Install dependencies:
    ```bash
    npm install
    ```

3. Run the Vue.js development server:
    ```bash
    npm run serve
    ```

   The frontend will be available at `http://localhost:8080`.

## Usage

1. Open the Vue.js application in your browser.
2. Enter data into the form and submit to interact with the Flask backend.

## Contributing

Feel free to open issues or submit pull requests. Please follow the coding conventions and ensure that all tests pass.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
