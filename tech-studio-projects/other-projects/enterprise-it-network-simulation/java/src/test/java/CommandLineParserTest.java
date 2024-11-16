import com.system.simulation.Main.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * CommandLineParserTest class contains tests for command-line argument parsing.
 */
public class CommandLineParserTest {

    private final CommandLineParser parser = new CommandLineParser();

    /**
     * Tests parsing the "start" command-line option.
     */
    @Test
    public void testParseStartCommand() {
        String[] args = {"-s"};
        CommandLine cmd = parser.parseArguments(args);
        assertNotNull(cmd);
        assertTrue(cmd.hasOption("start"), "'start' command should be parsed correctly");
    }

    /**
     * Tests parsing the "simulateAttack" command-line option.
     */
    @Test
    public void testParseSimulateAttackCommand() {
        String[] args = {"-a"};
        CommandLine cmd = parser.parseArguments(args);
        assertNotNull(cmd);
        assertTrue(cmd.hasOption("simulateAttack"), "'simulateAttack' command should be parsed correctly");
    }
}
