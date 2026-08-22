package Utilities;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestResultManager {

	private static final String RESULT_FILE = System.getProperty("user.dir") + File.separator + "test-results"
			+ File.separator + "test-results.json";

	private static final ObjectMapper mapper = new ObjectMapper();

	/**
	 * Update or add the latest result of a test case.
	 */
	public static synchronized void updateResult(String testName, String status, String screenshotPath) {

		try {

			File file = new File(RESULT_FILE);
			System.out.println("RESULT FILE PATH: " + file.getAbsolutePath());
			// Create directory if it doesn't exist
			File parent = file.getParentFile();

			if (!parent.exists()) {
				parent.mkdirs();
			}

			Map<String, Map<String, String>> results;
			// Read existing results
			if (file.exists() && file.length() > 0) {

				results = mapper.readValue(file, new TypeReference<Map<String, Map<String, String>>>() {
				});
			} else {
				results = new LinkedHashMap<>();
			}

			// Create latest result
			Map<String, String> testResult = new LinkedHashMap<>();
			testResult.put("status", status);
			testResult.put("lastRun", java.time.LocalDateTime.now().toString());
			if (screenshotPath != null) {
				testResult.put("screenshot", screenshotPath);
			}

			// ADD new test or UPDATE existing test
			results.put(testName, testResult);

			// Write back to JSON
			mapper.writerWithDefaultPrettyPrinter().writeValue(file, results);
			System.out.println("Result stored for: " + testName + " → " + status);
		} catch (IOException e) {
			System.out.println("Unable to update test result: " + e.getMessage());
		}
	}

	/**
	 * Read all stored test results.
	 */
	public static synchronized Map<String, Map<String, String>> getAllResults() {

		try {

			File file = new File(RESULT_FILE);

			if (!file.exists() || file.length() == 0) {

				return new LinkedHashMap<>();
			}

			return mapper.readValue(file, new TypeReference<Map<String, Map<String, String>>>() {
			});

		} catch (IOException e) {

			System.out.println("Unable to read test results: " + e.getMessage());

			return new LinkedHashMap<>();
		}
	}
	
	
	
}