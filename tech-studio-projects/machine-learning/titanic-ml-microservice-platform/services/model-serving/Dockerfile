# Use the official Python image as the base image
FROM python:3.9-slim

# Set the working directory in the container
WORKDIR /app

# Copy the requirements file to the working directory
COPY requirements.txt .

# Install the required Python packages
RUN pip install --no-cache-dir -r requirements.txt

# Copy the application code and configuration files to the container
COPY app.py .
COPY config.yaml .
COPY trained_models/ ./trained_models/

# Expose the port on which the app will run
EXPOSE 5000

# Define the command to run the application
CMD ["python", "app.py"]
