package Listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

import Reports.CumulativeReportGenerator;
import Reports.ExtentManager;

public class ExtentTestNGListener
        implements ITestListener {

    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        String testName =
                result.getTestClass()
                      .getRealClass()
                      .getSimpleName();

        /*
         * Create a new Extent entry for the
         * currently executing test.
         *
         * This will be the latest execution.
         */
        ExtentTest extentTest =
                ExtentManager.getExtentReports()
                        .createTest(testName);

        test.set(extentTest);

        extentTest.info(
                "Test execution started."
        );
    }

    @Override
    public void onTestSuccess(
            ITestResult result) {

        test.get().pass(
                "Test passed successfully."
        );
    }

    @Override
    public void onTestFailure(
            ITestResult result) {

        test.get().fail(
                result.getThrowable()
        );
    }

    @Override
    public void onTestSkipped(
            ITestResult result) {

        test.get().skip(
                "Test skipped."
        );
    }

    @Override
    public void onFinish(ITestContext context) {
        CumulativeReportGenerator.generateReport();
    }

    public static ExtentTest getTest() {

        return test.get();
    }
}