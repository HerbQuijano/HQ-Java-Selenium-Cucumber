package org.hquijano.pageobjects;

import org.hquijano.utilityclasses.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StatusCodesPage extends BasePage {

    @FindBy(css = "a[href='status_codes/200']")
    WebElement status200Link;
    @FindBy(css = "a[href='status_codes/301']")
    WebElement status301Link;
    @FindBy(css = "a[href='status_codes/404']")
    WebElement status404Link;
    @FindBy(css = "a[href='status_codes/500']")
    WebElement status500Link;

    public StatusCodesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getStatus200URL() {

        return status200Link.getAttribute("href");
    }

    public String getStatus301URL() {
        return status301Link.getAttribute("href");
    }

    public String getStatus404URL() {
        return status404Link.getAttribute("href");
    }

    public String getStatus500URL() {
        return status500Link.getAttribute("href");
    }

}
