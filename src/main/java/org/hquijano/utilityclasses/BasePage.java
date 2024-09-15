package org.hquijano.utilityclasses;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    int timeout = 10;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public void setTimeOut(int seconds) {
        this.timeout = seconds;
    }

    public void type(WebElement element, String text) {
        element.sendKeys(text);
    }

    public void click(WebElement element) {
        element.click();
    }

    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void getElementScreenshot(WebElement element, String fileName) {
        File sc = element.getScreenshotAs(OutputType.FILE);
        try {
            sc.renameTo(new File(fileName));
            FileUtils.copyFile(sc, new File("src/screenshots/" + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
