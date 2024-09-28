# Use the official Python image as a base
FROM python:3.9

# Set the working directory in the container
WORKDIR /app

# Copy the requirements file into the container
COPY requirements.txt .

# Install the necessary packages
RUN pip install --no-cache-dir -r requirements.txt

# Copy the entire project into the container
COPY . .

# Command to run the FastAPI application using Uvicorn
CMD ["uvicorn", "microservices/api/main:app", "--host", "0.0.0.0", "--port", "8000"]
