package org.hquijano.tests;

import org.hquijano.pageobjects.StatusCodesPage;
import org.hquijano.testcomponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class StatusCodesTest extends BaseTest {
    @Test
    public void testStatus200() {
        StatusCodesPage scPage = navigateToStatusCodesPage();
        try {
            URL url = new URL(scPage.getStatus200URL());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            Assert.assertEquals(200, responseCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testStatus301() {
        StatusCodesPage scPage = navigateToStatusCodesPage();
        try {
            URL url = new URL(scPage.getStatus301URL());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            Assert.assertEquals(301, responseCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testStatus404() {
        StatusCodesPage scPage = navigateToStatusCodesPage();
        try {
            URL url = new URL(scPage.getStatus404URL());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            Assert.assertEquals(404, responseCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testStatus500() {
        StatusCodesPage scPage = navigateToStatusCodesPage();
        try {
            URL url = new URL(scPage.getStatus500URL());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            SoftAssert sa = new SoftAssert();
            sa.assertEquals(500, responseCode);
            Assert.assertEquals(500, responseCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
