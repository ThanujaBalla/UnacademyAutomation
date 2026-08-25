package Base;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import Utilities.ConfigReader;
import Utilities.DriverManager;
import Utilities.ScreenShotUtility;
import Utilities.TestResultManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {

	@Before
	public void setUp(Scenario scenario) {

		String browser = ConfigReader.getProperty("browser");

		if (scenario.getSourceTagNames().contains("@chrome")) {

			browser = "chrome";

		} else if (scenario.getSourceTagNames().contains("@edge")) {

			browser = "edge";
		}

		WebDriver driver;

		if ("chrome".equalsIgnoreCase(browser)) {

			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();

		} else if ("edge".equalsIgnoreCase(browser)) {

			WebDriverManager.edgedriver().setup();

			driver = new EdgeDriver();

		} else {

			throw new RuntimeException("Unsupported browser: " + browser);
		}

		driver.manage().window().maximize();

		DriverManager.setDriver(driver);

		driver.get(ConfigReader.getProperty("url"));

		System.out.println("Scenario started: " + scenario.getName());
	}

	@After
	public void tearDown(Scenario scenario) {

		WebDriver driver = DriverManager.getDriver();

		String status;

		if (scenario.isFailed()) {

			status = "FAIL";

		} else {

			status = "PASS";
		}

		String screenshotPath = null;

		try {

			screenshotPath = ScreenShotUtility.captureScreenshot(driver, scenario.getName());

		} catch (IOException e) {

			System.out.println("Screenshot failed: " + e.getMessage());
		}

		/*
		 * Store Cucumber result in JSON.
		 */
		String relativeScreenshotPath = "Screenshots" + File.separator + new File(screenshotPath).getName();
		TestResultManager.updateResult(scenario.getName(), status, relativeScreenshotPath);

		DriverManager.quitDriver();

		System.out.println("Cucumber result stored: " + scenario.getName() + " -> " + status);
	}
}