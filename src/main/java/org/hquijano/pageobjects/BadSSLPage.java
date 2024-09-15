package org.hquijano.pageobjects;

import org.hquijano.utilityclasses.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BadSSLPage extends BasePage {

    @FindBy(id = "error-code")
    private WebElement certMessage;

    public BadSSLPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getCertMessage() {
        return certMessage.getText();
    }
}
