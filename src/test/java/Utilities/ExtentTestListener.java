package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Reports.ExtentManager;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class ExtentTestListener implements ITestListener {
    private static final ExtentReports EXTENT = ExtentManager.getInstance();
    private static final ThreadLocal<ExtentTest> CURRENT = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        String name = result.getTestClass().getRealClass().getSimpleName()
                + " - " + result.getMethod().getMethodName();
        CURRENT.set(EXTENT.createTest(name));
        CURRENT.get().info("Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        CURRENT.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest t = CURRENT.get();
        if (result.getThrowable() != null) t.fail(result.getThrowable());
        File screenshot = new File("target/artifacts/" + safe(result.getTestClass().getRealClass().getSimpleName()
                + "_" + result.getMethod().getMethodName()) + ".png");
        if (screenshot.exists()) {
            try { t.addScreenCaptureFromPath(screenshot.getAbsolutePath()); } catch (Exception ignored) {}
        }
        t.fail("Test Failed");
    }

  
    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTest t = CURRENT.get();

        if (t == null) {
            return;
        }

        if (result.getThrowable() != null) {
            t.skip(result.getThrowable());
        } else {
            t.skip("Test Skipped");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        EXTENT.flush();
        CURRENT.remove();
    }

    private static String safe(String value) {
        return value.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}
