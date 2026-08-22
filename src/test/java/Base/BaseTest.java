package Base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentTest;

import Listeners.ExtentTestNGListener;
import Utilities.DriverManager;
import Utilities.ScreenShotUtility;
import Utilities.TestResultManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        System.out.println("Starting browser...");

        WebDriverManager.edgedriver().setup();

        driver = new EdgeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
               .implicitlyWait(Duration.ofSeconds(10));

        DriverManager.setDriver(driver);

        System.out.println("Browser started successfully.");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        String screenshotPath = null;

        try {

            if (driver != null) {

                /*
                 * 1. Get Java class name.
                 *
                 * Example:
                 * TS033_AboutUsPageTest
                 */
                String testName =
                        result.getTestClass()
                              .getRealClass()
                              .getSimpleName();

                System.out.println(
                        "Test class: " + testName
                );

                /*
                 * 2. Take screenshot
                 */
                screenshotPath =
                        ScreenShotUtility.captureScreenshot(
                                driver,
                                testName
                        );

                System.out.println(
                        "Screenshot saved: "
                        + screenshotPath
                );

                /*
                 * 3. Determine test status
                 */
                String status;

                if (result.getStatus() == ITestResult.SUCCESS) {

                    status = "PASS";

                } else if (result.getStatus() == ITestResult.FAILURE) {

                    status = "FAIL";

                } else {

                    status = "SKIP";
                }

                /*
                 * 4. Store/update result in JSON
                 */
                String relativeScreenshotPath =
                        "Screenshots"
                        + File.separator
                        + new File(screenshotPath).getName();

                TestResultManager.updateResult(
                        testName,
                        status,
                        relativeScreenshotPath
                );

                /*
                 * 5. Attach screenshot to Extent Report
                 */
                ExtentTest extentTest =
                        ExtentTestNGListener.getTest();

                if (extentTest != null) {

                    String relativePath =
                            ".."
                            + File.separator
                            + "Screenshots"
                            + File.separator
                            + new File(screenshotPath)
                                    .getName();

                    extentTest.addScreenCaptureFromPath(
                            relativePath,
                            "Test Screenshot"
                    );
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Screenshot/Report error: "
                    + e.getMessage()
            );

        } finally {

            System.out.println("Closing browser...");

            DriverManager.quitDriver();

            System.out.println("Browser closed.");
        }
    }
}