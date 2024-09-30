# Use an official Python runtime as a parent image
FROM python:3.9-slim

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy the current directory contents into the container at /usr/src/app
COPY . .

# Install any needed packages specified in requirements.txt
RUN pip install --no-cache-dir -r requirements.txt

# Expose port (if needed, for local communication)
EXPOSE 8080

# Define environment variable
ENV PYTHONUNBUFFERED 1

# Run the monitoring service
CMD ["python", "monitoring_service.py"]
