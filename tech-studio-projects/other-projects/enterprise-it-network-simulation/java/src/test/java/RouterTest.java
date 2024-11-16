import com.system.simulation.devices.Router;
import com.system.simulation.network.config.NetworkConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * RouterTest class contains unit tests for the Router class,
 * verifying routing table initialization, packet handling, and IP assignment.
 */
public class RouterTest {

    private Router router;
    private NetworkConfig networkConfig;

    /**
     * Sets up a new Router instance with sample configuration for testing.
     */
    @BeforeEach
    public void setUp() {
        networkConfig = new NetworkConfig("10.0.0.1", 24, "CorporateNet");
        router = new Router(networkConfig);
    }

    /**
     * Test that the router is initialized with the correct IP and network name.
     */
    @Test
    public void testRouterInitialization() {
        assertEquals("10.0.0.1", router.getIpAddress(), "Router IP should match configuration");
        assertEquals("CorporateNet", router.getNetworkName(), "Network name should match configuration");
    }

    /**
     * Tests packet handling by simulating data packets sent to the router.
     */
    @Test
    public void testHandlePacket() {
        String packetData = "Test Packet Data";
        boolean handled = router.receivePacket(packetData);

        assertTrue(handled, "Router should handle packet successfully");
    }
}
