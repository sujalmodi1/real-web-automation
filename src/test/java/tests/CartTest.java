package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutInformationPage;
import pages.LoginPage;
import pages.ProductsPage;

public class CartTest extends BaseTest {
    @Test
    public void verifyProductIsDisplayedInCart() {
        // Arrange (create LoginPage object)
        LoginPage loginPage = new LoginPage(driver);
        // Act (login to the application)
        loginPage.login("standard_user", "secret_sauce");
        // Arrange again (create ProductsPage object)
        ProductsPage productsPage = new ProductsPage(driver);
        // Act (add a product to cart)
        productsPage.addProductToCart("sauce-labs-backpack");
        // Act (navigate to the cart page)
        productsPage.clickCart();
        // Arrange again (create CartPage object)
        CartPage cartPage = new CartPage(driver);
        // Assert (verify the product is displayed in the cart)
        Assert.assertTrue(
            cartPage.isProductDisplayedInCart(
                "Sauce Labs Backpack"), 
            "Product should be displayed in the cart.");
    }

    @Test
    public void verifyProductQuantity() {
	    //Arrange
	    LoginPage loginPage = new LoginPage(driver);
	    //Act
	    loginPage.login("standard_user", "secret_sauce");
	    //Arrange again
	    ProductsPage productsPage = new ProductsPage(driver);
	    //Act
	    productsPage.addProductToCart("sauce-labs-backpack");
	    productsPage.clickCart();
	    //Arrange again
	    CartPage cartPage = new CartPage(driver);
	    //Assert
	    Assert.assertEquals(cartPage.getProductQuantity(
            "Sauce Labs Backpack"), 
            "1", 				
            "Product quantity should be 1 after adding the product to the cart");
    }
    
    @Test
    public void verifyProductPrice() {
 	    //Arrange
	    LoginPage loginPage = new LoginPage(driver);
	    //Act
	    loginPage.login("standard_user", "secret_sauce");
	    //Arrange
	    ProductsPage productsPage = new ProductsPage(driver);
	    //Act
	    productsPage.addProductToCart("sauce-labs-backpack");
	    productsPage.clickCart();
	    //Arrange
	    CartPage cartPage = new CartPage(driver);
	    //Assert
	    Assert.assertEquals(cartPage.getProductPrice(
            "Sauce Labs Backpack"), 
            "$29.99", 
            "The product price should be $29.99.");
    }

    @Test
    public void verifyRemoveProductFromCart() {
        // Arrange (create LoginPage object)
        LoginPage loginPage = new LoginPage(driver);
        // Act (login to the application)
        loginPage.login("standard_user", "secret_sauce");
        // Arrange again (create ProductsPage object)
        ProductsPage productsPage = new ProductsPage(driver);
        // Act (add a product to cart)
        productsPage.addProductToCart("sauce-labs-backpack");
        productsPage.clickCart();
        // Arrange again (create CartPage object)
        CartPage cartPage = new CartPage(driver);
        //Assert (pre-condition)
	    Assert.assertTrue(cartPage.isProductPresentInCart(
            "Sauce Labs Backpack"), 
            "The product should be present in the cart.");
	    //Act
	    cartPage.removeProductFromCart("Sauce Labs Backpack");
	    //Assert (post-condition)
	    Assert.assertFalse(cartPage.isProductPresentInCart(
            "Sauce Labs Backpack"), 
            "The product should no longer be present in the cart.");
    }

    @Test
    public void verifyContinueShoppingNavigation() {
	    //Arrange
	    LoginPage loginPage = new LoginPage(driver);
	    //Act
	    loginPage.login("standard_user", "secret_sauce");
	    //Arrange
	    ProductsPage productsPage = new ProductsPage(driver);
	    //Act
	    productsPage.clickCart();
	    //Arrange
	    CartPage cartPage = new CartPage(driver);
	    //Act
	    cartPage.clickContinueShoppingButton();
	    //Assert
	    Assert.assertTrue(productsPage.isProductsPageDisplayed(), 
        "Products page should be displayed.");
    }

    @Test   
    public void verifyCheckoutNavigation() {
	    //Arrange
	    LoginPage loginPage = new LoginPage(driver);
	    //Act
	    loginPage.login("standard_user", "secret_sauce");
	    //Arrange
	    ProductsPage productsPage = new ProductsPage(driver);
	    //Act
	    productsPage.clickCart();
	    //Arrange
	    CartPage cartPage = new CartPage(driver);
	    //Act
	    cartPage.clickCheckoutButton();
	    //Arrange
	    CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(driver); 
	    //Assert
	    Assert.assertTrue(checkoutInformationPage.isCheckoutInformationPageDisplayed(),
        "Checkout: Your Information page should be displayed.");
    } 

}
