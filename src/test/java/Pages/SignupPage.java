package Pages;

import Utils.Utils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SignupPage {
    @FindBy(name = "email_create")
    WebElement txtRegEmail;
    @FindBy(css = "[type=submit]")
    List<WebElement> btnSignup;
    @FindBy(id = "id_gender1")
    WebElement rb1;
    @FindBy(id = "customer_firstname")
    public WebElement txtFirstName;
    @FindBy(id = "customer_lastname")
    WebElement txtLastName;
    @FindBy(id = "passwd")
    WebElement txtPassword;
    @FindBy(id = "days")
    WebElement cbDays;
    @FindBy(id = "months")
    WebElement cbMonths;
    @FindBy(id = "years")
    WebElement cbYears;
    @FindBy(id = "address1")
    WebElement txtAddress1;
    @FindBy(id = "city")
    WebElement txtCity;
    @FindBy(id = "id_state")
    WebElement cbState;
    @FindBy(id = "postcode")
    WebElement txtPostCode;
    @FindBy(id = "phone_mobile")
    WebElement txtMobile;
    @FindBy(id = "submitAccount")
    public WebElement regButton;
    @FindBy(className = "account")
    public WebElement userAccountName;
    @FindBy(className = "page-heading")
    public WebElement pageHeading;
    @FindBy(css = "[title=Home]")
    public WebElement btnHome;
    @FindBy(className = "logout")
    public WebElement btnLogOut;
    @FindBy(xpath = "//li[contains(text(),'An account using this email address has already be')]")
    public WebElement msgAlreadyRegistered;
    @FindBy(xpath = "//li[contains(text(),'You must register at least one phone number.')]")
    public WebElement OnePhoneNumber;
    @FindBy(xpath = "//li[contains(text(),' is invalid.')]")
    public List<WebElement> errorMessages;
    @FindBy(xpath = "//li[contains(text(),\"The Zip/Postal code you've entered is invalid. It \")]")
    public WebElement invalidZipCodeMsg;
    @FindBy(xpath = "//h1[contains(text(),'Create an account')]")
    public WebElement txtcreateAccount;
    @FindBy(xpath = "//p[contains(text(),'There are 8 errors')]")
    public WebElement totalErrors;
    @FindBy(xpath = "//li[contains(text(),' is invalid')]")
    public List<WebElement> errorPassmsg;
    @FindBy(xpath = "//h1[contains(text(),'My account')]")
    public WebElement signUpSuccesful;
    WebDriver driver;



    public SignupPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    Utils utils = new Utils();
    String password = utils.generateRandomPassword(8);
    String email;
    String mobile;
    public String firstName = "Test" + ((char) utils.generateRandomNumber(65, 123));
    public void validEmailRegisterForm(String email) throws InterruptedException {
        Thread.sleep(1000);
        txtRegEmail.clear();
        txtRegEmail.sendKeys(email);
        this.email = email;
        btnSignup.get(1).click();
        Thread.sleep(1200);
    }

    public String alreadyRegisteredEmail(String email) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.linkLogin.click();
        Thread.sleep(1000);
        txtRegEmail.sendKeys(email);
        btnSignup.get(1).click();
        Thread.sleep(2000);
        return msgAlreadyRegistered.getText();
    }
//
    public void EmptyInfoReg(){

        regButton.click();
    }
//
    public List<String> invalidFirstandLastName() throws InterruptedException {
        rb1.click();
        Thread.sleep(1000);
        txtFirstName.sendKeys("Test" + (utils.generateRandomNumber(1, 1000)));
        Thread.sleep(1000);
        txtLastName.sendKeys("User" + (utils.generateRandomNumber(1, 1000)));
        Thread.sleep(1200);
        regButton.click();
        List<String> invalidName = new ArrayList<>();
        invalidName.add(errorMessages.get(1).getText());
        invalidName.add(errorMessages.get(0).getText());
        Thread.sleep(1000);
        txtFirstName.clear();
        Thread.sleep(1000);
        txtLastName.clear();
        Thread.sleep(1000);
        txtFirstName.sendKeys(firstName);
        Thread.sleep(1000);
        txtLastName.sendKeys("user");
        return invalidName;
    }
    public String invalidPasswordCheck() throws InterruptedException {
        Thread.sleep(500);
        txtPassword.sendKeys("5469");
        Thread.sleep(1000);
        regButton.click();
        Thread.sleep(1200);
        String invalidPasswordMessage = errorPassmsg.get(0).getText();
        //rectifying password
        Thread.sleep(1300);
        return invalidPasswordMessage;
    }
    public String invalidZipCode() throws InterruptedException {
        Thread.sleep(1000);
        //giving valid password
        txtPassword.sendKeys(password);
        Thread.sleep(1000);
        //giving address
        txtAddress1.sendKeys("Dhamrai,Dhaka");
        Thread.sleep(1000);
        //giving city
        txtCity.sendKeys("Dhaka");
        Thread.sleep(1000);
        //selecting states
        Select state = new Select(cbState);
        state.selectByValue("27");
        Thread.sleep(1000);
        txtPostCode.sendKeys(Integer.toString(utils.generateRandomNumber(100000, 50000000)));
        Thread.sleep(1000);
        regButton.click();
        //rectifying zip code
        Thread.sleep(1000);
        String invalidZipCodeMessage = invalidZipCodeMsg.getText();
        Thread.sleep(1000);
        txtPostCode.clear();
        Thread.sleep(1000);
        txtPostCode.sendKeys("0001" + utils.generateRandomNumber(1, 10));
        Thread.sleep(1000);
        return invalidZipCodeMessage;
    }

    public String OneMobileNum(){
        regButton.click();
        String mobNumError= OnePhoneNumber.getText();
//        txtMobile.sendKeys(mobile);

        return mobNumError;
    }
    public String finalSignUp(String mobile){
        txtPassword.sendKeys(password);
        txtMobile.sendKeys(mobile);
        regButton.click();
        String signUpmsg = signUpSuccesful.getText();
        return signUpmsg;

    }

}
