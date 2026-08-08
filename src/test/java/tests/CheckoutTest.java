package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutCompletePage;
import pages.CheckoutInformationPage;
import pages.CheckoutOverviewPage;
import pages.LoginPage;
import pages.ProductsPage;
import utilities.PdfUtility;

public class CheckoutTest extends BaseTest {
    @Test
    public void verifyCheckoutInformationPageNavigation() {
        // Arrange
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutInformationPage checkoutInformationPage =
            new CheckoutInformationPage(driver);

        // Act
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProductToCart("sauce-labs-backpack");
        productsPage.clickCart();
        cartPage.clickCheckoutButton();

        // Assert
        Assert.assertTrue(
            checkoutInformationPage
                    .isCheckoutInformationPageDisplayed(),
            "Checkout information page should be displayed.");
    }

    @Test
    public void verifyCheckoutOverviewPageNavigation() {
        // Arrange
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutInformationPage checkoutInformationPage =
            new CheckoutInformationPage(driver);
        CheckoutOverviewPage checkoutOverviewPage =
            new CheckoutOverviewPage(driver);

        // Act
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProductToCart("sauce-labs-backpack");
        productsPage.clickCart();
        cartPage.clickCheckoutButton();

        checkoutInformationPage.fillCheckoutInformation(
            "John",
            "Wick",
            "560076");

        checkoutInformationPage.clickContinueButton();

        // Assert
        Assert.assertTrue(
            checkoutOverviewPage
                    .isCheckoutOverviewPageDisplayed(),
            "Checkout overview page should be displayed.");
    }

    @Test
    public void verifyProductInformationOnOverviewPage() {
        // Arrange
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutInformationPage checkoutInformationPage =
            new CheckoutInformationPage(driver);
        CheckoutOverviewPage checkoutOverviewPage =
            new CheckoutOverviewPage(driver);
    
        String productName = "Sauce Labs Backpack";
        String expectedQuantity = "1";
        String expectedPrice = "$29.99";

        // Act
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProductToCart("sauce-labs-backpack");
        productsPage.clickCart();
        cartPage.clickCheckoutButton();
        checkoutInformationPage.fillCheckoutInformation(
            "John",
            "Wick",
            "560076");
    
        checkoutInformationPage.clickContinueButton();

        // Assert
        Assert.assertTrue(
            checkoutOverviewPage.isProductDisplayed(productName),
            "Product should be displayed.");

        Assert.assertEquals(
            checkoutOverviewPage.getProductQuantity(productName),
            expectedQuantity,
            "Product quantity is incorrect.");

        Assert.assertEquals(
            checkoutOverviewPage.getProductPrice(productName),
            expectedPrice,
            "Product price is incorrect.");
    }

    @Test
    public void verifyFinishCheckout() {
        // Arrange
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutInformationPage checkoutInformationPage =
            new CheckoutInformationPage(driver);
        CheckoutOverviewPage checkoutOverviewPage =
            new CheckoutOverviewPage(driver);
        CheckoutCompletePage checkoutCompletePage =
            new CheckoutCompletePage(driver);
        // Act
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProductToCart("sauce-labs-backpack");
        productsPage.clickCart();
        cartPage.clickCheckoutButton();
        checkoutInformationPage.fillCheckoutInformation(
            "John",
            "Wick",
            "560076");
    
        checkoutInformationPage.clickContinueButton();
        checkoutOverviewPage.clickFinishButton();

        // Assert
        Assert.assertTrue(
            checkoutCompletePage.isCheckoutCompletePageDisplayed(),
            "Checkout complete page should be displayed.");
    }

    @Test
    public void verifyBackHomeNavigation() {
        // Arrange
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutInformationPage checkoutInformationPage =
            new CheckoutInformationPage(driver);
        CheckoutOverviewPage checkoutOverviewPage =
            new CheckoutOverviewPage(driver);
        CheckoutCompletePage checkoutCompletePage = 
            new CheckoutCompletePage(driver);

        // Act
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProductToCart("sauce-labs-backpack");
        productsPage.clickCart();
        cartPage.clickCheckoutButton();
        checkoutInformationPage.fillCheckoutInformation(
            "John",
            "Wick",
            "560076");

        checkoutInformationPage.clickContinueButton();
        checkoutOverviewPage.clickFinishButton();
        checkoutCompletePage.clickBackHomeButton();

        // Assert
        Assert.assertTrue(
            productsPage.isProductsPageDisplayed(),
            "Products page should be displayed.");
    }

    @Test
    public void verifyPdfDownload() {
        // Arrange
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutInformationPage checkoutInformationPage =
            new CheckoutInformationPage(driver);
        CheckoutOverviewPage checkoutOverviewPage =
            new CheckoutOverviewPage(driver);
        CheckoutCompletePage checkoutCompletePage =
            new CheckoutCompletePage(driver);

        // Act
        loginPage.login("standard_user", "secret_sauce");
        productPage.addProductToCart("sauce-labs-backpack");
        productPage.clickCart();
        cartPage.clickCheckoutButton();
        checkoutInformationPage.fillCheckoutInformation(
            "John",
            "Wick",
            "560076");

        checkoutInformationPage.clickContinueButton();
        checkoutOverviewPage.clickFinishButton();
        checkoutCompletePage.clickGeneratePdfButton();

        // Assert
        Assert.assertTrue(
            PdfUtility.isPdfDownloaded(),
            "PDF should be downloaded.");
    }

}
