package Reports;

import java.io.File;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Utilities.ConfigReader;
import Utilities.TestResultManager;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getExtentReports() {

        if (extent == null) {

            String reportPath =
                    System.getProperty("user.dir")
                    + File.separator
                    + "Reports"
                    + File.separator
                    + "ExtentTestReports.html";

            ExtentSparkReporter reporter =
                    new ExtentSparkReporter(reportPath);

            reporter.config()
                    .setReportName(
                            "Unacademy Automation Test Report"
                    );

            reporter.config()
                    .setDocumentTitle(
                            "Unacademy Test Execution Report"
                    );

            extent = new ExtentReports();

            extent.attachReporter(reporter);

            extent.setSystemInfo(
                    "Tester",
                    "Thanuja"
            );

            extent.setSystemInfo(
                    "Application",
                    "Unacademy"
            );

            extent.setSystemInfo(
                    "Framework",
                    "Selenium + Cucumber + TestNG"
            );

            extent.setSystemInfo(
                    "Environment",
                    "QA"
            );

            extent.setSystemInfo(
                    "Browser",
                    ConfigReader.getProperty("browser")
            );

            /*
             * Load previous test results
             * from test-results.json
             */
            loadPreviousResults();
        }

        return extent;
    }

    private static void loadPreviousResults() {

        Map<String, Map<String, String>> results =
                TestResultManager.getAllResults();

        for (Map.Entry<String, Map<String, String>> entry
                : results.entrySet()) {

            String testName = entry.getKey();

            Map<String, String> testData =
                    entry.getValue();

            String status =
                    testData.get("status");

            String screenshot =
                    testData.get("screenshot");

            ExtentTest test =
                    extent.createTest(testName);

            if ("PASS".equalsIgnoreCase(status)) {

                test.pass(
                        "Latest result: PASS"
                );

            } else if ("FAIL".equalsIgnoreCase(status)) {

                test.fail(
                        "Latest result: FAIL"
                );

            } else {

                test.skip(
                        "Latest result: SKIP"
                );
            }

            /*
             * Add latest screenshot
             */
            if (screenshot != null) {

                try {

                    String fileName =
                            new File(screenshot)
                            .getName();

                    String relativePath =
                            ".."
                            + File.separator
                            + "Screenshots"
                            + File.separator
                            + fileName;

                    test.addScreenCaptureFromPath(
                            relativePath,
                            "Latest Screenshot"
                    );

                } catch (Exception e) {

                    test.info(
                            "Screenshot could not be attached."
                    );
                }
            }
        }
    }
    
    public static void generateCumulativeReport() {

        // Create a fresh ExtentReports instance
        ExtentReports cumulativeExtent = new ExtentReports();

        String reportPath =
                System.getProperty("user.dir")
                + File.separator
                + "Reports"
                + File.separator
                + "ExtentTestReports.html";

        ExtentSparkReporter reporter =
                new ExtentSparkReporter(reportPath);

        reporter.config().setReportName(
                "Unacademy Automation Test Report"
        );

        reporter.config().setDocumentTitle(
                "Unacademy Cumulative Test Report"
        );

        cumulativeExtent.attachReporter(reporter);

        cumulativeExtent.setSystemInfo(
                "Tester",
                "Thanuja"
        );

        cumulativeExtent.setSystemInfo(
                "Application",
                "Unacademy"
        );

        cumulativeExtent.setSystemInfo(
                "Framework",
                "Selenium + Cucumber + TestNG"
        );

        cumulativeExtent.setSystemInfo(
                "Environment",
                "QA"
        );

        Map<String, Map<String, String>> results =
                TestResultManager.getAllResults();

        for (Map.Entry<String, Map<String, String>> entry
                : results.entrySet()) {

            String testName = entry.getKey();

            Map<String, String> testData =
                    entry.getValue();

            String status =
                    testData.get("status");

            String screenshot =
                    testData.get("screenshot");

            ExtentTest test =
                    cumulativeExtent.createTest(testName);

            if ("PASS".equalsIgnoreCase(status)) {

                test.pass("Latest result: PASS");

            } else if ("FAIL".equalsIgnoreCase(status)) {

                test.fail("Latest result: FAIL");

            } else {

                test.skip("Latest result: SKIP");
            }

            if (screenshot != null) {

                try {

                    String fileName =
                            new File(screenshot).getName();

                    String relativePath =
                            ".."
                            + File.separator
                            + "Screenshots"
                            + File.separator
                            + fileName;

                    test.addScreenCaptureFromPath(
                            relativePath,
                            "Latest Screenshot"
                    );

                } catch (Exception e) {

                    test.info(
                            "Screenshot could not be attached."
                    );
                }
            }
        }

        cumulativeExtent.flush();

        System.out.println(
                "Cumulative Extent Report generated successfully."
        );
    }

    public static void flushReport() {

        if (extent != null) {

            extent.flush();
        }
    }
}