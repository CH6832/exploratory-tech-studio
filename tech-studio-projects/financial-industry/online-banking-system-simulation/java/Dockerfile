# Use an official Maven image to build the application
FROM maven:3.8.4-openjdk-11 AS builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean package

# Use an official OpenJDK image to run the application
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from the builder stage
COPY --from=builder /app/target/banking-0.0.1-SNAPSHOT.jar /app/banking.jar

# Expose the port
EXPOSE 8443

# Run the application
ENTRYPOINT ["java", "-jar", "/app/banking.jar"]
