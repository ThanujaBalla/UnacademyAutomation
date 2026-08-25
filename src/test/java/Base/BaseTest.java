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

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

import Listeners.ExtentTestNGListener;
import Utilities.ConfigReader;
import Utilities.DriverManager;
import Utilities.ScreenShotUtility;
import Utilities.TestResultManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import Utilities.ConfigReader;

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
        String engine = ConfigReader.getProperty("engine");

        if ("playwright".equalsIgnoreCase(engine)) {
            setupPlaywright();
        } else {
            setupSelenium();
        }
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
        BrowserType.LaunchOptions launch = new BrowserType.LaunchOptions()
                .setHeadless(ConfigReader.bool("headless"))
                .setSlowMo(Double.parseDouble(ConfigReader.getProperty("slowMo")));
        
        String b = ConfigReader.getProperty("browser").toLowerCase();
        BrowserType type = playwright.chromium();
        if ("chrome".equals(b)) launch.setChannel("chrome");
        else if ("edge".equals(b)) launch.setChannel("msedge");

        browser = type.launch(launch);
        Browser.NewContextOptions options = new Browser.NewContextOptions().setViewportSize(1440, 900);
        String authState = ConfigReader.getProperty("authState");
        if (authState != null && !authState.isBlank() && Files.exists(Paths.get(authState))) {
            options.setStorageStatePath(Paths.get(authState));
        }
        
        context = browser.newContext(options);
        context.setDefaultTimeout(ConfigReader.integer("timeoutMs"));
        page = context.newPage();
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true).setSnapshots(true).setSources(true));
        System.out.println("Playwright Engine started successfully.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        String testName = result.getTestClass().getRealClass().getSimpleName() + "_" +
                result.getName().replaceAll("[^a-zA-Z0-9._-]", "_");

        // Handle Playwright Cleanup & Artifacts
        if (page != null) {
            Path artifactDir = Paths.get("target", "artifacts");
            try { Files.createDirectories(artifactDir); } catch (Exception ignored) {}

            if (!result.isSuccess()) {
                try {
                    page.screenshot(new Page.ScreenshotOptions()
                            .setPath(artifactDir.resolve(testName + ".png"))
                            .setFullPage(true));
                } catch (Exception ignored) {}
            }
            if (context != null) {
                try {
                    context.tracing().stop(new Tracing.StopOptions()
                            .setPath(artifactDir.resolve(testName + ".zip")));
                } catch (Exception ignored) {}
                context.close();
            }
            if (browser != null) browser.close();
            if (playwright != null) playwright.close();
        }

        // Handle Selenium Cleanup & Reporting
        if (driver != null) {
            try {
                String screenshotPath = ScreenShotUtility.captureScreenshot(driver, testName);
                String status = (result.getStatus() == ITestResult.SUCCESS) ? "PASS" :
                                (result.getStatus() == ITestResult.FAILURE) ? "FAIL" : "SKIP";

                String relativeScreenshotPath = "Screenshots" + File.separator + new File(screenshotPath).getName();
                TestResultManager.updateResult(testName, status, relativeScreenshotPath);

                ExtentTest extentTest = ExtentTestNGListener.getTest();
                if (extentTest != null) {
                    String relativePath = ".." + File.separator + "Screenshots" + File.separator + new File(screenshotPath).getName();
                    extentTest.addScreenCaptureFromPath(relativePath, "Test Screenshot");
                }
            } catch (IOException e) {
                System.out.println("Screenshot/Report error: " + e.getMessage());
            } finally {
                System.out.println("Closing Selenium Driver...");
                DriverManager.quitDriver();
                System.out.println("Browser closed.");
            }
        }
    }
}