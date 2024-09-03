package org.hquijano.testcomponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.hquijano.pageobjects.BadSSLPage;
import org.hquijano.pageobjects.LandingPage;
import org.hquijano.pageobjects.StatusCodesPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

public abstract class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    static ExtentReports reports;
    protected ExtentTest test;

    static {
        System.out.println("BeforeSuite: Initializing ExtentReports");
        reports = new ExtentReports();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("src/test/resources/extent/report.html");
        reports.attachReporter(htmlReporter);
    }

    @BeforeClass
    public void setup() {
        Properties prop = new Properties();

        try {
            FileInputStream propFile = new FileInputStream("src/test/resources/config.properties");
            prop.load(propFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String browser = prop.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("chrome-badSSL")){
            ChromeOptions options = new ChromeOptions();
            options.setAcceptInsecureCerts(true);
            driver = new ChromeDriver(options);
        }


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        //driver.get("https://the-internet.herokuapp.com/");
    }

    @BeforeMethod
    public void beforeTest(Method method) {
        System.out.println("Creating test");
        test = reports.createTest(method.getName());
    }

    @AfterMethod
    public void captureStatus(ITestResult result) {
        // Capture the result of each test and log it in the report
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test skipped");
        }
    }

    @AfterSuite(alwaysRun = true)
    public void flushReport() {
        reports.flush();
    }

    @AfterClass
    public void tearDown(){
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

    public StatusCodesPage navigateToStatusCodesPage(){
        driver.get("https://the-internet.herokuapp.com/status_codes");
        return new StatusCodesPage(driver);
    }

}
