package org.hquijano.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Hooks {
    private WebDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        //String hubURL = "http://selenium-hub:4444/wd/hub";
        String hubURL = "http://localhost:4444/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String browser = System.getProperty("browser", "chrome");

        if (browser.equalsIgnoreCase("headless-chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            capabilities.setBrowserName("chrome");
            options.merge(capabilities);
            driver = new RemoteWebDriver(new URL(hubURL), options);
        } else if (browser.equalsIgnoreCase("chrome")) {
            capabilities.setBrowserName("chrome");
            driver = new RemoteWebDriver(new URL(hubURL), capabilities);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            capabilities.setBrowserName("firefox");
            options.setAcceptInsecureCerts(true);
            options.merge(capabilities);
            driver = new RemoteWebDriver(new URL(hubURL), options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        // This will quit the WebDriver after each scenario
        driver.quit();

    }

    // This method provides WebDriver for dependency injection
    public Hooks() {
    }

    public WebDriver getDriver() {
        return driver;
    }
}