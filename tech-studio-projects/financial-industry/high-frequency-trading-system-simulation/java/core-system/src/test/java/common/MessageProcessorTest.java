package common;

import com.hft.common.MessageProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageProcessorTest {

    private MessageProcessor messageProcessor;

    @BeforeEach
    public void setup() {
        messageProcessor = new MessageProcessor();
    }

    @Test
    public void testProcessValidMessage() {
        String validMessage = "BUY 100 100.5"; // Example message format
        boolean result = messageProcessor.processMessage(validMessage);
        assertTrue(result, "Valid message should be processed successfully.");
    }

    @Test
    public void testProcessInvalidMessage() {
        String invalidMessage = "INVALID MESSAGE FORMAT";
        boolean result = messageProcessor.processMessage(invalidMessage);
        assertFalse(result, "Invalid message should fail processing.");
    }

    @Test
    public void testProcessEmptyMessage() {
        String emptyMessage = "";
        boolean result = messageProcessor.processMessage(emptyMessage);
        assertFalse(result, "Empty message should not be processed.");
    }

    @Test
    public void testMessageProcessingEdgeCases() {
        String edgeCaseMessage = "BUY 0 100.5"; // Edge case: Quantity is zero
        boolean result = messageProcessor.processMessage(edgeCaseMessage);
        assertFalse(result, "Edge case message with zero quantity should fail processing.");
    }

    @Test
    public void testProcessMultipleMessages() {
        String[] messages = {
                "BUY 100 100.5",
                "SELL 200 99.0",
                "BUY 50 105.5"
        };

        for (String message : messages) {
            boolean result = messageProcessor.processMessage(message);
            assertTrue(result, "Each message should be processed successfully.");
        }
    }
}
