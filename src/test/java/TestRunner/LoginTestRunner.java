package TestRunner;

import Pages.LoginPage;
import Utils.Utils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.io.IOException;

public class LoginTestRunner extends Base.Setup {
    LoginPage loginPage;
    Utils utils;
    @Test(priority = 10)
    public void doLoginWithValidCreds() throws IOException, ParseException {
        loginPage=new LoginPage(driver);
        utils =new Utils();
        utils.getUserCreds(utils.getUserCount());
        driver.get("http://automationpractice.com/");
        boolean isLogoutFound= loginPage.doLoginWithValidCreds(utils.getEmail(),utils.getPassword());
        Assert.assertEquals(isLogoutFound,true);
        //loginPage.linkLogout.click();
    }
    @Test(priority = 1)
    public void doLoginWithInvalidPass() throws IOException, ParseException {
        driver.get("http://automationpractice.com/");
        loginPage=new LoginPage(driver);
        utils=new Utils();
        utils.getUserCreds(1);
        String validationMessage= loginPage.doLoginWithInvalidPass(utils.getEmail(),utils.getPassword());
        Assert.assertTrue(validationMessage.contains("failed"));
    }
    @Test(priority = 3)
    public void doLoginWithInvalidCreds() throws IOException, ParseException {
        //driver.get("http://automationpractice.com/");
        loginPage=new LoginPage(driver);
        utils=new Utils();
        utils.getUserCreds(3);
        String validationMessage= loginPage.doLoginWithInvalidCreds(utils.getEmail(), utils.getPassword());
        Assert.assertTrue(validationMessage.contains("Invalid"));
    }
    @Test(priority = 4)
    public void doLoginWithBlankEmail() throws IOException, ParseException {
        //driver.get("http://automationpractice.com/");
        loginPage = new LoginPage(driver);
        utils = new Utils();
        utils.getUserCreds(5);
        String validationMessage = loginPage.doLoginWithBlankEmail("", utils.getPassword());
        Assert.assertTrue(validationMessage.contains("required"));
    }
    @Test(priority = 5)
    public void doLoginWithBlankPass() throws IOException, ParseException {
        //driver.get("http://automationpractice.com/");
        loginPage = new LoginPage(driver);
        utils = new Utils();
       utils.getUserCreds(6);
        String validationMessage = loginPage.doLoginWithBlankPass(utils.getEmail(), "");
        Assert.assertTrue(validationMessage.contains("required"));
    }
    @Test(priority = 6)
    public void doLoginWithBlankCreds() throws IOException, ParseException {
        //driver.get("http://automationpractice.com/");
        loginPage = new LoginPage(driver);
        utils = new Utils();
        //utils.getUserCreds(2);
        String validationMessage = loginPage.doLoginWithBlankCreds(" ", " ");
        Assert.assertTrue(validationMessage.contains("required"));
    }
    @Test(priority = 7)
    public void visibleForgetPass() throws IOException, ParseException {
        //driver.get("http://automationpractice.com/");
        loginPage = new LoginPage(driver);
        boolean forgetPassVisibility= loginPage.visibleForgetPass();
        Assert.assertTrue(forgetPassVisibility);
        Assert.assertTrue(loginPage.forgetPass.isEnabled());
    }
    @Test(priority = 8)
    public void retrivePassVisible() throws IOException, ParseException {
        //driver.get("http://automationpractice.com/");
        loginPage = new LoginPage(driver);
        boolean retrievePassVisibility= loginPage.retrivePassVisible();
        Assert.assertTrue(retrievePassVisibility);
        Assert.assertTrue(loginPage.retrievePass.get(1).isEnabled());
    }
    @Test(priority = 9)
    public void retrievePass() throws IOException, ParseException {
        //driver.get("http://automationpractice.com/");
        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.backToLog.isDisplayed());
        utils = new Utils();
        utils.getUserCreds(1);
       loginPage.retrivePass(utils.getEmail());
        Assert.assertTrue(loginPage.retrievePass.get(1).isEnabled());
        Assert.assertTrue(loginPage.linkLogin.isDisplayed());
    }










}
