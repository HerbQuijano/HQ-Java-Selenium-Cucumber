package org.hquijano.testcomponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.hquijano.pageobjects.BadSSLPage;
import org.hquijano.pageobjects.LandingPage;
import org.hquijano.pageobjects.StatusCodesPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

    @BeforeClass
    public void setup() {
//        Properties prop = new Properties();
//
//        try {
//            FileInputStream propFile = new FileInputStream("src/test/resources/config.properties");
//            prop.load(propFile);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        String browser = prop.getProperty("browser");
        String browser = System.getProperty("browser", "chrome");

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("chrome-badSSL")) {
            ChromeOptions options = new ChromeOptions();
            options.setAcceptInsecureCerts(true);
            driver = new ChromeDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public LandingPage launchApplication() {
        driver.get("https://the-internet.herokuapp.com/");
        return new LandingPage(driver);
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
