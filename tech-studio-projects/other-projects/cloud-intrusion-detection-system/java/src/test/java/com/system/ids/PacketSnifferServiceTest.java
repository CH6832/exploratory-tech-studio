package com.system.ids;

import com.system.ids.PacketSnifferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PacketSnifferServiceTest {
    private PacketSnifferService packetSnifferService;

    @BeforeEach
    public void setUp() {
        packetSnifferService = new PacketSnifferService();
    }

    @Test
    public void testSniffPackets() {
        packetSnifferService.sniffPackets();
        // Check for alerts, modify as needed
    }
}
