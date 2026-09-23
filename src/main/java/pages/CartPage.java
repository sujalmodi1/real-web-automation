package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle = By.className("title");
    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait (driver, Duration.ofSeconds(10));
    }

    public boolean isCartPageDisplayed() {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(pageTitle, "Your Cart"));
    }

    public void clickCheckoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public boolean isProductDisplayedInCart(String productName) {
        By productLocator = By.xpath("//div[@class='inventory_item_name' and text()='"
            + productName + "']");
        
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator)).isDisplayed();
    }

    public String getProductQuantity(String productName) {
        By quantityLocator = By.xpath("//div[@class='inventory_item_name' and text()='" 
            + productName
            + "']/ancestor::div[@class='cart_item']"
            + "//div[@class='cart_quantity']");
        
        return wait.until(ExpectedConditions.visibilityOfElementLocated(quantityLocator)).getText();
    }

    public String getProductPrice(String productName) {
        By priceLocator = By.xpath("//div[@class='inventory_item_name' and text()='"
            + productName
            + "']/ancestor::div[@class='cart_item']"
            + "//div[@class='inventory_item_price']");
        
        return wait.until(ExpectedConditions.visibilityOfElementLocated(priceLocator)).getText();
    }

    public void removeProductFromCart(String productName) {
        By removeButton = By.xpath("//div[@class='inventory_item_name' and text()='"
            + productName
            + "']/ancestor::div[@class='cart_item']"
            + "//button[contains(@id,'remove')]");
        
        wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
    }

    public boolean isProductPresentInCart(String productName) {
            By productLocator = By.xpath("//div[@class='inventory_item_name' and text()='"
                + productName + "']");

            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(productLocator)
            ).isDisplayed();
    }

    public boolean isProductRemovedFromCart(String productName) {
            By productLocator = By.xpath("//div[@class='inventory_item_name' and text()='"
                + productName + "']");

            return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(productLocator));
    }

    public void clickContinueShoppingButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton)).click();
    }
}
