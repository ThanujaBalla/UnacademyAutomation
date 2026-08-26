package Base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;


import Utilities.ConfigReader;
import Utilities.DriverManager;
import Utilities.ScreenShotUtility;
import Utilities.TestResultManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BaseTest {

	// Selenium Engine Objects
	protected WebDriver driver;

	// Playwright Engine Objects
	protected Playwright playwright;
	protected Browser browser;
	protected BrowserContext context;
	protected Page page;

	@BeforeMethod(alwaysRun = true)
	public void setUp() {

		if ("playwright".equalsIgnoreCase(getEngine())) {
			setupPlaywright();
		} else {
			setupSelenium();
		}
	}

	protected String getEngine() {
		return "selenium";
	}

	private void setupSelenium() {
		System.out.println("Starting Selenium Driver...");
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		DriverManager.setDriver(driver);
		System.out.println("Selenium Driver started successfully.");
	}

	private void setupPlaywright() {
		System.out.println("Starting Playwright Engine...");
		playwright = Playwright.create();
		BrowserType.LaunchOptions launch = new BrowserType.LaunchOptions().setHeadless(ConfigReader.bool("headless"))
				.setSlowMo(Double.parseDouble(ConfigReader.getProperty("slowMo")));

		String b = ConfigReader.getProperty("browser").toLowerCase();
		BrowserType type = playwright.chromium();
		if ("chrome".equals(b))
			launch.setChannel("chrome");
		else if ("edge".equals(b))
			launch.setChannel("msedge");

		browser = type.launch(launch);
		Browser.NewContextOptions options = new Browser.NewContextOptions().setViewportSize(1440, 900);
		String authState = ConfigReader.getProperty("authState");
		if (authState != null && !authState.isBlank() && Files.exists(Paths.get(authState))) {
			options.setStorageStatePath(Paths.get(authState));
		}

		context = browser.newContext(options);
		context.setDefaultTimeout(ConfigReader.integer("timeoutMs"));
		page = context.newPage();
		context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
		System.out.println("Playwright Engine started successfully.");
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {

		String testName = result.getTestClass().getRealClass().getSimpleName();

		String status;

		if (result.getStatus() == ITestResult.SUCCESS) {

			status = "PASS";

		} else if (result.getStatus() == ITestResult.FAILURE) {

			status = "FAIL";

		} else {

			status = "SKIP";
		}

		/*
		 * ============================ PLAYWRIGHT ============================
		 */

		if (page != null) {

			Path artifactDir = Paths.get("target", "artifacts");

			try {
				Files.createDirectories(artifactDir);
			} catch (Exception ignored) {
			}

			String screenshotPath = null;

			if (!result.isSuccess()) {

				try {

					Path screenshot = artifactDir.resolve(testName + ".png");

					page.screenshot(new Page.ScreenshotOptions().setPath(screenshot).setFullPage(true));

					screenshotPath = screenshot.toString();

				} catch (Exception e) {

					System.out.println("Playwright screenshot failed: " + e.getMessage());
				}
			}

			/*
			 * Save Playwright result to JSON
			 */
			TestResultManager.updateResult(testName, status, screenshotPath);

			/*
			 * Stop tracing
			 */
			try {

				if (context != null) {

					context.tracing().stop(new Tracing.StopOptions().setPath(artifactDir.resolve(testName + ".zip")));
				}

			} catch (Exception ignored) {
			}

			try {

				if (context != null) {
					context.close();
				}

				if (browser != null) {
					browser.close();
				}
				if (playwright != null) {
					playwright.close();
				}
			} catch (Exception ignored) {
			}
		}

		/*
		 * ============================ SELENIUM ============================
		 */

		if (driver != null) {
			try {
				String screenshotPath = ScreenShotUtility.captureScreenshot(driver, testName);
				String relativeScreenshotPath = "Screenshots" + File.separator + new File(screenshotPath).getName();
				TestResultManager.updateResult(testName, status, relativeScreenshotPath);
			} catch (IOException e) {
				System.out.println("Screenshot/Report error: " + e.getMessage());
			} finally {
				DriverManager.quitDriver();
			}
		}

	}
}