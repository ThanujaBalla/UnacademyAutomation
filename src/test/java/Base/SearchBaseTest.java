package Base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import Pages.LoginPage;
import Utilities.ConfigReader;

public class SearchBaseTest extends BaseTest {

	protected WebDriverWait wait;

	private By searchInput = By.cssSelector("input[placeholder='Search courses, test series and educators']");

	@BeforeMethod
	public void searchSetup() {

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		driver.get(ConfigReader.getProperty("searchurl"));

		System.out.println();
		System.out.println("======================================");
		System.out.println("Search Test Setup");
		System.out.println("======================================");

		System.out.println("Current URL: " + driver.getCurrentUrl());

		/*
		 * First check whether Search is already available.
		 */
		try {

			wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));

			System.out.println("Search box is available.");
			System.out.println("Login is not required.");
			System.out.println("Continuing Search test...");

		} catch (Exception e) {

			/*
			 * Search is not available. Perform login automatically using the configured
			 * mobile number, then wait for manual OTP entry.
			 */

			System.out.println();
			System.out.println("Search box is not available.");
			System.out.println("Starting automatic login...");
			performLogin();
			wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
			System.out.println();
			System.out.println("Login completed successfully.");
			System.out.println("Search box is now available.");
			System.out.println("Continuing Search test...");
		}
	}

	private void performLogin() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.gotoLoginPage();
		String mobileNumber = ConfigReader.getProperty("mobile_number");
		System.out.println("Using configured mobile number: " + mobileNumber);
		loginPage.enterMobileNumber(mobileNumber);
		loginPage.clickLogin();
		loginPage.clickVerifyOTP();
		System.out.println();
		System.out.println("OTP screen opened.");

		System.out.println("Please enter the REAL OTP manually in the browser.");

		loginPage.waitForManualOTP();

		System.out.println("OTP submitted successfully.");
	}
}