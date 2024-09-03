package org.hquijano.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class StandaloneTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        Properties prop = new Properties();

        try{
            FileInputStream propFile = new FileInputStream("src/test/resources/config.properties");
            prop.load(propFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        String browser = prop.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome"))
        {
            driver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }
        else if(browser.equalsIgnoreCase("edge")){
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
    }

    @AfterMethod
    public void teardown(){
        if(driver != null){
            driver.quit();
        }
    }

    @Test
    public void landingPageTest(){
        WebElement formAuthLink = driver.findElement(By.linkText("Form Authentication"));
        Assert.assertTrue(formAuthLink.isDisplayed());
    }

    @Test
    public void formAuthTest(){
        Properties data = new Properties();

        WebElement formAuthLink = driver.findElement(By.linkText("Form Authentication"));
        formAuthLink.click();
        try{
            FileInputStream dataFile = new FileInputStream("src/test/resources/data.properties");
            data.load(dataFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        usernameInput.sendKeys(data.getProperty("username"));
        passwordInput.sendKeys(data.getProperty("password"));
        submitButton.click();

        WebElement flashSuccessMessage = driver.findElement(By.className("flash"));
        Assert.assertTrue(flashSuccessMessage.getText().contains("You logged into a secure area!"));
    }
}
