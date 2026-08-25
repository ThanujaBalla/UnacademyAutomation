package Reports;

import java.io.File;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Utilities.TestResultManager;

public class CumulativeReportGenerator {

	public static void generateReport() {

		String reportPath = System.getProperty("user.dir") + File.separator + "Reports" + File.separator
				+ "ExtentTestReports.html";

		/*
		 * Create a completely fresh report.
		 */
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setReportName("Unacademy Automation Test Report");
		reporter.config().setDocumentTitle("Unacademy Test Execution Report");
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Unacademy Testing Team");
		extent.setSystemInfo("Application", "Unacademy");
		extent.setSystemInfo("Framework", "Selenium + Cucumber + TestNG");
		extent.setSystemInfo("Environment", "QA");

		/*
		 * Read the latest result of every test from test-results.json.
		 */
		Map<String, Map<String, String>> results = TestResultManager.getAllResults();

		for (Map.Entry<String, Map<String, String>> entry : results.entrySet()) {
			String testName = entry.getKey();
			Map<String, String> testData = entry.getValue();
			String status = testData.get("status");
			String screenshot = testData.get("screenshot");
			String lastRun = testData.get("lastRun");

			/*
			 * Create exactly ONE Extent entry for each test name.
			 */
			ExtentTest test = extent.createTest(testName);

			/*
			 * Latest status
			 */
			if ("PASS".equalsIgnoreCase(status)) {
				test.pass("Latest result: PASS");
			} else if ("FAIL".equalsIgnoreCase(status)) {
				test.fail("Latest result: FAIL");
			} else {
				test.skip("Latest result: SKIP");
			}

			/*
			 * Latest screenshot
			 */
			if (screenshot != null && !screenshot.trim().isEmpty()) {
				try{
					String fileName = new File(screenshot).getName();
					String relativePath = ".." + File.separator + "Screenshots" + File.separator + fileName;
					test.addScreenCaptureFromPath(relativePath, "Latest Screenshot");
				} catch (Exception e) {
					test.info("Screenshot could not be attached: " + e.getMessage());
				}
			}

			/*
			 * Last execution time
			 */
			if (lastRun != null) {
				test.info("Last Run: " + lastRun);
			}
		}

		/*
		 * Write the final report.
		 */
		extent.flush();

		System.out.println("Cumulative Extent Report generated successfully.");
	}
}