package nonfunctional.compatibility;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * DeviceCompatibilityTest ensures that the trading platform behaves consistently across different devices (desktop, tablet, mobile).
 */
public class DeviceCompatibilityTest {

    private WebDriver driver;
    private String deviceType;

    /**
     * Configures the WebDriver for different device types.
     *
     * @param deviceType The type of device to simulate ("desktop", "tablet", "mobile").
     */
    private void initializeDriverForDevice(String deviceType) {
        ChromeOptions options = new ChromeOptions();

        switch (deviceType.toLowerCase()) {
            case "mobile":
                options.addArguments("--window-size=375,812"); // Simulate iPhone X screen resolution
                System.out.println("Simulating Mobile Device...");
                break;
            case "tablet":
                options.addArguments("--window-size=768,1024"); // Simulate iPad screen resolution
                System.out.println("Simulating Tablet Device...");
                break;
            case "desktop":
            default:
                options.addArguments("--window-size=1920,1080"); // Default desktop resolution
                System.out.println("Simulating Desktop Device...");
                break;
        }

        driver = new ChromeDriver(options);
    }

    /**
     * Setup before each test to configure the driver.
     */
    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
    }

    /**
     * Tests the trading platform on a mobile device.
     */
    @Test
    public void testMobileCompatibility() {
        deviceType = "mobile";
        initializeDriverForDevice(deviceType);
        performDeviceSpecificTests();
    }

    /**
     * Tests the trading platform on a tablet device.
     */
    @Test
    public void testTabletCompatibility() {
        deviceType = "tablet";
        initializeDriverForDevice(deviceType);
        performDeviceSpecificTests();
    }

    /**
     * Tests the trading platform on a desktop device.
     */
    @Test
    public void testDesktopCompatibility() {
        deviceType = "desktop";
        initializeDriverForDevice(deviceType);
        performDeviceSpecificTests();
    }

    /**
     * Common logic for verifying platform compatibility across devices.
     */
    private void performDeviceSpecificTests() {
        try {
            driver.get("https://trading-platform.example.com/login");

            // Verify platform compatibility by checking the page title
            String pageTitle = driver.getTitle();
            assertTrue(pageTitle.contains("Trading Platform"),
                    deviceType + ": Page title should contain 'Trading Platform'");

            // Interact with the login functionality
            driver.findElement(By.id("username")).sendKeys("testuser");
            driver.findElement(By.id("password")).sendKeys("testpassword");
            driver.findElement(By.id("login-button")).click();

            // Verify successful login
            String dashboardTitle = driver.getTitle();
            assertTrue(dashboardTitle.contains("Dashboard"),
                    deviceType + ": Dashboard should load successfully after login");

            // Additional UI-specific checks for different devices
            boolean tradeWidgetVisible = driver.findElement(By.id("trade-widget")).isDisplayed();
            assertTrue(tradeWidgetVisible,
                    deviceType + ": Trade widget should be visible and functional");
        } catch (Exception e) {
            throw new RuntimeException(deviceType + ": Test failed due to exception", e);
        }
    }

    /**
     * Cleans up the WebDriver instance after each test.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
