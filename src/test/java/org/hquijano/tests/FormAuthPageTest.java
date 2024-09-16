package org.hquijano.tests;

import org.hquijano.pageobjects.FormAuthPage;
import org.hquijano.pageobjects.LandingPage;
import org.hquijano.pageobjects.LoginResultPage;
import org.hquijano.testcomponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

public class FormAuthPageTest extends BaseTest {

    @Test(dataProvider = "loginSuccessData", groups = {"Smoke"})
    public void testLoginSuccess(String username, String password) {
        LandingPage lp = launchApplication();
        FormAuthPage fap = lp.navigateToFormAuthPage();
        //fap.fillAuthForm("tomsmith", "SuperSecretPassword!");
        fap.fillAuthForm(username, password);
        fap.clickSubmitButton();
        LoginResultPage lr = fap.getLoginResultPage();

        Assert.assertTrue(lr.isResultMessageDisplayed());
        Assert.assertTrue(lr.getResultMessage().contains("You logged into a secure area!"));
    }

    @Test(dataProvider = "loginFailData", groups = {"Smoke"})
    public void testInvalidPassword(String username, String password) {
        LandingPage lp = launchApplication();
        FormAuthPage fap = lp.navigateToFormAuthPage();
        fap.fillAuthForm(username, password);
        fap.clickSubmitButton();
        LoginResultPage lr = fap.getLoginResultPage();

        Assert.assertTrue(lr.isResultMessageDisplayed());
        Assert.assertTrue(lr.getResultMessage().contains("Your password is invalid!"));
    }

    @Test(groups = {"Smoke"}, dataProvider = "loginSuccessData")
    public void testLoginFlashMessage(String username, String password) {
        LandingPage lp = launchApplication();
        FormAuthPage fap = lp.navigateToFormAuthPage();
        //fap.fillAuthForm("tomsmith", "SuperSecretPassword!");
        fap.fillAuthForm(username, password);
        fap.clickSubmitButton();
        LoginResultPage lr = fap.getLoginResultPage();

        Assert.assertTrue(lr.isResultMessageDisplayed());
        Assert.assertTrue(lr.getResultMessage().contains("You logged into a secure area!"));
    }

    @Test(groups = {"Smoke"}, dataProvider = "loginSuccessDataMap")
    public void testLoginFlashMessageMap(HashMap<Object, Object> map) {
        LandingPage lp = launchApplication();
        FormAuthPage fap = lp.navigateToFormAuthPage();
        //fap.fillAuthForm("tomsmith", "SuperSecretPassword!");
        fap.fillAuthForm(map.get("username").toString(), map.get("password").toString());
        fap.clickSubmitButton();
        LoginResultPage lr = fap.getLoginResultPage();

        Assert.assertTrue(lr.isResultMessageDisplayed());
        Assert.assertTrue(lr.getResultMessage().contains("You logged into a secure area!"), "Login flash message not displayed as expected " + lr.getResultMessage());
    }

    @DataProvider
    public Object[][] loginSuccessData() {
        return new Object[][]{
                {"tomsmith", "SuperSecretPassword!"}
        };
    }

    @DataProvider
    public Object[][] loginFailData() {
        return new Object[][]{
                {"tomsmith", "SuperSecretPassword"}
        };
    }

    @DataProvider
    public Object[][] loginSuccessDataMap() {
        HashMap<Object, Object> dataMap = new HashMap<>();
        dataMap.put("username", "tomsmith");
        dataMap.put("password", "SuperSecretPassword!");

        return new Object[][]{{dataMap}};
    }

//    static Stream<Arguments> loginDataPass(){
//        return Stream.of(
//                Arguments.of("tomsmith", "SuperSecretPassword!")
//        );
//    }
//
//    static Stream<Arguments> loginDataFail() {
//        return Stream.of(
//                Arguments.of("tomsmith", "wrongpassword")
//        );
//    }
}
