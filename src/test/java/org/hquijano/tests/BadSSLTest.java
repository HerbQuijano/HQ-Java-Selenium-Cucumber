package org.hquijano.tests;

import org.hquijano.pageobjects.BadSSLPage;
import org.hquijano.testcomponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class BadSSLTest extends BaseTest {

    @Test(groups = {"Certificates"})
    public void testBadSSL() throws IOException {
        BadSSLPage badSSLPage = navigateToBadSSLPage();
        //System.out.println(driver.getTitle());
        Assert.assertTrue(badSSLPage.getCertMessage().equalsIgnoreCase("net::ERR_CERT_DATE_INVALID"));
    }

}