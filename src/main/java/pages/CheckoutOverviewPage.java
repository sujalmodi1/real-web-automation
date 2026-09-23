package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutOverviewPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle = By.className("title");
    private final By finishButton = By.id("finish");
    private final By cancelButton = By.id("cancel");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isCheckoutOverviewPageDisplayed() {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(pageTitle, "Checkout: Overview"));
    }

    public void clickFinishButton() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public void clickCancelButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
    }

    public boolean isProductDisplayed(String productName) {
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
}
