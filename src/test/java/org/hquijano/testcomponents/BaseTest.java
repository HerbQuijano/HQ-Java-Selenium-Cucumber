package org.hquijano.testcomponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.hquijano.pageobjects.BadSSLPage;
import org.hquijano.pageobjects.FormAuthPage;
import org.hquijano.pageobjects.LandingPage;
import org.hquijano.pageobjects.StatusCodesPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public abstract class BaseTest {
    protected static ExtentReports reports;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ExtentTest extentTest;

    //@BeforeClass
    public void setup() throws MalformedURLException {
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

    //@AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public LandingPage launchApplication() {
        driver.get("https://the-internet.herokuapp.com/");
        return new LandingPage(driver);
    }

    public FormAuthPage navigateToFormAuthPage(){
        driver.get("https://the-internet.herokuapp.com/login");
        return new FormAuthPage(driver);
    }

    public BadSSLPage navigateToBadSSLPage() {
        driver.get("https://expired.badssl.com/");
        return new BadSSLPage(driver);
    }

    public StatusCodesPage navigateToStatusCodesPage() {
        driver.get("https://the-internet.herokuapp.com/status_codes");
        return new StatusCodesPage(driver);
    }

    public String getScreenshot(String testCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshotFile = ts.getScreenshotAs(OutputType.FILE);
        String fileName = testCaseName + ".png";
        Path screenshotPath = Paths.get(System.getProperty("user.dir") + "/src/test/resources/extent/screenshots/" + fileName);
        Files.move(screenshotFile.toPath(), screenshotPath, REPLACE_EXISTING);
        return screenshotPath.toString();
    }

}
