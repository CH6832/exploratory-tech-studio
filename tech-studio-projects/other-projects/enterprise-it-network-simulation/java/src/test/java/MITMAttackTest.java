import com.system.simulation.devices.Firewall;
import com.system.simulation.security.MitmAttack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * MITMAttackTest class tests the MITM attack simulation.
 */
public class MITMAttackTest {

    private Firewall firewall;
    private MitmAttack mitmAttack;

    /**
     * Sets up a Firewall instance and MITMAttack instance for testing.
     */
    @BeforeEach
    public void setUp() {
        firewall = new Firewall();
        mitmAttack = new MitmAttack(firewall);
    }

    /**
     * Tests launching a MITM attack and its effects on firewall simulation.
     */
    @Test
    public void testMITMAttackLaunch() {
        mitmAttack.launch();
        assertTrue(firewall.isInMitmMode(), "Firewall should be in MITM mode after attack launch.");
    }
}
