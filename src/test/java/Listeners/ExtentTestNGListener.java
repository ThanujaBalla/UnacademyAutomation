package Listeners;

import org.testng.IExecutionListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import Reports.CumulativeReportGenerator;

public class ExtentTestNGListener
        implements ITestListener, IExecutionListener {

    @Override
    public void onExecutionStart() {

        System.out.println(
                "======================================"
        );

        System.out.println(
                "TestNG execution started."
        );

        System.out.println(
                "======================================"
        );
    }

    @Override
    public void onExecutionFinish() {

        System.out.println(
                "======================================"
        );

        System.out.println(
                "Generating final cumulative Extent report..."
        );

        CumulativeReportGenerator.generateReport();

        System.out.println(
                "======================================"
        );
    }

    /*
     * These methods are intentionally empty.
     *
     * We do NOT create Extent entries here.
     * TestResultManager + JSON is the source of truth.
     */

    @Override
    public void onTestStart(ITestResult result) {

        System.out.println(
                "Test started: "
                + result.getTestClass()
                        .getRealClass()
                        .getSimpleName()
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        System.out.println(
                "Test passed: "
                + result.getTestClass()
                        .getRealClass()
                        .getSimpleName()
        );
    }

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println(
                "Test failed: "
                + result.getTestClass()
                        .getRealClass()
                        .getSimpleName()
        );
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        System.out.println(
                "Test skipped: "
                + result.getTestClass()
                        .getRealClass()
                        .getSimpleName()
        );
    }
}