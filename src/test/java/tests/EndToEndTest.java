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

public class EndToEndTest extends BaseTest {
    @Test
    public void verifyCompletePurchaseFLow() {
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

        String firstProductId = "sauce-labs-backpack";
        String secondProductId = "sauce-labs-bike-light";

        String firstProductName = "Sauce Labs Backpack";
        String secondProductName = "Sauce Labs Bike Light";

        // Execute the complete purchase flow
        loginPage.login("standard_user", "secret_sauce");
        productsPage.sortBy("lohi");
        productsPage.addProductToCart(firstProductId);
        productsPage.addProductToCart(secondProductId);
        productsPage.clickCart();

        // Verify cart contents
        Assert.assertTrue(
            cartPage.isProductPresentInCart(firstProductName),
            "First product " + firstProductName + " should be present in the cart");
        Assert.assertTrue(
            cartPage.isProductPresentInCart(secondProductName),
            "Second product " + secondProductName + " should be present in the cart");
        
        // Proceed to checkout
        cartPage.clickCheckoutButton();

        // Fill out the checkout information
        checkoutInformationPage.fillCheckoutInformation(
            "John",
            "Wick",
            "560076");

        checkoutInformationPage.clickContinueButton();

        // Verify overview page
        Assert.assertTrue(
            checkoutOverviewPage.isCheckoutOverviewPageDisplayed(),
            "Checkout overview page should be displayed.");

        // Verify product information
        Assert.assertTrue(
            checkoutOverviewPage.isProductDisplayed(firstProductName),
            "First product " + firstProductName + " should be displayed in the checkout overview.");
        Assert.assertTrue(
            checkoutOverviewPage.isProductDisplayed(secondProductName),
            "Second product " + secondProductName + " should be displayed in the overview page.");

        // Finish checkout
        checkoutOverviewPage.clickFinishButton();

        // Verify completion page
        Assert.assertTrue(
            checkoutCompletePage.isCheckoutCompletePageDisplayed(),
            "Checkout complete page should be displayed.");

        // Verify success message
        Assert.assertEquals(
            checkoutCompletePage.getCompleteHeader(),
            "Thank you for your order!",
            "Success message should be displayed.");

        // Verify success text
        Assert.assertEquals(
            checkoutCompletePage.getCompleteText(),
            "Your order has been dispatched, and will arrive "
            + "just as fast as the pony can get there!",
            "The success text is incorrect.");

        // Generate PDF
        checkoutCompletePage.clickGeneratePdfButton();

        // Verify the PDF is downloaded
        Assert.assertTrue(
            PdfUtility.isPdfDownloaded(),
            "PDF should be downloaded");

        // Head Home
        checkoutCompletePage.clickBackHomeButton();

        //Verify product page
        Assert.assertTrue(
            productsPage.isProductsPageDisplayed(),
            "Products page should be displayed.");
    }    
}
