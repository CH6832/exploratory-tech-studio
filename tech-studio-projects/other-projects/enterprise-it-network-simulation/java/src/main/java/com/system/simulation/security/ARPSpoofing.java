package com.system.simulation.security;

import com.system.simulation.devices.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The ARPSpoofing class simulates an ARP spoofing attack.
 * This attack involves sending fake ARP replies to poison the ARP cache
 * of devices in the network, allowing the attacker to intercept network traffic.
 */
public class ARPSpoofing {

    private static final Logger logger = LoggerFactory.getLogger(ARPSpoofing.class);

    private final Router router;

    /**
     * Constructs the ARPSpoofing attack for the given router.
     *
     * @param router The router to target for the ARP spoofing attack.
     */
    public ARPSpoofing(Router router) {
        this.router = router;
    }

    /**
     * Launches the ARP Spoofing attack by sending fake ARP replies to poison
     * the ARP cache of devices in the network.
     */
    public void spoof() {
        try {
            logger.info("Launching ARP Spoofing attack...");

            // Simulate sending fake ARP replies to the network
            String victimIP = "192.168.1.5"; // Example victim IP
            String attackerMAC = "00:11:22:33:44:55"; // Example attacker MAC address
            String victimMAC = "00:aa:bb:cc:dd:ee"; // Example victim MAC address (this would normally be discovered)

            // Poison the ARP cache of the victim by associating the attacker's MAC with the victim's IP
            poisonARPCache(victimIP, attackerMAC);

            // Log the attack details
            logger.info("ARP Spoofing Attack Details:");
            logger.info("Victim IP: {}", victimIP);
            logger.info("Attacker MAC: {}", attackerMAC);
            logger.info("Victim MAC: {}", victimMAC);

            // Simulate ARP poisoning on the router and target devices
            router.receivePoisonedARP(victimIP, attackerMAC);

            logger.info("ARP Spoofing attack successfully executed.");
        } catch (Exception e) {
            logger.error("Error while executing ARP Spoofing attack: {}", e.getMessage());
        }
    }

    /**
     * Simulates poisoning the ARP cache of a device.
     * In a real-world scenario, this would involve sending fake ARP replies.
     *
     * @param victimIP The IP address of the victim device.
     * @param attackerMAC The MAC address of the attacker.
     */
    private void poisonARPCache(String victimIP, String attackerMAC) {
        // Simulate poisoning the ARP cache by mapping the victim IP to the attacker's MAC address
        logger.info("Poisoning ARP cache: Victim IP {} -> Attacker MAC {}", victimIP, attackerMAC);
    }
}
