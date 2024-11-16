package risk;

import com.hft.risk.MarginCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarginCalculatorTest {
    private MarginCalculator marginCalculator;

    @BeforeEach
    public void setup() {
        marginCalculator = new MarginCalculator();
    }

    @Test
    public void testCalculateMargin() {
        double orderValue = 1000.0;
        double margin = marginCalculator.calculateMargin(orderValue, "DEFAULT");
        assertEquals(100.0, margin, "Margin should be 10% of order value");
    }
}

