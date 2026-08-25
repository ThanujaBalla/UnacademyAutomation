package Reports;

import java.io.File;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Utilities.ConfigReader;
import Utilities.TestResultManager;

public final class ExtentManager {

    private static ExtentReports extent;

    private ExtentManager() {
        // Private constructor to prevent instantiation
    }

    /**
     * Singleton getter for ExtentReports. 
     * Initializes reporter and loads previous execution results.
     */
    public static synchronized ExtentReports getExtentReports() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir")
                    + File.separator + "Reports"
                    + File.separator + "ExtentTestReports.html";

            File reportFile = new File(reportPath);
            File parentDir = reportFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setReportName("Unacademy Automation Test Report");
            reporter.config().setDocumentTitle("Unacademy Test Execution Report");
            reporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            // System Information
            extent.setSystemInfo("Project", "Unacademy Automation");
            extent.setSystemInfo("Application", "Unacademy");
            extent.setSystemInfo("Framework", "Java + Selenium/Playwright + TestNG");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
            extent.setSystemInfo("Base URL", ConfigReader.getProperty("baseUrl"));
            extent.setSystemInfo("Payment Mode", ConfigReader.getProperty("paymentMode"));

            // Load stored execution results from JSON
            loadPreviousResults();
        }
        return extent;
    }

    /**
     * Alias method for compatibility with existing framework calls.
     */
    public static synchronized ExtentReports getInstance() {
        return getExtentReports();
    }

    /**
     * Reads saved test execution details and screenshots from TestResultManager.
     */
    private static void loadPreviousResults() {
        Map<String, Map<String, String>> results = TestResultManager.getAllResults();

        if (results == null || results.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Map<String, String>> entry : results.entrySet()) {
            String testName = entry.getKey();
            Map<String, String> testData = entry.getValue();

            String status = testData.get("status");
            String screenshot = testData.get("screenshot");

            ExtentTest test = extent.createTest(testName);

            if ("PASS".equalsIgnoreCase(status)) {
                test.pass("Latest result: PASS");
            } else if ("FAIL".equalsIgnoreCase(status)) {
                test.fail("Latest result: FAIL");
            } else {
                test.skip("Latest result: SKIP");
            }

            if (screenshot != null && !screenshot.isEmpty()) {
                try {
                    String fileName = new File(screenshot).getName();
                    String relativePath = ".." + File.separator + "Screenshots" + File.separator + fileName;

                    test.addScreenCaptureFromPath(relativePath, "Latest Screenshot");
                } catch (Exception e) {
                    test.info("Screenshot could not be attached: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Generates a separate standalone cumulative report.
     */
    public static void generateCumulativeReport() {
        ExtentReports cumulativeExtent = new ExtentReports();

        String reportPath = System.getProperty("user.dir")
                + File.separator + "Reports"
                + File.separator + "ExtentTestReports.html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Unacademy Automation Test Report");
        reporter.config().setDocumentTitle("Unacademy Cumulative Test Report");
        reporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

        cumulativeExtent.attachReporter(reporter);

        cumulativeExtent.setSystemInfo("Application", "Unacademy");
        cumulativeExtent.setSystemInfo("Framework", "Java + Selenium/Playwright + TestNG");
        cumulativeExtent.setSystemInfo("Environment", "QA");
        cumulativeExtent.setSystemInfo("Browser", ConfigReader.getProperty("browser"));

        Map<String, Map<String, String>> results = TestResultManager.getAllResults();

        if (results != null) {
            for (Map.Entry<String, Map<String, String>> entry : results.entrySet()) {
                String testName = entry.getKey();
                Map<String, String> testData = entry.getValue();

                String status = testData.get("status");
                String screenshot = testData.get("screenshot");

                ExtentTest test = cumulativeExtent.createTest(testName);

                if ("PASS".equalsIgnoreCase(status)) {
                    test.pass("Latest result: PASS");
                } else if ("FAIL".equalsIgnoreCase(status)) {
                    test.fail("Latest result: FAIL");
                } else {
                    test.skip("Latest result: SKIP");
                }

                if (screenshot != null && !screenshot.isEmpty()) {
                    try {
                        String fileName = new File(screenshot).getName();
                        String relativePath = ".." + File.separator + "Screenshots" + File.separator + fileName;
                        test.addScreenCaptureFromPath(relativePath, "Latest Screenshot");
                    } catch (Exception e) {
                        test.info("Screenshot could not be attached.");
                    }
                }
            }
        }

        cumulativeExtent.flush();
        System.out.println("Cumulative Extent Report generated successfully.");
    }

    /**
     * Flushes active ExtentReports instance to output file.
     */
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}