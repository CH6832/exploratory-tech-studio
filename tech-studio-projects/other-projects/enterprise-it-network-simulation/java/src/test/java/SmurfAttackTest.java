import com.system.simulation.devices.Router;
import com.system.simulation.security.SmurfAttack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * SmurfAttackTest class tests the smurf attack simulation.
 */
public class SmurfAttackTest {

    private Router router;
    private SmurfAttack smurfAttack;

    /**
     * Sets up the Router and SmurfAttack instances for testing.
     */
    @BeforeEach
    public void setUp() {
        router = new Router();
        smurfAttack = new SmurfAttack();
    }

    /**
     * Tests launching a smurf attack and router load changes.
     */
    @Test
    public void testSmurfAttackLaunch() {
        smurfAttack.launch();
        assertTrue(router.getLoad() > 0, "Router load should increase after smurf attack.");
    }
}
