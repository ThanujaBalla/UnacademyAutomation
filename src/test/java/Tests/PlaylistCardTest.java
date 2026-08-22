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

public class PlaylistCardTest extends BaseTest {

    @Test
    public void verifyPlaylistCard() throws InterruptedException {

        driver.get(
            "https://unacademy.com/goal/jee-main-and-advanced-preparation/TMUVD"
        );

        WebDriverWait wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(30)
        );

        By playlistCard = By.xpath(
            "//*[@id='__next']/div[1]/div[2]/div[2]/div[4]/div[2]/div[1]"
        );

        WebElement card = wait.until(
            ExpectedConditions.visibilityOfElementLocated(playlistCard)
        );

        Assert.assertTrue(
            card.isDisplayed(),
            "Playlist card is not displayed"
        );

        Assert.assertTrue(
            card.getText().contains("Playlist"),
            "Playlist title is not displayed"
        );

        Assert.assertTrue(
            card.getText().contains(
                "High quality lecture videos for the entire syllabus for all your subjects."
            ),
            "Playlist description is not displayed"
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
                "https://unacademy.com/goal/jee-main-and-advanced-preparation/TMUVD/topics"
            )
        );

        Assert.assertEquals(
            driver.getCurrentUrl(),
            "https://unacademy.com/goal/jee-main-and-advanced-preparation/TMUVD/topics",
            "Playlist page did not open"
        );

        System.out.println(
            "Playlist card opened successfully."
        );
    }
}