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

public class PracticeCardTest extends BaseTest {

    @Test
    public void verifyPracticeCard() {

        driver.get(
            "https://unacademy.com/goal/jee-main-and-advanced-preparation/TMUVD"
        );

        WebDriverWait wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(30)
        );

        By practiceCard = By.xpath(
            "//*[@id='__next']/div[1]/div[2]/div[2]/div[4]/div[2]/div[2]/a/span/div/div"
        );

        WebElement card = wait.until(
            ExpectedConditions.visibilityOfElementLocated(practiceCard)
        );

        Assert.assertTrue(
            card.isDisplayed(),
            "Practice card is not displayed"
        );

        Assert.assertTrue(
            card.getText().contains("Practice"),
            "Practice title is not displayed"
        );

        Assert.assertTrue(
            card.getText().contains(
                "Strengthen your exam preparation with adaptive practice tests."
            ),
            "Practice description is not displayed"
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
                "https://unacademy.com/goal/jee-main-and-advanced-preparation/TMUVD/practice"
            )
        );

        Assert.assertEquals(
            driver.getCurrentUrl(),
            "https://unacademy.com/goal/jee-main-and-advanced-preparation/TMUVD/practice",
            "Practice page did not open"
        );

        System.out.println(
            "Practice card opened successfully."
        );
    }
}