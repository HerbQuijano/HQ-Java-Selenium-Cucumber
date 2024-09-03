package org.hquijano.pageobjects;

import org.hquijano.utilityclasses.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginResultPage extends BasePage {

    WebElement resultMessage = driver.findElement(By.cssSelector(".flash"));

    public LoginResultPage(WebDriver driver){
        super(driver);
    }

    public String getResultMessage(){
        return resultMessage.getText();
    }

    public boolean isResultMessageDisplayed(){
        return resultMessage.isDisplayed();
    }
}
