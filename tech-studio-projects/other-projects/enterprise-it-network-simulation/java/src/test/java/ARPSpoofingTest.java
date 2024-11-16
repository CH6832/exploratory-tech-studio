import com.system.simulation.devices.Router;
import com.system.simulation.security.ARPSpoofing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ARPSpoofingTest class tests ARP spoofing simulation.
 */
public class ARPSpoofingTest {

    private Router router;
    private ARPSpoofing arpSpoofing;

    /**
     * Sets up the Router and ARPSpoofing instances for testing.
     */
    @BeforeEach
    public void setUp() {
        router = new Router();
        arpSpoofing = new ARPSpoofing(router);
    }

    /**
     * Tests ARP spoofing by checking ARP cache poisoning.
     */
    @Test
    public void testARPSpoofing() {
        arpSpoofing.spoof();
        assertFalse(router.isArpCachePoisoned(), "Router should have a poisoned ARP cache after spoofing.");
    }
}
