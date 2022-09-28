package TestRunner;

import Base.Setup;
import Pages.OrderPage;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.ws.soap.Addressing;

public class OrderTestRunner extends Setup {
    OrderPage orderPage;
    @Test(priority = 3,description = "User can search product")
    public void searchProduct(){
        //driver.get("http://automationpractice.com/");
        orderPage=new OrderPage(driver);
        String res= orderPage.searchProduct("dress");
        Assert.assertTrue(res.contains("results have been found"));
        Allure.description("User can search product successfully");
    }
    @Test(priority = 2)
    public void InvalidSearchProduct(){
        //driver.get("http://automationpractice.com/");
        orderPage=new OrderPage(driver);
        String invalidSearch= orderPage.invalidSearch("kihggd");
        Assert.assertTrue(invalidSearch.contains("No results were found"));
        //Allure.description("User can search product successfully");
    }
    @Test(priority = 1)
    public void emptySearch(){
        driver.get("http://automationpractice.com/");
        orderPage=new OrderPage(driver);
        String emptySearcherror= orderPage.invalidSearch("");
        Assert.assertTrue(emptySearcherror.contains("search keyword"));
        //Allure.description("User can search product successfully");
    }

    @Test(priority = 4, description = "User can choose product")
    public void chooseProduct(){
        orderPage=new OrderPage(driver);
        boolean found= orderPage.chooseProduct();
        Assert.assertTrue(found);
        Allure.description("User can choose product from the product list");
    }
    @Test(priority = 5, description = "User can add cart successfully")
    public void addToCart(){
        orderPage=new OrderPage(driver);
        boolean found= orderPage.addToCart();
        Assert.assertTrue(found);
        Allure.description("User added the product into card");
    }
    @Test(priority = 6, description = "User can see product summary")
    public void checkProductSumary(){
        orderPage=new OrderPage(driver);
        String summaryText= orderPage.checkProductSumary();
        Assert.assertTrue(summaryText.contains("SHOPPING-CART SUMMARY"));
        Allure.description("User can view product summary before checkout");
    }
}
