package Listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

import Reports.CumulativeReportGenerator;
import Reports.ExtentManager;

public class ExtentTestNGListener implements ITestListener {

    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        Class<?> testClass =
                result.getTestClass().getRealClass();

        /*
         * Do not create Extent entries for Cucumber
         * TestRunner classes.
         */
        if (testClass.getPackageName().equals("Runners")) {
            return;
        }

        String testName =
                testClass.getSimpleName();

        ExtentTest extentTest =
                ExtentManager.getExtentReports()
                        .createTest(testName);

        test.set(extentTest);

        extentTest.info(
                "Test execution started."
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        Class<?> testClass =
                result.getTestClass().getRealClass();

        if (testClass.getPackageName().equals("Runners")) {
            return;
        }

        if (test.get() != null) {
            test.get().pass(
                    "Test passed successfully."
            );
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {

        Class<?> testClass =
                result.getTestClass().getRealClass();

        if (testClass.getPackageName().equals("Runners")) {
            return;
        }

        if (test.get() != null) {
            test.get().fail(
                    result.getThrowable()
            );
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        Class<?> testClass =
                result.getTestClass().getRealClass();

        if (testClass.getPackageName().equals("Runners")) {
            return;
        }

        if (test.get() != null) {
            test.get().skip(
                    "Test skipped."
            );
        }
    }

    @Override
    public void onFinish(ITestContext context) {

        /*
         * First close the current Extent report.
         */
        ExtentManager.flushReport();

        /*
         * Then generate the final report from
         * test-results.json.
         */
        CumulativeReportGenerator.generateReport();
    }

    public static ExtentTest getTest() {
        return test.get();
    }
}