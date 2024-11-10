/*

- http://localhost:8080/logs
{"level": "info", "message": "This is an informational log message."}'

Explanation

- Log Levels: The `LoggingController` accepts the following log levels:
   - `"info"`: Logs a message as `INFO` level.
   - `"warn"`: Logs a message as `WARN` level.
   - `"error"`: Logs a message as `ERROR` level.
   - Any other value will be treated as `DEBUG`.

- Logging Behavior: Based on the log level, the `LoggingController` will route the log message to the appropriate SLF4J logger method:
   - `info()`: Logs at the INFO level.
   - `warn()`: Logs at the WARN level.
   - `error()`: Logs at the ERROR level.
   - `debug()`: Logs at the DEBUG level if the level is unspecified or unrecognized.

Example Usage Scenarios

- Log an Info Message:
  - Request: `{"level": "info", "message": "System startup successful"}`
  - This will log: `INFO: System startup successful`.

- Log a Warning Message:
  - Request: `{"level": "warn", "message": "Disk space is low"}`
  - This will log: `WARN: Disk space is low`.

- Log an Error Message:
  - Request: `{"level": "error", "message": "Unexpected error occurred in payment processing"}`
  - This will log: `ERROR: Unexpected error occurred in payment processing`.

- Log a Debug Message (if level is not specified):
  - Request: `{"message": "Debugging user login flow"}`
  - This will log: `DEBUG: Debugging user login flow`.

*/

package com.cms.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logs")
public class LoggingController {

    private static final Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @PostMapping
    public ResponseEntity<Void> logMessage(@RequestBody LogEntry logEntry) {
        switch (logEntry.getLevel().toLowerCase()) {
            case "info":
                logger.info(logEntry.getMessage());
                break;
            case "warn":
                logger.warn(logEntry.getMessage());
                break;
            case "error":
                logger.error(logEntry.getMessage());
                break;
            default:
                logger.debug(logEntry.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
