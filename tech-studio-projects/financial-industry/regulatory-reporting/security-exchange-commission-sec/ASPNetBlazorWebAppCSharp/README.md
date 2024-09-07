Certainly! Here’s an updated `README.md` that includes the information about Visual Studio Community Edition and the ASP.NET Web Project using Blazor:

---

# GAAPCalcPro

## Project Overview

GAAPCalcPro is a financial application designed to perform complex calculations related to Generally Accepted Accounting Principles (GAAP). The application is built using modern technologies to ensure robustness and ease of use. It provides accurate and efficient calculations for financial data, helping users adhere to GAAP standards.

## Technologies Used

- **.NET 8.0**: The application is developed using the .NET 8.0 framework, which provides a robust runtime environment and extensive libraries for building scalable and high-performance applications.
- **ASP.NET Core**: Utilized for creating the web-based interface of the application.
- **Blazor**: Used for building interactive web UIs with C# instead of JavaScript, providing a rich user experience.
- **Visual Studio Community Edition**: The project was created and developed using Visual Studio Community Edition, which offers a comprehensive IDE for building .NET applications.
- **Docker**: Employed for containerization, which ensures consistency across different environments and simplifies deployment and scaling.
- **Windows Nano Server**: The base image used for the Docker container, providing a lightweight and secure environment for running the application.

## Prerequisites

Before running the project, ensure you have the following installed:

- **Docker Desktop**: Required to build and run the Docker container. Download and install it from [Docker's official website](https://www.docker.com/products/docker-desktop).
- **.NET SDK 8.0**: Ensure you have the .NET SDK 8.0 installed if you plan to build or modify the project. You can download it from the [.NET official website](https://dotnet.microsoft.com/download/dotnet/8.0).
- **Visual Studio Community Edition**: Needed if you want to modify the project or view the source code. Download it from [Visual Studio's official website](https://visualstudio.microsoft.com/vs/community/).

## Getting Started

### Building the Docker Image

1. **Open Command Prompt or PowerShell**:
   Navigate to the directory containing the Dockerfile for the project.

2. **Build the Docker Image**:
   Run the following command to build the Docker image:
   ```bash
   docker build -t gaapcalcpro:latest .
   ```

### Running the Docker Container

1. **Start Docker Desktop**:
   Ensure Docker Desktop is running.

2. **Run the Docker Container**:
   Execute the following command to start a new container from the built image:
   ```bash
   docker run -d -p 8080:80 --name gaapcalcpro gaapcalcpro:latest
   ```
   - `-d`: Runs the container in detached mode (background).
   - `-p 8080:80`: Maps port 80 inside the container to port 8080 on your host machine.
   - `--name gaapcalcpro`: Assigns a name to the container.

### Accessing the Application

Once the container is running, you can access the application:

- **Web Interface**: Open a web browser and navigate to [http://localhost:8080](http://localhost:8080).

### Managing the Docker Container

- **View Running Containers**:
  ```bash
  docker ps
  ```

- **Stop the Container**:
  ```bash
  docker stop gaapcalcpro
  ```

- **Remove the Container**:
  ```bash
  docker rm gaapcalcpro
  ```

- **View Container Logs**:
  ```bash
  docker logs gaapcalcpro
  ```

## Contributing

We welcome contributions to GAAPCalcPro! To contribute:

1. **Fork the Repository**: Create your own fork of the repository.
2. **Create a Branch**: Develop your changes on a new branch.
3. **Submit a Pull Request**: Open a pull request with your changes for review.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any questions or support, please contact:

- **Email**: support@gaapcalcpro.com
- **GitHub Issues**: [Submit an issue](https://github.com/your-repo/issues)

---

This `README.md` now includes information about Visual Studio Community Edition and Blazor, providing a comprehensive overview of the development environment and technologies used in the project. Adjust any specific details or contact information as needed.