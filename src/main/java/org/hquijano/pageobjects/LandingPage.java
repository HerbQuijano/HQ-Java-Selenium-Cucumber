package org.hquijano.pageobjects;

import org.hquijano.utilityclasses.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage extends BasePage {

    WebElement formAuthLink = driver.findElement(By.linkText("Form Authentication"));

    public LandingPage(WebDriver driver){
        super(driver);
    }

    public void clickOnFormAuthLink(){
        formAuthLink.click();
    }

    public FormAuthPage navigateToFormAuthPage(){
        clickOnFormAuthLink();
        return new FormAuthPage(driver);
    }

}
