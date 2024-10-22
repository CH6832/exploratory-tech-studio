@echo off
rem ----------------------------------------------------------------------------
rem Maven Wrapper Script
rem This script is used to run the Maven Wrapper, which allows users to
rem execute Maven commands without needing to install Maven globally.
rem It ensures that the correct version of Maven is used for the project.
rem ----------------------------------------------------------------------------

setlocal
rem Initialize a local environment for variables.

rem Wrapper script version
rem This variable holds the expected version of the Maven Wrapper.
set WRAPPER_VERSION=3.8.4

rem Define paths
rem 'DIR' gets the directory of the script being executed. The '%~dp0' 
rem expands to the drive and path of the batch file, ensuring that the 
rem script works regardless of the current working directory.
set DIR=%~dp0

rem Define the path to the Maven Wrapper JAR file. This JAR is responsible 
rem for running Maven.
set MAVEN_WRAPPER_JAR=%DIR%.mvn\wrapper\maven-wrapper.jar

rem Define the path to the properties file that contains configuration for 
rem the Maven Wrapper.
set MAVEN_WRAPPER_PROPERTIES=%DIR%.mvn\wrapper\maven-wrapper.properties

rem Check if the wrapper jar exists
rem This conditional checks for the existence of the Maven Wrapper JAR file.
rem If the file is not found, an error message is displayed, instructing the 
rem user to run the 'mvn wrapper:wrapper' command to generate the necessary files.
if not exist "%MAVEN_WRAPPER_JAR%" (
    echo Maven Wrapper JAR not found. Please ensure you have run 'mvn wrapper:wrapper' in your project.
    exit /b 1
)

rem Execute the Maven Wrapper
rem This command runs the Java Virtual Machine (JVM) and executes the Maven 
rem Wrapper Main class, passing along any arguments that were provided to 
rem this script. The 'java -cp' command sets the classpath to include the 
rem Maven Wrapper JAR.
java -cp "%MAVEN_WRAPPER_JAR%" org.apache.maven.wrapper.MavenWrapperMain %*
