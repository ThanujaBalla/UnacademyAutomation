package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

public class CareersPage {

    WebDriver driver;
    WebDriverWait wait;

    // "We Have 7 Open Jobs" button
    By openJobs =
            By.xpath("//a[contains(@href, '/careers/allJobs') and contains(@class, 'open-jobs-btn')]");

    // Search bar on All Jobs page
    By jobSearchBar =
            By.xpath("//div[@role='combobox']//input[@type='text']");

    // Constructor
    public CareersPage(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Verify Open Jobs button
    public boolean isOpenJobsDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(openJobs)
        ).isDisplayed();
    }

    // Click Open Jobs
    public void clickOpenJobs() {

        WebElement button = wait.until(
                ExpectedConditions.visibilityOfElementLocated(openJobs)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                button
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                button
        );

        wait.until(
                ExpectedConditions.urlContains("/careers/allJobs")
        );
    }

    // Verify Search Bar
    public boolean isJobSearchBarDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(jobSearchBar)
        ).isDisplayed();
    }
}