package Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseTest;

public class GoalPersistenceTest extends BaseTest {

    @Test
    public void verifyGoalIsRetainedAfterLogoutAndRelogin() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(60)
        );

        // =========================================================
        // STEP 1: Open JEE page
        // =========================================================

        driver.get(
            "https://unacademy.com/goal/jee-main-and-advanced-preparation/TMUVD"
        );

        Thread.sleep(5000);

        // =========================================================
        // STEP 2: Click Login
        // =========================================================

        By loginButton = By.xpath(
            "//*[@id='__next']/header/div[1]/div[2]/div[4]/div/button[1]/span"
        );

        WebElement login = wait.until(
            ExpectedConditions.elementToBeClickable(loginButton)
        );

        login.click();

        // =========================================================
        // STEP 3: Enter mobile number
        // =========================================================

        By mobileInput = By.xpath(
            "//*[@id='DrawerPaper']/div[2]/div[1]/div[2]/div/input"
        );

        WebElement mobile = wait.until(
            ExpectedConditions.visibilityOfElementLocated(mobileInput)
        );

        mobile.sendKeys("6383917450");

        // =========================================================
        // STEP 4: Click Login / Get OTP
        // =========================================================

        By getOtpButton = By.xpath(
            "//*[@id='DrawerPaper']/div[2]/div[1]/div[3]/button"
        );

        WebElement getOtp = wait.until(
            ExpectedConditions.elementToBeClickable(getOtpButton)
        );

        getOtp.click();

        // =========================================================
        // STEP 5: MANUAL OTP
        // =========================================================
        // Enter OTP manually on your phone.
        // Selenium waits until login drawer disappears.
        // =========================================================

        wait.until(
            ExpectedConditions.invisibilityOfElementLocated(
                By.id("DrawerPaper")
            )
        );

        System.out.println("Login completed successfully.");

        Thread.sleep(5000);

        // =========================================================
        // STEP 6: Open Goal Dropdown
        // =========================================================

        By goalDropdown = By.xpath(
            "//*[@id=\"__next\"]/header/div[1]/div[1]/div[2]/div/button"
        );

        WebElement goalButton = wait.until(
            ExpectedConditions.elementToBeClickable(goalDropdown)
        );

        goalButton.click();

        Thread.sleep(2000);

        // =========================================================
        // STEP 7: Click "Add another goal"
        // =========================================================

        By addGoalButton = By.xpath(
            "/html/body/div[3]/div[3]/ul/div/div/li[2]/p"
        );

        WebElement addGoal = wait.until(
            ExpectedConditions.elementToBeClickable(addGoalButton)
        );

        addGoal.click();

        Thread.sleep(5000);

        // =========================================================
        // STEP 8: Verify Explore page
        // =========================================================

        wait.until(
            ExpectedConditions.urlToBe(
                "https://unacademy.com/explore"
            )
        );

        Assert.assertEquals(
            driver.getCurrentUrl(),
            "https://unacademy.com/explore",
            "Explore page did not open."
        );

        System.out.println("Explore page opened successfully.");

        Thread.sleep(5000);

        // =========================================================
        // STEP 9: Find NEET UG
        // =========================================================

        By neetGoal = By.xpath(
            "//*[@id='MIBRP']/div/div[2]/div"
        );

        WebElement neet = wait.until(
            ExpectedConditions.visibilityOfElementLocated(neetGoal)
        );

        // =========================================================
        // STEP 10: Click NEET UG
        // =========================================================

        wait.until(
            ExpectedConditions.elementToBeClickable(neet)
        );

        neet.click();

        Thread.sleep(5000);

        System.out.println("NEET UG added successfully.");

        // =========================================================
        // STEP 11: Refresh after adding goal
        // =========================================================

        driver.navigate().refresh();

        Thread.sleep(5000);

        // =========================================================
        // STEP 12: Open goal dropdown after refresh
        // =========================================================

        WebElement goalAfterAdd = wait.until(
            ExpectedConditions.elementToBeClickable(goalDropdown)
        );

        goalAfterAdd.click();

        Thread.sleep(2000);

        // =========================================================
        // STEP 13: Verify NEET UG exists before logout
        // =========================================================

        By neetVerification = By.xpath(
            "//*[normalize-space()='NEET UG']"
        );

        boolean neetExistsBeforeLogout =
            driver.findElements(neetVerification).size() > 0;

        Assert.assertTrue(
            neetExistsBeforeLogout,
            "NEET UG was not found after adding the goal."
        );

        System.out.println(
            "NEET UG verified before logout."
        );

        // =========================================================
        // STEP 14: Refresh before logout
        // =========================================================

        driver.navigate().refresh();

        Thread.sleep(5000);

        // =========================================================
        // STEP 15: Click Profile
        // =========================================================

        By profileButton = By.xpath(
        	    "//*[@id='__next']/header/div[1]/div[2]/div[4]/div/div/picture/img"
        	);

        	WebElement profile = wait.until(
        	    ExpectedConditions.elementToBeClickable(profileButton)
        	);

        	profile.click();

        	Thread.sleep(2000);

        // =========================================================
        // STEP 16: Click Sign Out
        // =========================================================

        	By logoutButton = By.xpath(
        		    "/html/body/div[3]/div[3]/ul/div[2]/div/p"
        		);

        		WebElement logout = wait.until(
        		    ExpectedConditions.elementToBeClickable(logoutButton)
        		);

        		logout.click();

        		System.out.println("Sign out clicked.");
        		
        		Thread.sleep(80000);

        		System.out.println(
        		    "Current URL after logout: " + driver.getCurrentUrl()
        		);

        // =========================================================
        // STEP 17: Verify redirected to homepage
        // =========================================================

        wait.until(
            ExpectedConditions.urlToBe(
                "https://unacademy.com/"
            )
        );

        Assert.assertEquals(
            driver.getCurrentUrl(),
            "https://unacademy.com/",
            "User was not redirected to homepage after logout."
        );

        System.out.println(
            "Logout successful. Redirected to homepage."
        );

     // =========================================================
     // STEP 18: Refresh after logout
     // =========================================================

     driver.navigate().refresh();
     Thread.sleep(5000);

     System.out.println("=================================================");
     System.out.println("LOGGED OUT SUCCESSFULLY");
     System.out.println("Please click the Log in button MANUALLY.");
     System.out.println("Selenium will continue automatically after the login drawer opens.");
     System.out.println("=================================================");


     // =========================================================
     // STEP 19: WAIT FOR MANUAL LOGIN CLICK
     // =========================================================

     // IMPORTANT:
     // We DO NOT click the Login button using Selenium here.
     // You manually click "Log in" on the homepage.
     //
     // Selenium waits until the mobile number field appears.

     By mobileInputAgain = By.xpath(
         "//*[@id='DrawerPaper']/div[2]/div[1]/div[2]/div/input"
     );

     System.out.println("Waiting for you to click Log in...");

     WebElement mobileAgain = wait.until(
         ExpectedConditions.visibilityOfElementLocated(mobileInputAgain)
     );

     System.out.println("Login drawer detected.");
     System.out.println("Continuing automation...");

     Thread.sleep(2000);


     // =========================================================
     // STEP 20: ENTER MOBILE NUMBER
     // =========================================================

     mobileAgain.sendKeys("6383917450");

     System.out.println("Mobile number entered.");

     Thread.sleep(1000);


     // =========================================================
     // STEP 21: CLICK LOGIN / GET OTP
     // =========================================================

     By loginOtpButton = By.xpath(
         "//*[@id='DrawerPaper']/div[2]/div[1]/div[3]/button"
     );

     WebElement loginOtp = wait.until(
         ExpectedConditions.elementToBeClickable(loginOtpButton)
     );

     loginOtp.click();

     System.out.println("OTP requested.");
     System.out.println("Please enter the OTP manually.");


     // =========================================================
     // STEP 22: WAIT FOR MANUAL OTP
     // =========================================================

     wait.until(
         ExpectedConditions.invisibilityOfElementLocated(
             By.id("DrawerPaper")
         )
     );

     System.out.println("Relogin completed successfully.");

     Thread.sleep(5000);


     // =========================================================
     // STEP 23: REFRESH AFTER RELOGIN
     // =========================================================

     driver.navigate().refresh();

     Thread.sleep(5000);

     System.out.println("Page refreshed after relogin.");


     // =========================================================
     // STEP 24: OPEN GOAL DROPDOWN
     // =========================================================

     By finalGoalDropdown = By.xpath(
         "//*[@id='__next']/header/div[1]/div[1]/div[2]/div/button"
     );

     WebElement finalGoalButton = wait.until(
         ExpectedConditions.elementToBeClickable(finalGoalDropdown)
     );

     finalGoalButton.click();

     Thread.sleep(3000);

     System.out.println("Goal dropdown opened after relogin.");


     // =========================================================
     // STEP 25: VERIFY NEET UG IS RETAINED
     // =========================================================

     By finalNeetGoal = By.xpath(
         "//*[normalize-space()='NEET UG']"
     );

     boolean neetRetained =
         driver.findElements(finalNeetGoal).size() > 0;

     Assert.assertTrue(
         neetRetained,
         "NEET UG was not retained after logout and relogin."
     );

     System.out.println(
         "PASSED - NEET UG was retained after logout and relogin."
     );
        
        /*By homeLoginButton = By.xpath(
            "//*[@id='__next']/header/div/div[3]/button[1]"
        );

        WebElement homeLogin = wait.until(
            ExpectedConditions.elementToBeClickable(homeLoginButton)
        );

        homeLogin.click();

        Thread.sleep(2000);

        // =========================================================
        // STEP 20: Enter mobile number again
        // =========================================================

        WebElement mobileAgain = wait.until(
            ExpectedConditions.visibilityOfElementLocated(mobileInput)
        );

        mobileAgain.sendKeys("6383917450");

        // =========================================================
        // STEP 21: Click Login
        // =========================================================

        WebElement loginAgain = wait.until(
            ExpectedConditions.elementToBeClickable(getOtpButton)
        );

        loginAgain.click();

        // =========================================================
        // STEP 22: MANUAL OTP AGAIN
        // =========================================================
        // Enter OTP manually.
        // Wait until login drawer disappears.
        // =========================================================

        wait.until(
            ExpectedConditions.invisibilityOfElementLocated(
                By.id("DrawerPaper")
            )
        );

        System.out.println(
            "Relogin completed successfully."
        );

        Thread.sleep(5000);

        // =========================================================
        // STEP 23: Refresh after relogin
        // =========================================================

        driver.navigate().refresh();

        Thread.sleep(5000);

        // =========================================================
        // STEP 24: Open Goal Dropdown
        // =========================================================

        WebElement finalGoalButton = wait.until(
            ExpectedConditions.elementToBeClickable(goalDropdown)
        );

        finalGoalButton.click();

        Thread.sleep(3000);

        // =========================================================
        // STEP 25: Verify NEET UG is retained
        // =========================================================

        By finalNeetGoal = By.xpath(
            "//*[normalize-space()='NEET UG']"
        );

        boolean neetRetained =
            driver.findElements(finalNeetGoal).size() > 0;

        Assert.assertTrue(
            neetRetained,
            "NEET UG was not retained after logout and relogin."
        );

        System.out.println(
            "PASSED - NEET UG goal was retained after logout and relogin."
        );*/
    }
}