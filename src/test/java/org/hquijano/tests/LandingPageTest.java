package org.hquijano.tests;

import org.hquijano.pageobjects.LandingPage;
import org.hquijano.testcomponents.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LandingPageTest extends BaseTest {

    @Test
    public void testFormAuthExists() {
        LandingPage lp = launchApplication();
        lp.waitForElementToBeVisible(driver.findElement(By.linkText("Form Authentication")));
        Assert.assertTrue(driver.findElement(By.linkText("Form Authentication")).isDisplayed());
    }
}
