package org.hquijano.pageobjects;

import org.hquijano.utilityclasses.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BadSSLPage extends BasePage {

    public BadSSLPage(WebDriver driver) {
        super(driver);
    }

    WebElement certMessage = driver.findElement(By.id("error-code"));

    public String getCertMessage(){
        return certMessage.getText();
    }
}
