package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentListener implements ITestListener {
    private static ExtentReports extentReports;
    private static ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        extentReports = ExtentManager.getExtentReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extentReports.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass(result.getName() + " passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getName() + " failed");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
    
}
