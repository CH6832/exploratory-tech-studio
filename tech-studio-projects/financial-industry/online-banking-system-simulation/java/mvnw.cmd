@echo off
rem ----------------------------------------------------------------------------
rem Maven Wrapper
rem ----------------------------------------------------------------------------

setlocal

rem Wrapper script version
set WRAPPER_VERSION=3.8.4

rem Define paths
set DIR=%~dp0
set MAVEN_WRAPPER_JAR=%DIR%.mvn\wrapper\maven-wrapper.jar
set MAVEN_WRAPPER_PROPERTIES=%DIR%.mvn\wrapper\maven-wrapper.properties

rem Check if the wrapper jar exists
if not exist "%MAVEN_WRAPPER_JAR%" (
    echo Maven Wrapper JAR not found. Please ensure you have run 'mvn wrapper:wrapper' in your project.
    exit /b 1
)

rem Execute the Maven Wrapper
java -cp "%MAVEN_WRAPPER_JAR%" org.apache.maven.wrapper.MavenWrapperMain %*
