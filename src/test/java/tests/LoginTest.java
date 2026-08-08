package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginTest extends BaseTest {
    @Test
    public void verifyValidLogin() {

        // Arrange (create LoginPage object)
        LoginPage loginPage = new LoginPage(driver);
        // Act (Perform Login)
        loginPage.login("standard_user", "secret_sauce");
        // Arrange again (create ProductsPage object)
        ProductsPage productsPage = new ProductsPage(driver);
        // Assert (verify the login)
        Assert.assertTrue(productsPage.isProductsPageDisplayed(), "Products page is not displayed after login.");
    }

    @Test
    public void verifyInvalidUsername() {

        // Arrange (create LoginPage object)
        LoginPage loginPage = new LoginPage(driver);
        // Act (Attempt Invalid Username login)
        loginPage.login("invalid_user", "secret_sauce");
        // Assert (Instead of checking the products page, verify the error message)
        Assert.assertEquals(
            loginPage.getErrorMessage(),
            "Epic sadface: Username and password do not match any user in this service",
            "Incorrect error message displayed."
        );
    }

    @Test
    public void verifyInvalidPassword() {

        // Arrange (create LoginPage object)
        LoginPage loginPage = new LoginPage(driver);
        // Act (Attempt Invalid password login)
        loginPage.login("standard_user", "invalid_password");
        // Assert (Instead of checking the products page, verify the error message)
        Assert.assertEquals(
            loginPage.getErrorMessage(),
            "Epic sadface: Username and password do not match any user in this service",
            "Incorrect error message displayed."
        );
    }

    @Test
    public void verifyEmptyUsername() {

        // Arrange (create LoginPage object)
        LoginPage loginPage = new LoginPage(driver);
        // Act (Attempt on empty username login)
        loginPage.login("", "secret_sauce");
        // Assert (Instead of checking the products page, verify the error message)
        Assert.assertEquals(
            loginPage.getErrorMessage(),
            "Epic sadface: Username is required",
            "Incorrect error message displayed."
        );
    }

    @Test
    public void verifyEmptyPassword() {

        //  Arrange (create LoginPage object)
        LoginPage loginPage = new LoginPage(driver);
        // Act (Attempt on empty password login)
        loginPage.login("standard_user", "");
        // Assert (Instead of checking the products page, verify the error message)
        Assert.assertEquals(
            loginPage.getErrorMessage(),
            "Epic sadface: Password is required",
            "Incorrect error message displayed."
        );
    }

    @Test
    public void verifyLockedOutUser() {

        // Arrange (create LoginPage object)
        LoginPage loginPage = new LoginPage(driver);
        // Act (Attempt on locked out user login)
        loginPage.login("locked_out_user", "secret_sauce");
        // Assert (Instead of checking the products page, verify the error message)
        Assert.assertEquals(
            loginPage.getErrorMessage(),
            "Epic sadface: Sorry, this user has been locked out.",
            "Incorrect error message displayed."

        );
    }
}
