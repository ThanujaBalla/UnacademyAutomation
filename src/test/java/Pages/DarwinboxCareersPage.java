package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DarwinboxCareersPage {

    WebDriver driver;

    WebDriverWait wait;

    // All Jobs page - Search input
    By jobSearchInput =
            By.xpath("//input[@aria-autocomplete='list']");

    // Job titles
    By jobTitles =
            By.xpath("//span[contains(@class,'job-title')]");

    // View and Apply buttons
    By viewAndApplyButtons =
            By.xpath("//a[contains(@class,'action-btn') and normalize-space()='View and Apply']");

    public DarwinboxCareersPage(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isAllJobsPageOpened() {

        return wait.until(
                ExpectedConditions.urlContains("/careers/allJobs")
        );
    }

    public boolean isJobSearchDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(jobSearchInput)
        ).isDisplayed();
    }

    public void searchJob(String jobName) {

        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(jobSearchInput)
        );

        // Scroll search box into the center of the screen
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                searchBox
        );

        // JavaScript click avoids overlay interception
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                searchBox
        );

        // Enter search text
        searchBox.sendKeys(jobName);
    }

    public boolean isJobDisplayed(String jobName) {

        By job = By.xpath(
                "//span[contains(@class,'job-title') and normalize-space()='"
                + jobName + "']"
        );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(job)
        ).isDisplayed();
    }

    public boolean isViewAndApplyDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(viewAndApplyButtons)
        ).isDisplayed();
    }
}