
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

public class CourseNavigationTest extends BaseTest {

    @Test
    public void verifyIITJEEToNEETAndBackToExplore() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(30)
        );

        // STEP 1: Open IIT JEE goal page
        driver.get(
            "https://unacademy.com/goal/jee-main-and-advanced-preparation/TMUVD"
        );

        // STEP 2: Click IIT JEE header button
        By iitJeeHeaderButton = By.xpath(
            "//*[@id='__next']/header/div[1]/div[1]/div[2]/div/button"
        );

        WebElement iitJeeButton = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                iitJeeHeaderButton
            )
        );

        wait.until(
            ExpectedConditions.elementToBeClickable(iitJeeButton)
        );

        iitJeeButton.click();

        // Verify Explore page
        wait.until(
            ExpectedConditions.urlToBe(
                "https://unacademy.com/explore"
            )
        );

        Assert.assertEquals(
            driver.getCurrentUrl(),
            "https://unacademy.com/explore",
            "Explore page did not open from IIT JEE"
        );

        // STEP 3: Find NEET UG card
        By neetUGCard = By.xpath(
            "//*[@id=\"MIBRP\"]/div/div[2]/p[1]"
        );

        WebElement neetCard = wait.until(
        	    ExpectedConditions.visibilityOfElementLocated(
        	        neetUGCard
        	    )
        	);

        	// Scroll NEET card to the center of the viewport
        	((JavascriptExecutor) driver).executeScript(
        	    "arguments[0].scrollIntoView({block: 'center', inline: 'center'});",
        	    neetCard
        	);

        	wait.until(
        	    ExpectedConditions.elementToBeClickable(neetCard)
        	);
        	Thread.sleep(1500);
        	neetCard.click();

        // Verify NEET UG page
        wait.until(
            ExpectedConditions.urlToBe(
                "https://unacademy.com/goal/neet-ug/YOTUH"
            )
        );

        Assert.assertEquals(
            driver.getCurrentUrl(),
            "https://unacademy.com/goal/neet-ug/YOTUH",
            "NEET UG page did not open"
        );

        // STEP 4: Click NEET UG header button
        By neetHeaderButton = By.xpath(
            "//*[@id='__next']/header/div[1]/div[1]/div[2]/div/button"
        );

        WebElement neetButton = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                neetHeaderButton
            )
        );

        wait.until(
            ExpectedConditions.elementToBeClickable(neetButton)
        );
        Thread.sleep(1500);
        neetButton.click();

        // Verify final Explore page
        wait.until(
            ExpectedConditions.urlToBe(
                "https://unacademy.com/explore"
            )
        );
        Thread.sleep(1500);
        Assert.assertEquals(
            driver.getCurrentUrl(),
            "https://unacademy.com/explore",
            "Explore page did not open from NEET UG"
        );

        System.out.println(
            "Course navigation test passed - IIT JEE → Explore → NEET UG → Explore."
        );
    }
}