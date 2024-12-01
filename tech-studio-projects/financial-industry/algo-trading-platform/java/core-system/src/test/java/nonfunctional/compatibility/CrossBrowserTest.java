package nonfunctional.compatibility;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * CrossBrowserTest ensures that the trading platform behaves consistently across different browsers.
 */
public class CrossBrowserTest {

    private WebDriver driver;

    /**
     * Initialize WebDriver for a specific browser.
     *
     * @param browserName The name of the browser to test.
     */
    private void initializeDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
                driver = new ChromeDriver();
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
                driver = new FirefoxDriver();
                break;

            case "edge":
                System.setProperty("webdriver.edge.driver", "path/to/edgedriver");
                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }

    /**
     * Test trading platform functionality in Chrome.
     */
    @Test
    public void testInChrome() {
        initializeDriver("chrome");
        runCrossBrowserTests("Chrome");
    }

    /**
     * Test trading platform functionality in Firefox.
     */
    @Test
    public void testInFirefox() {
        initializeDriver("firefox");
        runCrossBrowserTests("Firefox");
    }

    /**
     * Test trading platform functionality in Edge.
     */
    @Test
    public void testInEdge() {
        initializeDriver("edge");
        runCrossBrowserTests("Edge");
    }

    /**
     * Runs the cross-browser tests for the specified browser.
     *
     * @param browserName The name of the browser being tested.
     */
    private void runCrossBrowserTests(String browserName) {
        try {
            // Navigate to the trading platform's login page
            driver.get("https://trading-platform.example.com/login");

            // Verify the page title is correct
            String pageTitle = driver.getTitle();
            assertTrue(pageTitle.contains("Trading Platform"),
                    browserName + ": Page title should contain 'Trading Platform'");

            // Interact with login form
            driver.findElement(By.id("username")).sendKeys("testuser");
            driver.findElement(By.id("password")).sendKeys("testpassword");
            driver.findElement(By.id("login-button")).click();

            // Verify successful login (e.g., dashboard is displayed)
            String dashboardTitle = driver.getTitle();
            assertTrue(dashboardTitle.contains("Dashboard"),
                    browserName + ": Dashboard should load successfully after login");

            // Additional UI interactions (e.g., check trade statuses)
            boolean tradeStatusVisible = driver.findElement(By.id("trade-status")).isDisplayed();
            assertTrue(tradeStatusVisible,
                    browserName + ": Trade status section should be visible");
        } catch (Exception e) {
            throw new RuntimeException(browserName + ": Test failed due to exception", e);
        }
    }

    /**
     * Clean up after each test.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
