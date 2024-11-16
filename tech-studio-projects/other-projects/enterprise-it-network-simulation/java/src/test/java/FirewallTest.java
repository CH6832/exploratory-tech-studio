import com.system.simulation.devices.Firewall;
import com.system.simulation.network.config.FirewallRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * FirewallTest class contains unit tests for the Firewall class,
 * verifying rule application, packet filtering, and MITM simulation.
 */
public class FirewallTest {

    private Firewall firewall;
    private FirewallRules firewallRules;

    /**
     * Sets up a Firewall instance with mock rules for testing.
     */
    @BeforeEach
    public void setUp() {
        firewallRules = new FirewallRules();
        firewallRules.addRule("ALLOW", "10.0.0.0/24");
        firewallRules.addRule("DENY", "192.168.1.0/24");
        firewall = new Firewall(firewallRules);
    }

    /**
     * Test that allowed packets are accepted by the firewall.
     */
    @Test
    public void testAllowPacket() {
        boolean allowed = firewall.filterPacket("10.0.0.5");
        assertTrue(allowed, "Packet from allowed IP should pass through firewall");
    }

    /**
     * Test that denied packets are blocked by the firewall.
     */
    @Test
    public void testDenyPacket() {
        boolean denied = firewall.filterPacket("192.168.1.10");
        assertFalse(denied, "Packet from denied IP should be blocked by firewall");
    }

    /**
     * Test that modifying rules changes packet filtering behavior.
     */
    @Test
    public void testModifyRules() {
        firewallRules.addRule("ALLOW", "192.168.1.10");
        boolean allowed = firewall.filterPacket("192.168.1.10");

        assertTrue(allowed, "Modified rule should allow packet from previously denied IP");
    }
}
