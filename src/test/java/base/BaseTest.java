package base;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utilities.ScreenshotUtility;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Create a download directory inside the Jenkins/project workspace
        String downloadPath = System.getProperty("user.dir")
                + File.separator + "target"
                + File.separator + "downloads";

        try {
            Files.createDirectories(Paths.get(downloadPath));

            // Delete old PDF files before each test
            Path downloadDirectory = Paths.get(downloadPath);

            Files.list(downloadDirectory)
                    .filter(path -> path.getFileName()
                            .toString()
                            .startsWith("swag-labs-order-"))
                    .filter(path -> path.getFileName()
                            .toString()
                            .endsWith(".pdf"))
                    .forEach(path -> {
                        try {
                            Files.deleteIfExists(path);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

        } catch (Exception e) {
            throw new RuntimeException(
                    "Could not prepare download directory", e);
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.setExperimentalOption(
            "prefs",
            Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_leak_detection", false,
                "download.default_directory", downloadPath,
                "download.prompt_for_download", false,
                "download.directory_upgrade", true,
                "plugins.always_open_pdf_externally", true));

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                ScreenshotUtility.captureScreenshot(
                    driver,
                    result.getName());
            }
            driver.quit();
        }
    }

}
