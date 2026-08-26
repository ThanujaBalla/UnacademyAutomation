package Reports;

import java.io.File;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Utilities.TestResultManager;

public class CumulativeReportGenerator {

	public static void generateReport() {

		System.out.println("Generating final cumulative Extent report...");

		ExtentReports extent = ExtentManager.createReport();

		Map<String, Map<String, String>> results = TestResultManager.getAllResults();

		for (Map.Entry<String, Map<String, String>> entry : results.entrySet()) {

			String testName = entry.getKey();

			Map<String, String> testData = entry.getValue();

			String status = testData.get("status");

			String screenshot = testData.get("screenshot");

			String lastRun = testData.get("lastRun");

			/*
			 * ONE Extent entry per test name.
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

				try {

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

		extent.flush();

		System.out.println("Cumulative Extent Report generated successfully.");
	}
}