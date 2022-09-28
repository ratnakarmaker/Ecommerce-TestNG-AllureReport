package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {
    @FindBy(className = "login")
    public
    WebElement linkLogin;
    @FindBy(id = "email")
    WebElement txtEmail;
    @FindBy(id = "passwd")
    WebElement txtPassword;
    @FindBy(id = "SubmitLogin")
    public  WebElement btnSubmit;
    @FindBy(className = "logout")
    public
    WebElement linkLogout;
    @FindBy(xpath = "//li[contains(text(),'Authentication failed.')]")
    WebElement lblAuthError;
    @FindBy(xpath = "//li[contains(text(),'Invalid email address.')]")
    WebElement InvalidError;
    @FindBy(xpath = "//li[contains(text(),'An email address required.')]")
    WebElement emailRequiredError;
    @FindBy(xpath = "//li[contains(text(),'Password is required.')]")
    WebElement passRequiredError;
    @FindBy(xpath = "//a[contains(text(),'Forgot your password?')]")
    public
    WebElement forgetPass;
    @FindBy(css = "[type=submit]")
    public
    List<WebElement> retrievePass;
    @FindBy(css = "[title='Back to Login']")
    public
    WebElement backToLog;




    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean doLoginWithValidCreds(String email, String password) {
        linkLogin.click();
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnSubmit.click();
        return linkLogout.isDisplayed();
    }

    public String doLoginWithInvalidPass(String email, String password) {
        linkLogin.click();
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnSubmit.click();
        return lblAuthError.getText();
    }

    public String doLoginWithInvalidEmail(String email, String password) {
        linkLogin.click();
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnSubmit.click();
        return lblAuthError.getText();
    }

    public String doLoginWithInvalidCreds(String email, String password) {
        linkLogin.click();
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnSubmit.click();
        return InvalidError.getText();
    }

    public String doLoginWithBlankEmail(String email, String password) {
        linkLogin.click();
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnSubmit.click();
        return emailRequiredError.getText();
    }

    public String doLoginWithBlankPass(String email, String password) {
        linkLogin.click();
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnSubmit.click();
        return passRequiredError.getText();
    }

    public String doLoginWithBlankCreds(String email, String password) {
        linkLogin.click();
        txtEmail.sendKeys(email);
        txtPassword.sendKeys(password);
        btnSubmit.click();
        return emailRequiredError.getText();
    }

    public boolean visibleForgetPass() {
       return forgetPass.isDisplayed();
    }
    public boolean retrivePassVisible() {
       forgetPass.click();
       return retrievePass.get(1).isDisplayed();
    }
    public void retrivePass(String email) {
       txtEmail.sendKeys(email);
       retrievePass.get(1).click();
       backToLog.click();

    }




}
