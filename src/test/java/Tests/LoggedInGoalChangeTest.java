package Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseTest;
import Utilities.ConfigReader;

public class LoggedInGoalChangeTest extends BaseTest {

    @Test
    public void verifyLoggedInUserCanChangeGoal() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(60)
        );

        // =========================================================
        // STEP 1: Open NEET UG page
        // =========================================================

        driver.get(
                "https://unacademy.com/goal/neet-ug/YOTUH"
        );

        // Wait 5 seconds after opening the page
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

        mobile.sendKeys(ConfigReader.getProperty("mobile_number"));

        // =========================================================
        // STEP 4: Click Login / Get OTP
        // =========================================================

        By getOtpButton = By.xpath(
                "//*[@id='DrawerPaper']/div[2]/div[1]/div[3]/button"
        );

        WebElement getOtp = wait.until(
                ExpectedConditions.elementToBeClickable(getOtpButton)
        );
        
        By switchGoalButton = By.xpath(
        	    "//*[contains(@class,'TooltipAction') and normalize-space()='SWITCH GOAL']"
        	);
        
        getOtp.click();

        // =========================================================
        // STEP 5: MANUAL OTP
        // =========================================================
        //
        // Enter OTP manually on your phone / browser.
        //
        // Selenium waits until login drawer disappears.
        // =========================================================

        System.out.println("Please enter the OTP manually...");

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.id("DrawerPaper")
                )
        );

        System.out.println("Login completed successfully.");

        // Give the logged-in page time to settle
        Thread.sleep(5000);

        // =========================================================
        // STEP 6: Close popup appearing after login
        // =========================================================

        try {

            /*
             * Popup close button.
             *
             * The mui-xxxxx ID can change between runs,
             * so we locate the SVG through the popup structure
             * instead of depending on one generated ID.
             */

            By popupClose = By.xpath(
                    "//div[starts-with(@id,'mui-')]/div/div/div[1]/div/svg"
            );

            WebElement switchGoal = wait.until(
            	    ExpectedConditions.elementToBeClickable(switchGoalButton)
            	);

            	switchGoal.click();

            	Thread.sleep(5000);
        } catch (Exception e) {

            // Popup may not appear every time.
            System.out.println(
                    "Post-login popup not present. Continuing..."
            );
        }

        // =========================================================
        // STEP 7: Click current NEET UG goal button
        // =========================================================

        By goalButton = By.xpath(
                "//*[@id='__next']/header/div[1]/div[1]/div[2]/div/button"
        );

        WebElement neetGoalButton = wait.until(
                ExpectedConditions.elementToBeClickable(goalButton)
        );

        System.out.println(
                "Current goal: " + neetGoalButton.getText()
        );

        neetGoalButton.click();

        // Wait 5 seconds so dropdown is completely visible
        Thread.sleep(5000);

        // =========================================================
        // STEP 8: Select IIT JEE
        // =========================================================

        By iitJeeOption = By.xpath(
                "/html/body/div[3]/div[3]/ul/div/div/div/div/div/div[2]/li/div[2]/p[1]"
        );

        WebElement iitJee = wait.until(
                ExpectedConditions.visibilityOfElementLocated(iitJeeOption)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                iitJee
        );

        Thread.sleep(1000);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                iitJee
        );

        System.out.println("IIT JEE selected.");

        // =========================================================
        // STEP 9: Wait for IIT JEE page navigation
        // =========================================================

        wait.until(
                ExpectedConditions.urlToBe(
                        "https://unacademy.com/goal/jee-main-and-advanced-preparation/TMUVD"
                )
        );

        Thread.sleep(5000);

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://unacademy.com/goal/jee-main-and-advanced-preparation/TMUVD",
                "Goal did not change to IIT JEE"
        );

        // Verify header
        WebElement currentGoal = wait.until(
                ExpectedConditions.visibilityOfElementLocated(goalButton)
        );

        Assert.assertTrue(
                currentGoal.getText().contains("IIT JEE"),
                "Top goal button does not show IIT JEE"
        );

        System.out.println(
                "IIT JEE page loaded successfully."
        );

        // =========================================================
        // STEP 10: Wait 5 seconds before reverse navigation
        // =========================================================

        Thread.sleep(5000);

        // =========================================================
        // STEP 11: Open IIT JEE goal dropdown
        // =========================================================

        WebElement iitJeeGoalButton = wait.until(
                ExpectedConditions.elementToBeClickable(goalButton)
        );

        iitJeeGoalButton.click();

        // Wait 5 seconds for dropdown
        Thread.sleep(5000);

        // =========================================================
        // STEP 12: Select NEET UG
        // =========================================================

        By neetOption = By.xpath(
                "/html/body/div[3]/div[3]/ul/div/div/div/div/div/div[2]/li/div[2]/p[1]"
        );

        WebElement neet = wait.until(
                ExpectedConditions.visibilityOfElementLocated(neetOption)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                neet
        );

        Thread.sleep(1000);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                neet
        );

        System.out.println("NEET UG selected.");

        // =========================================================
        // STEP 13: Wait for NEET page
        // =========================================================

        wait.until(
                ExpectedConditions.urlToBe(
                        "https://unacademy.com/goal/neet-ug/YOTUH"
                )
        );

        Thread.sleep(5000);

        // =========================================================
        // STEP 14: Final verification
        // =========================================================

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://unacademy.com/goal/neet-ug/YOTUH",
                "Goal did not change back to NEET UG"
        );

        WebElement finalGoal = wait.until(
                ExpectedConditions.visibilityOfElementLocated(goalButton)
        );

        Assert.assertTrue(
                finalGoal.getText().contains("NEET UG"),
                "Top goal button does not show NEET UG"
        );

        System.out.println(
                "================================================="
        );

        System.out.println(
                "PASSED - Logged-in user successfully changed:"
        );

        System.out.println(
                "NEET UG -> IIT JEE -> NEET UG"
        );

        System.out.println(
                "================================================="
        );
    }
}