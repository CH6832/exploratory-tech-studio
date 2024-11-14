# Base Image
FROM openjdk:11-jre-slim

# Working Directory
WORKDIR /app

# Copy and Build
COPY . /app
RUN ./mvnw clean package

# Expose port and run
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/core-system.jar"]
