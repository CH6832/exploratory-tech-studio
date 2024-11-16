package com.system.simulation.tests;

import com.system.simulation.devices.Router;
import com.system.simulation.security.PacketLoss;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * PacketLossTest class tests the packet loss simulation.
 */
public class PacketLossTest {

    private Router router;
    private PacketLoss packetLoss;

    /**
     * Sets up a Router instance and PacketLoss instance for testing.
     */
    @BeforeEach
    public void setUp() {
        router = new Router();
        packetLoss = new PacketLoss(router);
    }

    /**
     * Tests packet loss simulation with controlled packet drops.
     */
    @Test
    public void testPacketLossSimulation() {
        packetLoss.simulate();
        assertTrue(router.getDroppedPackets() > 0, "Router should record dropped packets after packet loss simulation.");
    }
}
