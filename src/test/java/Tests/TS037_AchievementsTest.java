package Tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.AboutUsPage;

public class TS037_AchievementsTest extends BaseTest {

    @Test
    public void TS037_verifyCompanyAchievements() {

        Reporter.log(
                "Starting TS037 - Verify Company Achievements / Journey",
                true
        );

        // Open About Us page
        driver.get("https://unacademy.com/about");

        Reporter.log("About Us page opened", true);

        AboutUsPage aboutUsPage = new AboutUsPage(driver);

        // Verify 2015
        Assert.assertTrue(
                aboutUsPage.isJourney2015Displayed(),
                "2015 journey milestone is not displayed"
        );

        Reporter.log("2015 milestone verified", true);

        // Verify 2015 description
        Assert.assertTrue(
                aboutUsPage.isJourney2015DescriptionDisplayed(),
                "2015 milestone description is not displayed"
        );

        Reporter.log("2015 milestone description verified", true);

        // Verify 2017
        Assert.assertTrue(
                aboutUsPage.isJourney2017Displayed(),
                "2017 journey milestone is not displayed"
        );

        Reporter.log("2017 milestone verified", true);

        // Verify 2018
        Assert.assertTrue(
                aboutUsPage.isJourney2018Displayed(),
                "2018 journey milestone is not displayed"
        );

        Reporter.log("2018 milestone verified", true);

        // Verify 2020
        Assert.assertTrue(
                aboutUsPage.isJourney2020Displayed(),
                "2020 journey milestone is not displayed"
        );

        Reporter.log("2020 milestone verified", true);

        Reporter.log(
                "TS037 Passed - Company achievements / journey verified successfully",
                true
        );
    }
}