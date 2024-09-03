package org.hquijano.pageobjects;

import org.hquijano.utilityclasses.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BadSSLPage extends BasePage {

    public BadSSLPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "error-code")
    private WebElement certMessage;
    //WebElement certMessage = driver.findElement(By.id("error-code"));


    public String getCertMessage(){
        return certMessage.getText();
    }
}
