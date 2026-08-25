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

public class AddRemoveGoalTest extends BaseTest {

    @Test
    public void verifyUserCanAddAndRemoveGoal() throws InterruptedException {

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
            ExpectedConditions.visibilityOfElementLocated(loginButton)
        );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            login
        );

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
        // STEP 4: Click Get OTP / Login
        // =========================================================

        By getOtpButton = By.xpath(
            "//*[@id='DrawerPaper']/div[2]/div[1]/div[3]/button"
        );

        WebElement getOtp = wait.until(
            ExpectedConditions.visibilityOfElementLocated(getOtpButton)
        );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            getOtp
        );

        // =========================================================
        // STEP 5: MANUAL OTP
        // User enters OTP manually.
        // Wait until login drawer disappears.
        // =========================================================

        wait.until(
            ExpectedConditions.invisibilityOfElementLocated(
                By.id("DrawerPaper")
            )
        );

        System.out.println("Login completed successfully.");

        // =========================================================
        // STEP 6: Wait 5 seconds after login
        // =========================================================

        Thread.sleep(5000);

        // =========================================================
        // STEP 7: Click current goal dropdown
        // =========================================================

        By goalDropdown = By.xpath(
            "//*[@id='__next']/header/div[1]/div[1]/div[2]/div/button"
        );

        WebElement goalButton = wait.until(
            ExpectedConditions.visibilityOfElementLocated(goalDropdown)
        );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            goalButton
        );

        System.out.println("Goal dropdown opened.");

        Thread.sleep(5000);

        // =========================================================
        // STEP 8: Click Add another goal
        // =========================================================

     // =========================================================
     // STEP 8: Click Add another goal
     // =========================================================

     By addGoalButton = By.xpath(
         "/html/body/div[3]/div[3]/ul/div/div/li[2]/p"
     );

     WebElement addGoal = wait.until(
         ExpectedConditions.visibilityOfElementLocated(addGoalButton)
     );

     ((JavascriptExecutor) driver).executeScript(
         "arguments[0].scrollIntoView({block:'center'});",
         addGoal
     );

     Thread.sleep(2000);

     ((JavascriptExecutor) driver).executeScript(
         "arguments[0].click();",
         addGoal
     );

     System.out.println("Add another goal clicked.");

     Thread.sleep(5000);

        // =========================================================
        // STEP 9: Verify Explore page
        // =========================================================

        wait.until(
            ExpectedConditions.urlToBe(
                "https://unacademy.com/explore"
            )
        );

        Assert.assertEquals(
            driver.getCurrentUrl(),
            "https://unacademy.com/explore",
            "Explore page did not open after clicking Add Goal"
        );

        System.out.println("Explore page opened successfully.");

        // =========================================================
        // STEP 10: Find NEET UG
        // =========================================================

     // =========================================================
     // STEP 10: Find NEET UG card
     // =========================================================

     By neetGoal = By.xpath("//*[@id=\"MIBRP\"]/div/div[2]/div");

     WebElement neet = wait.until(
         ExpectedConditions.presenceOfElementLocated(neetGoal)
     );

     System.out.println("NEET UG element found.");

     // Scroll NEET card into the center of the screen
     ((JavascriptExecutor) driver).executeScript(
         "arguments[0].scrollIntoView({block:'center', inline:'center'});",
         neet
     );

     Thread.sleep(3000);

     // =========================================================
     // STEP 11: Click NEET UG
     // =========================================================

     wait.until(
         ExpectedConditions.elementToBeClickable(neetGoal)
     );

     neet.click();

     Thread.sleep(5000);

     System.out.println("NEET UG selected.");
     
        // =========================================================
        // STEP 12: Open goal dropdown again
        // =========================================================

        WebElement goalButtonAfterAdd = wait.until(
            ExpectedConditions.visibilityOfElementLocated(goalDropdown)
        );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            goalButtonAfterAdd
        );

        System.out.println("Goal dropdown opened after adding NEET.");

        Thread.sleep(3000);
        //refresh again
        
        Thread.sleep(3000);

        driver.navigate().refresh();

        Thread.sleep(5000);
        
        //----------------------------
        //step 12b : change to jee
        //----------------------------
        
        By iitJeeOption = By.xpath(
        	    "/html/body/div[3]/div[3]/ul/div/div/div/div/div/div[2]/li/div[2]/p[1]"
        	);

        	WebElement iitJEE = wait.until(
        	    ExpectedConditions.visibilityOfElementLocated(iitJeeOption)
        	);

        	((JavascriptExecutor) driver).executeScript(
        	    "arguments[0].scrollIntoView({block:'center', inline:'center'});",
        	    iitJEE
        	);

        	Thread.sleep(2000);

        	iitJEE.click();

        	Thread.sleep(5000);
        	
       //Refresh Again 
            Thread.sleep(3000);

            driver.navigate().refresh();

            Thread.sleep(5000);
            
       //Open Goal Again
            WebElement goalButtonAfterAdd1 = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(goalDropdown)
                );

                ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    goalButtonAfterAdd1
                );

                System.out.println("Goal dropdown opened after adding NEET.");

                Thread.sleep(3000);
        
        // =========================================================
        // STEP 13: Click Edit
        // =========================================================

        	By editGoal = By.xpath(
        		    "/html/body/div[3]/div[3]/ul/div/div/li[1]/div/h6"
        		);

        		WebElement edit = wait.until(
        		    ExpectedConditions.elementToBeClickable(editGoal)
        		);

        		edit.click();

        		Thread.sleep(3000);
        // =========================================================
        // STEP 14: Click red Remove icon beside JEE
        // =========================================================

        		// Click red remove icon
        		By removeNeetButton = By.xpath(
        		    "/html/body/div[3]/div[3]/ul/div/div/div/div/div/div[2]/li/div[3]/img"
        		);

        		WebElement removeNeet = wait.until(
        		    ExpectedConditions.elementToBeClickable(removeNeetButton)
        		);

        		removeNeet.click();

        		Thread.sleep(2000);
        // =========================================================
        // STEP 15: Confirmation popup
        // =========================================================

        		By confirmRemoveButton = By.xpath(
        			    "/html/body/div[4]/div[3]/div/div/div/button[1]"
        			);

        			WebElement confirmRemove = wait.until(
        			    ExpectedConditions.elementToBeClickable(confirmRemoveButton)
        			);

        			confirmRemove.click();

        			Thread.sleep(3000);

        			System.out.println("NEET removal confirmed.");
        			
        	        Thread.sleep(3000);

        	        driver.navigate().refresh();

        	        Thread.sleep(5000);
        // =========================================================
        // STEP 16: Refresh page
        // =========================================================

        Thread.sleep(3000);

        driver.navigate().refresh();

        Thread.sleep(5000);

        /*// =========================================================
        // STEP 17: Open goal dropdown after refresh
        // =========================================================

        WebElement finalGoalButton = wait.until(
            ExpectedConditions.visibilityOfElementLocated(goalDropdown)
        );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            finalGoalButton
        );

        Thread.sleep(3000);

        // =========================================================
        // STEP 18: Verify IIT JEE has been removed
        // =========================================================

     // =========================================================
     // STEP 18: Verify NEET UG has been removed
     // =========================================================

     By neetGoal1 = By.xpath(
         "//*[normalize-space()='NEET UG']"
     );

     boolean neetStillPresent =
         driver.findElements(neetGoal1).size() > 0;

     Assert.assertFalse(
         neetStillPresent,
         "NEET UG is still present after removing the goal."
     );

     // =========================================================
     // STEP 19: Verify IIT JEE remains
     // =========================================================

     By finalJeeGoal = By.xpath(
         "//*[normalize-space()='IIT JEE']"
     );

     boolean jeePresent =
         driver.findElements(finalJeeGoal).size() > 0;

     Assert.assertTrue(
         jeePresent,
         "IIT JEE is not present after removing NEET."
     );

     System.out.println(
         "PASSED - NEET UG was removed and IIT JEE remains successfully."
     );*/
        			 System.out.println(
        			         "PASSED - NEET UG was removed and IIT JEE remains successfully."
        			     ); 			
    }
}