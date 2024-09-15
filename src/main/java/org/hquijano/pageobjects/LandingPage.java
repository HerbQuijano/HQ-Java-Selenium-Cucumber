package org.hquijano.pageobjects;

import org.hquijano.utilityclasses.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BasePage {

    @FindBy(linkText = "Form Authentication")
    WebElement formAuthLink;

    public LandingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnFormAuthLink() {
        formAuthLink.click();
    }

    public void navigateToHome() {
        driver.get("https://the-internet.herokuapp.com/");
    }

    public FormAuthPage navigateToFormAuthPage() {
        clickOnFormAuthLink();
        return new FormAuthPage(driver);
    }

}
