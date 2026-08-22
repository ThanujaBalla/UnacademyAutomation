package Tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

import Base.BaseTest;

public class TestSeriesCardTest extends BaseTest {

    @Test
    public void verifyTestSeriesCard() {

        driver.get(
            "https://unacademy.com/goal/jee-main-and-advanced-preparation/TMUVD"
        );

        WebDriverWait wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(30)
        );

        By testSeriesCard = By.xpath(
            "//*[@id='__next']/div[1]/div[2]/div[2]/div[4]/div[2]/div[3]/a/span/div/div/h5"
        );

        WebElement card = wait.until(
            ExpectedConditions.visibilityOfElementLocated(testSeriesCard)
        );

        Assert.assertTrue(
            card.isDisplayed(),
            "Test Series card is not displayed"
        );

        Assert.assertEquals(
            card.getText(),
            "Test series",
            "Test Series title is not displayed correctly"
        );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block: 'center', inline: 'center'});",
            card
        );

        wait.until(
            ExpectedConditions.elementToBeClickable(card)
        );

        card.click();

        wait.until(
            ExpectedConditions.urlToBe(
                "https://unacademy.com/goal/jee-main-and-advanced-preparation/TMUVD/test-series"
            )
        );

        Assert.assertEquals(
            driver.getCurrentUrl(),
            "https://unacademy.com/goal/jee-main-and-advanced-preparation/TMUVD/test-series",
            "Test Series page did not open"
        );

        System.out.println(
            "Test Series card opened successfully."
        );
    }
}