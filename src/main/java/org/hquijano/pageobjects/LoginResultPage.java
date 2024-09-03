package org.hquijano.pageobjects;

import org.hquijano.utilityclasses.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginResultPage extends BasePage {

    @FindBy(css = ".flash")
    WebElement resultMessage;

    public LoginResultPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getResultMessage(){
        return resultMessage.getText();
    }

    public boolean isResultMessageDisplayed(){
        return resultMessage.isDisplayed();
    }
}
