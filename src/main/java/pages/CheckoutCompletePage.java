package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutCompletePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle = By.className("title");
    private final By completeHeader = By.className("complete-header");
    private final By completeText = By.className("complete-text");
    private final By backHomeButton = By.id("back-to-products");
    private final By generatePdfButton = By.id("generate-pdf-order");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isCheckoutCompletePageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText().equals("Checkout: Complete!");
    }

    public String getCompleteHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
    }

    public String getCompleteText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeText)).getText();
    }

    public void clickBackHomeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(backHomeButton)).click();
    }

    public void clickGeneratePdfButton() {
        wait.until(ExpectedConditions.elementToBeClickable(generatePdfButton)).click();
    }
}
