package Reports;

import java.io.File;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Utilities.TestResultManager;

public class CumulativeReportGenerator {

    public static void generateReport() {

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
                "Unacademy Test Execution Report"
        );

        ExtentReports extent = new ExtentReports();

        extent.attachReporter(reporter);

        extent.setSystemInfo("Tester", "Thanuja");
        extent.setSystemInfo("Application", "Unacademy");
        extent.setSystemInfo(
                "Framework",
                "Selenium + Cucumber + TestNG"
        );
        extent.setSystemInfo("Environment", "QA");

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

                test.pass("Latest result: PASS");

            } else if ("FAIL".equalsIgnoreCase(status)) {

                test.fail("Latest result: FAIL");

            } else {

                test.skip("Latest result: SKIP");
            }

            if (screenshot != null) {

                try {

                    String screenshotFile =
                            new File(screenshot).getName();

                    String relativePath =
                            ".."
                            + File.separator
                            + "Screenshots"
                            + File.separator
                            + screenshotFile;

                    test.addScreenCaptureFromPath(
                            relativePath,
                            "Latest Screenshot"
                    );

                } catch (Exception e) {

                    test.info(
                            "Screenshot could not be attached: "
                            + e.getMessage()
                    );
                }
            }

            String lastRun =
                    testData.get("lastRun");

            if (lastRun != null) {

                test.info("Last Run: " + lastRun);
            }
        }

        extent.flush();

        System.out.println(
                "Cumulative Extent Report generated successfully."
        );
    }
}