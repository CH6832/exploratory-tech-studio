package com.system.simulation.tests;

import com.system.simulation.devices.Router;
import com.system.simulation.security.DDoSAttack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * DDoSAttackTest class contains unit tests for the DDoSAttack class,
 * verifying attack launch and impact on router.
 */
public class DDoSAttackTest {

    private Router router;
    private DDoSAttack ddosAttack;

    /**
     * Sets up a Router instance and DDoSAttack instance for testing.
     */
    @BeforeEach
    public void setUp() {
        router = new Router();
        ddosAttack = new DDoSAttack(router);
    }

    /**
     * Tests that the DDoS attack is launched and router packet load increases.
     */
    @Test
    public void testDDoSAttackLaunch() {
        ddosAttack.launch();
        assertTrue(router.getLoad() > 0, "Router load should increase after DDoS attack.");
    }
}
