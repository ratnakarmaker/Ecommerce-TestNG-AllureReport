package TestRunner;

import Base.Setup;
import Pages.LoginPage;
import Pages.SignupPage;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class SignupTestRunner extends Setup {
    //positive test case
    Utils utils=new Utils();
    String email;
    String password;
    String mobile;
    public void requiredData(){
        String emailRandom="testman"+utils.generateRandomNumber(100,10000);
        email=emailRandom+"@test.com";
        password=utils.generateRandomPassword(8);
        mobile="175"+utils.generateRandomNumber(100000,9999999);
    }
    @Test(priority=1,description = "Cannot Create Account with already registered email")
    public void alreadyRegisteredEmail() throws IOException, ParseException, InterruptedException {
        driver.get("http://automationpractice.com/");
        SignupPage signupPage=new SignupPage(driver);
        utils.getUserCreds(0);
        String errorAlreadyregisteredMsg=signupPage.alreadyRegisteredEmail(utils.getEmail());
        Assert.assertTrue(errorAlreadyregisteredMsg.contains("An account using this email address has already been registered."));

    }
    @Test(priority = 2,description = "After giving valid email,user will be redirecrted to registration form")
    public void validEmailRegisterForm() throws InterruptedException {
        SignupPage signupPage=new SignupPage(driver);
        requiredData();
        signupPage.validEmailRegisterForm(email);
        //after logging with valid email
        Assert.assertTrue(signupPage.txtcreateAccount.getText().contains("CREATE AN ACCOUNT"));
    }

    @Test(priority =3,description = "User press register button without inputting any data")
    public void EmptyInfoReg() throws InterruptedException {
        SignupPage signupPage=new SignupPage(driver);
        signupPage.EmptyInfoReg();
        Assert.assertTrue(signupPage.totalErrors.getText().equals("There are 8 errors"));
    }
    @Test(priority = 4)
    public void invalidFirstandLastName() throws InterruptedException {
        SignupPage signupPage=new SignupPage(driver);
        List<String> invalidName=signupPage.invalidFirstandLastName();
        String invalidFirstName=invalidName.get(0);
        String invalidLastName=invalidName.get(1);
        Assert.assertTrue(invalidFirstName.contains("firstname is invalid"));
        Assert.assertTrue(invalidLastName.contains("lastname is invalid"));

    }
    @Test(priority = 5)
    public void invalidPasswordCheck() throws InterruptedException {
        SignupPage signupPage=new SignupPage(driver);
        Assert.assertTrue(signupPage.invalidPasswordCheck().contains("passwd is invalid."));
    }
    @Test(priority=6)
    public void invalidZipCode() throws InterruptedException {
        SignupPage signupPage=new SignupPage(driver);
        String errorZipCode=signupPage.invalidZipCode();
        Assert.assertTrue(errorZipCode.contains("The Zip/Postal code you've entered is invalid. It must follow this format: 00000"));
    }
    @Test(priority = 7)
    public void OneMobileNum() throws InterruptedException, IOException, ParseException {
        SignupPage signupPage=new SignupPage(driver);
        String moberrormsg= signupPage.OneMobileNum();
        Assert.assertTrue(moberrormsg.contains("at least one phone number"));
    }

    @Test(priority = 8)
    public void finalSignUp(){
        SignupPage signupPage=new SignupPage(driver);
        String succssfulMsg = signupPage.finalSignUp(mobile);
        Assert.assertTrue(succssfulMsg.contains("MY ACCOUNT"));

    }


}