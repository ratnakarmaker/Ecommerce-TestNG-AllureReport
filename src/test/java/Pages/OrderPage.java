package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class OrderPage {
    @FindBy(id="search_query_top")
    WebElement txtSearch;
    @FindBy(name="submit_search")
    WebElement btnSearch;
    //@FindBy(xpath = "//p[contains(text(),'No results were found ')]")
    @FindBy(className = "alert")
    WebElement invalidSearchMsg;
    @FindBy(xpath = "//p[contains(text(),'Please enter a search keyword')]")
    WebElement emptySearchmsg;
    @FindBy(className = "heading-counter")
    WebElement lblResult;
    @FindBy(tagName = "img")
    List<WebElement> imgProduct;
    @FindBy(xpath = "//span[contains(text(),'Add to cart')]")
    WebElement btnAddCart;
    @FindBy(css = "[title='Proceed to checkout']")
    WebElement btnCheckout;
    @FindBy(tagName = "i")
    List <WebElement> iconClick;
    @FindBy(className = "page-heading")
    WebElement lblSummaryPage;
    WebDriver driver;

    public OrderPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String searchProduct(String product){
        txtSearch.clear();
        txtSearch.sendKeys(product);
        btnSearch.click();
        return lblResult.getText();

    }
    public String invalidSearch(String product){
        txtSearch.clear();
        txtSearch.sendKeys(product);
        btnSearch.click();
        String invalidAearchError= invalidSearchMsg.getText();
        return invalidAearchError;
    }
//    public String emptySearch(String product){
//        txtSearch.clear();
//        txtSearch.sendKeys(product);
//        String emptySearcherrormsg= emptySearchmsg.getText();
//        return emptySearcherrormsg;
//    }


    public Boolean chooseProduct(){
        imgProduct.get(14).click();
        return btnAddCart.isDisplayed();
    }
    public Boolean addToCart(){
        btnAddCart.click();
        return iconClick.get(2).isEnabled();
    }
    public String checkProductSumary(){
        btnCheckout.click();
        return lblSummaryPage.getText();

    }
}
