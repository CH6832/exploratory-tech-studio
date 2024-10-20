# Use an official OpenJDK image for Java development
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the source code and necessary files
COPY ./src /app/src
COPY ./scripts /app/scripts
COPY ./pom.xml /app/pom.xml # Assuming you are using Maven; adjust as needed
COPY ./docker-compose.yml /app/docker-compose.yml
COPY ./README.md /app/README.md

# Install dependencies (if needed)
# Here we are assuming you're using Maven to manage dependencies
# If you are using Gradle, adjust the commands accordingly
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean

# Build the project
RUN mvn clean package

# Command to run the Java application
# Change 'com.example.Main' to your actual main class path
CMD ["java", "-cp", "target/your-jar-file.jar", "com.example.Main"]
