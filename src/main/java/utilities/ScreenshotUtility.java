package utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtility {
    public static void captureScreenshot(WebDriver driver, String testName) {
        
        // Source file
        File sourceFile = ((TakesScreenshot) driver)
            .getScreenshotAs(OutputType.FILE);

        // Timestamp generation
        LocalDateTime currentTime = LocalDateTime.now();

        String timeStamp = currentTime.format(
            DateTimeFormatter.ofPattern(
                "yyyy-MM-dd_HH-mm-ss"));

        // Destination file
        File destinationFile =
                new File(
                    "screenshots/" 
                    + testName
                    + "-"
                    + timeStamp
                    + ".png");

        // Copy the file
        try {
            FileUtils.copyFile(
                sourceFile,
                destinationFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
