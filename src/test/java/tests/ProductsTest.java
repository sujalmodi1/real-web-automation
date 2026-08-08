package tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

public class ProductsTest extends BaseTest {
    @Test
    public void verifyAddSingleProductToCart() {
        //Arrange (create LoginPage object)
        LoginPage loginPage = new LoginPage(driver);
        //Act (login to the application)
        loginPage.login("standard_user", "secret_sauce");
        // Arrange again (create ProductsPage object)
        ProductsPage productsPage = new ProductsPage(driver);
        //Act (add a product to cart)
        productsPage.addProductToCart("sauce-labs-backpack");
        // Assert (verify the product was added to the cart)
        Assert.assertEquals(productsPage.getCartBadgeCount(), 1, "Cart badge count should be 1 after adding a product to the cart.");
    }

    @Test
    public void verifyAddMultipleProductsToCart() {
        //Arrange (create LoginPage object)
        LoginPage loginPage = new LoginPage(driver);
        //Act (login to the application)
        loginPage.login("standard_user", "secret_sauce");
        // Arrange again (create ProductsPage object)
        ProductsPage productsPage = new ProductsPage(driver);
        //Act (add multiple products to cart)
        productsPage.addProductToCart("sauce-labs-backpack");
        // System.out.println(productsPage.getCartBadgeCount());
        productsPage.addProductToCart("sauce-labs-bike-light");
        // Assert (verify the products were added to the cart)
        Assert.assertEquals(productsPage.getCartBadgeCount(), 2, "Cart badge count should be 2 after adding two products to the cart.");
    }

    @Test
    public void verifySortByPriceLowToHigh() {
	    //Arrange
	    LoginPage loginPage = new LoginPage(driver);
	    //Act
	    loginPage.login("standard_user", "secret_sauce");
	    //Arrange
	    ProductsPage productsPage = new ProductsPage(driver);
	    productsPage.sortBy("lohi");
	    //Get the Displayed prices
	    List<Double> actualPrices = productsPage.getProductPrices();
	    //Create a copy
	    List<Double> expectedPrices = new ArrayList<>(actualPrices);
	    //sort the copy
	    Collections.sort(expectedPrices);
	    //Assert
	    Assert.assertEquals(actualPrices, expectedPrices, "Products should be sorted from 	low to high");
    }

    @Test
    public void verifySortByPriceHighToLow() {
	    //Arrange
	    LoginPage loginPage = new LoginPage(driver);
	    //Act
	    loginPage.login("standard_user", "secret_sauce");
	    //Arrange
	    ProductsPage productsPage = new ProductsPage(driver);
	    //Act
	    productsPage.sortBy("hilo");
	    //Get the Displayed prices
	    List<Double> actualPrices = productsPage.getProductPrices();
	    //Create a copy
	    List<Double> expectedPrices = new ArrayList<>(actualPrices);
	    Collections.sort(expectedPrices, Collections.reverseOrder());
	    //Assert
	    Assert.assertEquals(actualPrices, expectedPrices, "Products should be sorted from 		high to low");
    }

    @Test
        public void verifySortByNameAToZ() {
	    //Arrange
	    LoginPage loginPage = new LoginPage(driver);
	    //Act
	    loginPage.login("standard_user", "secret_sauce");
	    //Arrange
	    ProductsPage productsPage = new ProductsPage(driver);
	    //Act
	    productsPage.sortBy("az");
	    //Prepare expected data
	    List<String> actualOrder = productsPage.getProductNames();
	    List<String> expectedOrder = new ArrayList<>(actualOrder);
	    Collections.sort(expectedOrder);
	    //Assert
	    Assert.assertEquals(actualOrder, expectedOrder, "Products should be sorted by name 		from A to Z.");
    }

    @Test
        public void verifySortByNameZToA() {
	    //Arrange
	    LoginPage loginPage = new LoginPage(driver);
	    //Act
	    loginPage.login("standard_user", "secret_sauce");
	    //Arrange
	    ProductsPage productsPage = new ProductsPage(driver);
	    //Act
	    productsPage.sortBy("za");
	    //Prepare expected data
	    List<String> actualOrder = productsPage.getProductNames();
	    List<String> expectedOrder = new ArrayList<>(actualOrder);
	    Collections.sort(expectedOrder, Collections.reverseOrder());
	    //Assert
	    Assert.assertEquals(actualOrder, expectedOrder, "Products should be sorted by name 		from Z to A.");
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
        productsPage.addProductToCart("sauce-labs-bike-light");
        // Act (remove the product from cart)
        productsPage.removeProductFromCart("sauce-labs-backpack");
        // Assert (verify the product was removed from the cart)
        Assert.assertEquals(productsPage.getCartBadgeCount(), 1, "Cart badge count should be 1 after removing one product from the cart.");
    }

    @Test
    public void verifyNavigateToCartPage() {
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
        // Assert (verify the cart page is displayed)
        Assert.assertEquals(cartPage.isCartPageDisplayed(), true, "Cart page should be displayed after clicking the cart icon.");
    }
}
