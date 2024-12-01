package nonfunctional.security;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * MarketDataPenetrationTest performs penetration testing on the market data
 * processor to identify vulnerabilities and ensure secure data handling.
 */
public class MarketDataPenetrationTest {

    private WebDriver driver;

    /**
     * Sets up the WebDriver before each test.
     */
    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
    }

    /**
     * Tests the system's resilience to SQL injection attempts.
     */
    @Test
    public void testSqlInjectionProtection() {
        try {
            driver.get("https://trading-platform.example.com/market-data");

            // Verify initial page load
            String pageTitle = driver.getTitle();
            assertTrue(pageTitle.contains("Market Data"), "Page title should indicate the market data page is loaded.");

            // Attempt SQL injection
            driver.findElement(By.id("data-query-input")).sendKeys("'; DROP TABLE market_data; --");
            driver.findElement(By.id("submit-query")).click();

            // Verify the system prevents SQL injection
            boolean errorDisplayed = driver.findElement(By.id("error-message")).getText()
                    .contains("Invalid input detected");
            assertTrue(errorDisplayed, "System should detect and prevent SQL injection.");
        } catch (Exception e) {
            throw new RuntimeException("SQL Injection test failed due to exception", e);
        }
    }

    /**
     * Tests the system's resilience to cross-site scripting (XSS) attacks.
     */
    @Test
    public void testXssProtection() {
        try {
            driver.get("https://trading-platform.example.com/market-data");

            // Inject a malicious script
            String xssPayload = "<script>alert('XSS');</script>";
            driver.findElement(By.id("data-query-input")).sendKeys(xssPayload);
            driver.findElement(By.id("submit-query")).click();

            // Verify the system escapes the malicious input
            boolean xssBlocked = driver.getPageSource().contains(xssPayload);
            assertFalse(xssBlocked, "System should escape and prevent XSS payloads.");
        } catch (Exception e) {
            throw new RuntimeException("XSS test failed due to exception", e);
        }
    }

    /**
     * Tests the system's response to malformed data inputs.
     */
    @Test
    public void testMalformedDataHandling() {
        try {
            driver.get("https://trading-platform.example.com/market-data");

            // Submit malformed data
            String malformedData = "INVALID_DATA_!@#";
            driver.findElement(By.id("data-query-input")).sendKeys(malformedData);
            driver.findElement(By.id("submit-query")).click();

            // Verify the system handles malformed data gracefully
            boolean errorDisplayed = driver.findElement(By.id("error-message")).getText()
                    .contains("Unable to process input");
            assertTrue(errorDisplayed, "System should reject malformed data and display an appropriate error.");
        } catch (Exception e) {
            throw new RuntimeException("Malformed data handling test failed due to exception", e);
        }
    }

    /**
     * Tears down the WebDriver instance after each test.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
