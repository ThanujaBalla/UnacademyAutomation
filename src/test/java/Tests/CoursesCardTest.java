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

public class CoursesCardTest extends BaseTest {

    @Test
    public void verifyCoursesCard() {

        driver.get(
            "https://unacademy.com/goal/jee-main-and-advanced-preparation/TMUVD"
        );

        WebDriverWait wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(30)
        );

        By coursesCard = By.xpath(
            "//*[@id='__next']/div[1]/div[2]/div[2]/div[4]/div[1]/div[4]/a/span/div/div"
        );

        WebElement card = wait.until(
            ExpectedConditions.visibilityOfElementLocated(coursesCard)
        );

        Assert.assertTrue(
            card.isDisplayed(),
            "Courses card is not displayed"
        );

        Assert.assertTrue(
            card.getText().contains("Courses"),
            "Courses title is not displayed"
        );

        Assert.assertTrue(
            card.getText().contains(
                "Learn every subject in detail from your favourite educator."
            ),
            "Courses description is not displayed"
        );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block: 'center', inline: 'center'});",
            card
        );

        wait.until(
            ExpectedConditions.elementToBeClickable(card)
        );

        card.click();

        Assert.assertEquals(
            driver.getCurrentUrl(),
            "https://unacademy.com/goal/jee-main-and-advanced-preparation/TMUVD/popular_courses",
            "Courses page did not open"
        );

        System.out.println(
            "Courses card opened successfully."
        );
    }
}