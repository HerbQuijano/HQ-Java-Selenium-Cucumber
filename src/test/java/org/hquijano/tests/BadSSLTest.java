package org.hquijano.tests;

import org.hquijano.pageobjects.BadSSLPage;
import org.hquijano.testcomponents.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class BadSSLTest extends BaseTest {

    @Test(groups = {"Certificates"})
    public void testBadSSL() throws IOException {
        BadSSLPage badSSLPage = navigateToBadSSLPage();
        //System.out.println(driver.getTitle());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshotFile = ts.getScreenshotAs(OutputType.FILE);
        Path screenshotPath = Paths.get("src/screenshots/screenshot.png");
        Files.move(screenshotFile.toPath(), screenshotPath, REPLACE_EXISTING);

        Assert.assertTrue(badSSLPage.getCertMessage().equalsIgnoreCase("net::ERR_CERT_DATE_INVALID"));
    }

}
