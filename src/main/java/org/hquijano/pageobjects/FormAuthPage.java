package org.hquijano.pageobjects;

import org.hquijano.utilityclasses.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormAuthPage extends BasePage {

    @FindBy(id = "username")
    WebElement usernameInput;
    @FindBy(id = "password")
    WebElement passwordInput;
    @FindBy(css = "button[type='submit']")  // CSS selector is used here instead of id to handle dynamic elements with IDs changing over time
    WebElement submitButton;

    public FormAuthPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillAuthForm(String username, String password) {
        //usernameInput.sendKeys(username);
        type(usernameInput, username);
        type(passwordInput, password);
    }

    public void clickSubmitButton() {
        click(submitButton);
    }

    public LoginResultPage getLoginResultPage() {
        return new LoginResultPage(driver);
    }
}
