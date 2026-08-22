package Tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.CareersPage;
import Pages.DarwinboxCareersPage;

public class TS039_JobOpeningsTest extends BaseTest {

    @Test
    public void TS039_verifyJobOpeningsNavigation() {

        Reporter.log(
                "Starting TS039 - Verify Job Openings navigation",
                true
        );

        // ==========================================
        // Step 1 - Open Darwinbox Careers Home
        // ==========================================

        driver.get(
                "https://unacademy.darwinbox.in/ms/candidatev2/main/careers/home"
        );

        Reporter.log(
                "Darwinbox Careers Home opened",
                true
        );

        CareersPage careersPage = new CareersPage(driver);

        // ==========================================
        // Step 2 - Verify Open Jobs button
        // ==========================================

        Assert.assertTrue(
                careersPage.isOpenJobsDisplayed(),
                "We Have Open Jobs button is not displayed"
        );

        Reporter.log(
                "Open Jobs button verified successfully",
                true
        );

        // ==========================================
        // Step 3 - Click Open Jobs
        // ==========================================

        careersPage.clickOpenJobs();

        Reporter.log(
                "Clicked We Have Open Jobs",
                true
        );

        // ==========================================
        // Step 4 - Create Darwinbox All Jobs Page
        // ==========================================

        DarwinboxCareersPage darwinboxPage =
                new DarwinboxCareersPage(driver);

        // ==========================================
        // Step 5 - Verify All Jobs page
        // ==========================================

        Assert.assertTrue(
                darwinboxPage.isAllJobsPageOpened(),
                "All Jobs page did not open"
        );

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://unacademy.darwinbox.in/ms/candidatev2/main/careers/allJobs",
                "All Jobs page URL is incorrect"
        );

        Reporter.log(
                "All Jobs page opened successfully",
                true
        );

        // ==========================================
        // Step 6 - Verify Search Bar
        // ==========================================

        Assert.assertTrue(
                darwinboxPage.isJobSearchDisplayed(),
                "Job search bar is not displayed"
        );

        Reporter.log(
                "Job search bar verified successfully",
                true
        );

        // ==========================================
        // Step 7 - Search for a Job
        // ==========================================

        String jobName = "Business Development Executive";

        darwinboxPage.searchJob(jobName);

        Reporter.log(
                "Searched for job: " + jobName,
                true
        );

        // ==========================================
        // Step 8 - Verify Job is Available
        // ==========================================

        Assert.assertTrue(
                darwinboxPage.isJobDisplayed(jobName),
                "Job is not available: " + jobName
        );

        Reporter.log(
                "Job is available: " + jobName,
                true
        );

        // ==========================================
        // Step 9 - Verify View and Apply
        // ==========================================

        Assert.assertTrue(
                darwinboxPage.isViewAndApplyDisplayed(),
                "View and Apply button is not displayed"
        );

        Reporter.log(
                "View and Apply button verified successfully",
                true
        );

        Reporter.log(
                "TS039 Passed - Job openings navigation, search and availability verified",
                true
        );
    }
}