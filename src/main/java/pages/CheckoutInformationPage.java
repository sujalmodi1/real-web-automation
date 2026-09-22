package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutInformationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By pageTitle = By.className("title");
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By cancelButton = By.id("cancel");

    public CheckoutInformationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public boolean isCheckoutInformationPageDisplayed() {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle, "Checkout: Your Information"));
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeField)).sendKeys(postalCode);
    }

    
    public void fillCheckoutInformation(String firstName,String lastName,String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }
    
    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }
    
    public void clickCancelButton() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
    }
}
