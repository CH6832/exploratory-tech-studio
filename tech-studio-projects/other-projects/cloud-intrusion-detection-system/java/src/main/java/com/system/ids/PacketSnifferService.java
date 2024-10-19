package com.system.ids;

import com.system.ids.CustomException;
import com.system.ids.Alert;
import com.system.ids.PacketUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service responsible for packet sniffing.
 */
@Service
public class PacketSnifferService {
    private static final Logger logger = LoggerFactory.getLogger(PacketSnifferService.class);
    
    public void sniffPackets() {
        try {
            // Simulate packet sniffing logic
            logger.info("Starting packet sniffing...");

            // Using utility methods for packet analysis
            Alert alert = PacketUtils.analyzePacket();
            if (alert != null) {
                logger.warn("Suspicious activity detected: {}", alert.getMessage());
                // Logic to store alert could be added here
            }
        } catch (Exception e) {
            throw new CustomException("Error while sniffing packets", e);
        }
    }
}
