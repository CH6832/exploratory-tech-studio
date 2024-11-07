# Use the official Python image as a base image
FROM python:3.11-slim

# Set the working directory to /app
WORKDIR /app

# Install dependencies
COPY requirements.txt .

# Install the required Python dependencies
RUN pip install --no-cache-dir -r requirements.txt

# Expose the port the app will run on
EXPOSE 8000

# Set the default command to run the app
CMD ["uvicorn", "app:app", "--host", "0.0.0.0", "--port", "8000"]
