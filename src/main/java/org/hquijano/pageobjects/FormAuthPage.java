package org.hquijano.pageobjects;

import org.hquijano.utilityclasses.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormAuthPage extends BasePage {

    public FormAuthPage(WebDriver driver){
        super(driver);
    }


    WebElement usernameInput = driver.findElement(By.id("username"));
    WebElement passwordInput = driver.findElement(By.id("password"));
    WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

    public void fillAuthForm(String username, String password){
        //usernameInput.sendKeys(username);
        type(usernameInput, username);
        type(passwordInput, password);
    }

    public void clickSubmitButton(){
        click(submitButton);
    }

    public LoginResultPage getLoginResultPage(){
        return new LoginResultPage(driver);
    }
}
