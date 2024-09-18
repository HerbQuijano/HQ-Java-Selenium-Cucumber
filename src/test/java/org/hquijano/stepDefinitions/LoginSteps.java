package org.hquijano.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hquijano.pageobjects.FormAuthPage;
import org.hquijano.pageobjects.LandingPage;
import org.hquijano.pageobjects.LoginResultPage;
import org.hquijano.testcomponents.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.net.MalformedURLException;

public class LoginSteps {
    private Hooks hooks;
    private WebDriver driver;

    // Constructor injection of WebDriver (PicoContainer handles this)
    public LoginSteps(Hooks hooks) {
        this.hooks = hooks;
        this.driver = hooks.getDriver();
    }
    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @When("the user enters valid credentials")
    public void the_user_enters_valid_credentials() {
        WebElement userNameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submitBtn = driver.findElement(By.cssSelector("button[type='submit']"));

        userNameField.sendKeys("tomsmith");
        passwordField.sendKeys("SuperSecretPassword!");
        submitBtn.click();
    }

    @Then("message should be displayed on the page")
    public void message_should_be_displayed_on_the_page() {
        WebElement flashMessage = driver.findElement(By.cssSelector(".flash"));
        Assert.assertTrue(flashMessage.isDisplayed());
        Assert.assertTrue(flashMessage.getText().contains("You logged into a secure area!"));
    }
}