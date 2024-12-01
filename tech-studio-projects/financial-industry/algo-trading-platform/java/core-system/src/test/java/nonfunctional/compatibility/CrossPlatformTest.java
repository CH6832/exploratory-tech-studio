package com.system.algotrading.compatibility;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * CrossPlatformTest ensures that the trading platform behaves consistently across different platforms (Windows, Mac, Linux).
 */
public class CrossPlatformTest {

    private WebDriver driver;
    private String osName;

    /**
     * Detects the operating system and sets the WebDriver accordingly.
     */
    @BeforeEach
    public void setup() {
        osName = System.getProperty("os.name").toLowerCase();
        initializeDriverForPlatform();
    }

    /**
     * Initializes WebDriver based on the detected operating system.
     */
    private void initializeDriverForPlatform() {
        if (osName.contains("win")) {
            System.out.println("Running tests on Windows...");
            System.setProperty("webdriver.chrome.driver", "drivers/windows/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (osName.contains("mac")) {
            System.out.println("Running tests on Mac...");
            System.setProperty("webdriver.chrome.driver", "drivers/mac/chromedriver");
            driver = new ChromeDriver();
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
            System.out.println("Running tests on Linux...");
            System.setProperty("webdriver.chrome.driver", "drivers/linux/chromedriver");
            driver = new ChromeDriver();
        } else {
            throw new IllegalStateException("Unsupported Operating System: " + osName);
        }
    }

    /**
     * Test trading platform functionality on the detected platform.
     */
    @Test
    public void testPlatformCompatibility() {
        try {
            driver.get("https://trading-platform.example.com/login");

            // Verify the platform compatibility by interacting with the page
            String pageTitle = driver.getTitle();
            assertTrue(pageTitle.contains("Trading Platform"),
                    osName + ": Page title should contain 'Trading Platform'");

            // Interact with the trading platform (e.g., login and perform an action)
            driver.findElement(By.id("username")).sendKeys("testuser");
            driver.findElement(By.id("password")).sendKeys("testpassword");
            driver.findElement(By.id("login-button")).click();

            // Verify successful login
            String dashboardTitle = driver.getTitle();
            assertTrue(dashboardTitle.contains("Dashboard"),
                    osName + ": Dashboard should load successfully after login");

            // Additional checks for platform-specific UI rendering
            boolean tradeWidgetVisible = driver.findElement(By.id("trade-widget")).isDisplayed();
            assertTrue(tradeWidgetVisible,
                    osName + ": Trade widget should be visible and functional");
        } catch (Exception e) {
            throw new RuntimeException(osName + ": Test failed due to exception", e);
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
