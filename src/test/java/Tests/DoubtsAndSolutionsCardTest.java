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

public class DoubtsAndSolutionsCardTest extends BaseTest {

    @Test
    public void verifyDoubtsAndSolutionsCard() {

        driver.get(
            "https://unacademy.com/goal/jee-main-and-advanced-preparation/TMUVD"
        );

        WebDriverWait wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(30)
        );

        By doubtsCard = By.xpath(
            "//*[@id='__next']/div[1]/div[2]/div[2]/div[4]/div[2]/div[4]/a/span/div"
        );

        WebElement card = wait.until(
            ExpectedConditions.visibilityOfElementLocated(doubtsCard)
        );

        Assert.assertTrue(
            card.isDisplayed(),
            "Doubts & Solutions card is not displayed"
        );

        Assert.assertTrue(
            card.getText().contains("Doubts & solutions"),
            "Doubts & Solutions title is not displayed"
        );

        Assert.assertTrue(
            card.getText().contains(
                "Get quick and detailed solutions to clarify your doubts."
            ),
            "Doubts & Solutions description is not displayed"
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
                "https://unacademy.com/goal/jee-main-and-advanced-preparation/TMUVD/doubts-and-solutions"
            )
        );

        Assert.assertEquals(
            driver.getCurrentUrl(),
            "https://unacademy.com/goal/jee-main-and-advanced-preparation/TMUVD/doubts-and-solutions",
            "Doubts & Solutions page did not open"
        );

        System.out.println(
            "Doubts & Solutions card opened successfully."
        );
    }
}