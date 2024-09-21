package com.fintech.algotrading.logging;

//Interface for a logger.
public interface ILogger {
 void debug(String module, String message);
 void debug(String module, Exception exception);

 void information(String module, String message);
 void information(String module, Exception exception);

 void warning(String module, String message);
 void warning(String module, Exception exception);

 void error(String module, String message);
 void error(String module, Exception exception);
 
void logInformation(String message);
void logWarning(String message);
void logError(String message);
}

