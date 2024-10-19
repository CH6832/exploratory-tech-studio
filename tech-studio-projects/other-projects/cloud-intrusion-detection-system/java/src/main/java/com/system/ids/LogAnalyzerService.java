package com.system.ids;

import com.system.ids.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service responsible for log analysis.
 */
@Service
public class LogAnalyzerService {
    private static final Logger logger = LoggerFactory.getLogger(LogAnalyzerService.class);

    public void analyzeLogs(String logData) {
        try {
            logger.info("Analyzing logs...");

            // Dummy log analysis logic
            if (logData.contains("ERROR")) {
                logger.warn("Suspicious log entry detected: {}", logData);
                // Logic to generate an alert could be added here
            }
        } catch (Exception e) {
            throw new CustomException("Error while analyzing logs", e);
        }
    }
}
