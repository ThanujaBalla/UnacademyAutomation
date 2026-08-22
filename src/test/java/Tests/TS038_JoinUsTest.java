package Tests;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.AboutUsPage;

public class TS038_JoinUsTest extends BaseTest {

    @Test
    public void TS038_verifyComeJoinUsAndCareersNavigation() {

        Reporter.log(
                "Starting TS038 - Verify Come Join Us and Careers navigation",
                true
        );

        driver.get("https://unacademy.com/about");

        Reporter.log("About Us page opened", true);

        AboutUsPage aboutUsPage = new AboutUsPage(driver);

        // Verify COME JOIN US section

        Assert.assertTrue(
                aboutUsPage.isJoinUsHeadingDisplayed(),
                "COME JOIN US heading is not displayed"
        );

        Assert.assertTrue(
                aboutUsPage.isJoinUsDescriptionDisplayed(),
                "Join Us description is not displayed"
        );

        Assert.assertTrue(
                aboutUsPage.isOpenRolesDisplayed(),
                "Open roles information is not displayed"
        );

        Assert.assertTrue(
                aboutUsPage.isSeeOpenPositionsDisplayed(),
                "See open positions button is not displayed"
        );

        Reporter.log(
                "COME JOIN US section verified successfully",
                true
        );

        // Click See open positions

        aboutUsPage.clickSeeOpenPositions();

        Reporter.log(
                "Clicked See open positions",
                true
        );

        // Verify Careers page

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://unacademy.com/careers",
                "Careers page URL is incorrect"
        );

        Reporter.log(
                "Careers page opened successfully",
                true
        );

        // Verify View all job openings

        Assert.assertTrue(
                aboutUsPage.isViewAllJobOpeningsDisplayed(),
                "View all job openings button is not displayed"
        );

        Reporter.log(
                "TS038 Passed - Careers page and View all job openings verified",
                true
        );
    }
}