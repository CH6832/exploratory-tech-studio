import com.system.simulation.network.VPNTunnel;
import com.system.simulation.network.config.VPNConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * VPNTest class contains unit tests for the VPNTunnel class,
 * verifying configuration loading, connection handling, and secure data transfer.
 */
public class VPNTest {

    private VPNTunnel vpnTunnel;
    private VPNConfig vpnConfig;

    /**
     * Sets up the VPN tunnel instance with sample configuration.
     */
    @BeforeEach
    public void setUp() {
        vpnConfig = new VPNConfig("CorporateVPN", "10.0.0.2", 443);
        vpnTunnel = new VPNTunnel(vpnConfig);
    }

    /**
     * Test VPN configuration values to ensure they are correctly loaded.
     */
    @Test
    public void testVPNConfiguration() {
        assertEquals("CorporateVPN", vpnTunnel.getVpnName(), "VPN name should match configuration");
        assertEquals("10.0.0.2", vpnTunnel.getIpAddress(), "VPN IP should match configuration");
        assertEquals(443, vpnTunnel.getPort(), "VPN port should match configuration");
    }

    /**
     * Test establishing a VPN connection with a valid client.
     */
    @Test
    public void testVPNConnection() {
        boolean connected = vpnTunnel.connect("Client1");

        assertTrue(connected, "VPN should allow valid client connection");
    }

    /**
     * Test secure data transfer over the VPN tunnel.
     */
    @Test
    public void testDataTransfer() {
        vpnTunnel.connect("Client1");
        String response = vpnTunnel.sendData("Client1", "Secure Message");

        assertEquals("Data received: Secure Message", response, "VPN should handle secure data transfer correctly");
    }
}
