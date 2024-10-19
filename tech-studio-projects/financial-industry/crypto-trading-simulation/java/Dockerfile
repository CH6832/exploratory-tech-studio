# Use an official GCC image for C++ development
FROM gcc:latest

# Set the working directory in the container
WORKDIR /app

# Copy the source code and necessary files
COPY ./src /app/src
COPY ./scripts /app/scripts
COPY ./Makefile /app/Makefile
COPY ./docker-compose.yml /app/docker-compose.yml
COPY ./README.md /app/README.md

# Install dependencies
RUN apt-get update && \
    apt-get install -y cmake g++ && \
    apt-get clean

# Build the project
RUN make

# Command to run the market maker
CMD ["./build/market_maker"]
