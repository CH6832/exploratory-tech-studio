# Java Web Application with JSP and JSTL

This is a Java web application that utilizes JSP (JavaServer Pages) and JSTL (JavaServer Pages Standard Tag Library) for dynamic web content. This README provides instructions on setting up the project, including dependency management and resolving common issues related to servlet configurations.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Resolving Common Issues](#resolving-common-issues)
- [License](#license)

## Prerequisites

Before you begin, ensure you have the following installed on your system:

- **Java Development Kit (JDK)**: Version 8 or higher.
- **Apache Tomcat**: Version 8 or higher for running the web application.
- **Maven**: (Optional) For dependency management.
- **IDE**: An IDE like Eclipse or IntelliJ IDEA for Java development.

## Project Structure

Here’s the recommended structure for the project:

```
your-webapp/
│
├── WEB-INF/
│   ├── lib/
│   │   ├── javax.servlet-api-3.1.0.jar
│   │   ├── jstl-1.2.jar
│   │   └── standard.jar
│   └── web.xml
├── index.jsp
└── yourOtherJSPFiles.jsp
```

## Setup Instructions

### 1. Add Dependencies

If you are using **Maven**, add the following dependencies to your `pom.xml` file:

```xml
<dependencies>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>3.1.0</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
</dependencies>
```

### 2. Manual JAR Management (if not using Maven)

1. Download the required JAR files:
   - `javax.servlet-api-3.1.0.jar`
   - `jstl-1.2.jar`
   - `standard.jar` (if needed)

2. Place these JAR files in the `WEB-INF/lib/` directory of your project.

### 3. Configure Your IDE

If you are using **Eclipse**:

1. Right-click on your project and select **Properties**.
2. Go to **Java Build Path** > **Libraries**.
3. Click on **Add External JARs...** and add the downloaded JAR files.
4. Add server runtime libraries:
   - Right-click on your project > **Properties** > **Java Build Path** > **Libraries** > **Add Library...** > **Server Runtime** > Select your server (e.g., Apache Tomcat).

### 4. Configure Project Facets

Ensure that your project is set to use the correct Dynamic Web Module version:

1. Right-click on your project > **Properties** > **Project Facets**.
2. Set **Dynamic Web Module** to **3.1**.

### 5. Refresh and Clean the Project

After configuring, refresh and clean your project:

1. Right-click on your project and select **Refresh**.
2. Go to **Project** > **Clean...** and select your project.

## Resolving Common Issues

### Error: `javax.servlet.http.HttpServlet` Not Found

If you encounter the error related to the `HttpServlet` superclass not found:

1. Ensure you have the `javax.servlet-api-3.1.0.jar` in your `WEB-INF/lib/`.
2. Verify that your Java Build Path includes the servlet API.
3. Check that you have added server runtime libraries for your web server (e.g., Tomcat).

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
