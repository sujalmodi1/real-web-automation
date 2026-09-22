package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle = By.className("title");
    private final By sortDropdown = By.className("product_sort_container");
    private final By productNames = By.className("inventory_item_name");
    private final By productPrices = By.className("inventory_item_price");
    private final By cartIcon = By.className("shopping_cart_link");
    private final By cartBadge = By.className("shopping_cart_badge");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isProductsPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText().equals("Products");
    }

    public void sortBy(String value) {
        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown)));
        select.selectByValue(value);
    }

    public List<String> getProductNames() {
        List<WebElement> elements = 
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNames));
        List<String> names = new ArrayList<>();
        for(WebElement element : elements) {
                names.add(element.getText());
        }
        return names;
    }

    public List<Double> getProductPrices() {
        List<WebElement> elements = 
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productPrices));
        List<Double> prices = new ArrayList<>();
        for(WebElement element : elements) {
            String priceText = element.getText();
            priceText = priceText.replace("$", "");
            double price = Double.parseDouble(priceText);
            prices.add(price);
        }
        return prices;
    }

    public void addProductToCart(String productId) {
        By addButton = By.id("add-to-cart-" + productId);
        By removeButton = By.id("remove-" + productId);
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(removeButton));
    }

    public void removeProductFromCart(String productId) {
        By removeButton = By.id("remove-" + productId);
        wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
    }

    public int getCartBadgeCount() {
        if (driver.findElements(cartBadge).isEmpty()) {
            return 0;
        }
        return Integer.parseInt(driver.findElement(cartBadge).getText());
    }

    public void clickCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }
}
