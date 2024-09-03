package org.hquijano.pageobjects;

import org.hquijano.utilityclasses.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StatusCodesPage extends BasePage {

    public StatusCodesPage(WebDriver driver) {
        super(driver);
    }

    //WebElement statusCodeTitle = driver.findElement(By.cssSelector("h3"));
    WebElement status200Link = driver.findElement(By.cssSelector("a[href='status_codes/200']"));
    WebElement status301Link = driver.findElement(By.cssSelector("a[href='status_codes/301']"));
    WebElement status404Link = driver.findElement(By.cssSelector("a[href='status_codes/404']"));
    WebElement status500Link = driver.findElement(By.cssSelector("a[href='status_codes/500']"));

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
