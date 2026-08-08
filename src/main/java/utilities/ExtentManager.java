package utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extentReports;
    private static ExtentSparkReporter sparkReporter;

    public static ExtentReports getExtentReports() {
        if (extentReports == null) {
            LocalDateTime currentTime = LocalDateTime.now();
    
            String timeStamp = currentTime.format(
                DateTimeFormatter.ofPattern(
                    "yyyy-MM-dd_HH-mm-ss"));
    
            sparkReporter = new ExtentSparkReporter(
                "reports/ExtentReport_"
                + timeStamp
                + ".html");
    
            extentReports = new ExtentReports();
    
            extentReports.attachReporter(sparkReporter);

            sparkReporter.config()
                .setDocumentTitle("Sauce Automation Report");
            sparkReporter.config()
                .setReportName("Regression Test Report");

            extentReports.setSystemInfo("Operating System", "Windows 10");
            extentReports.setSystemInfo("Tester", "Sujal");
            extentReports.setSystemInfo("Browser", "Chrome");
        }        

        return extentReports;
    }
}
