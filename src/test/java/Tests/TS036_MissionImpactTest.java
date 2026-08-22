package Tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import Base.BaseTest;
import Pages.AboutUsPage;

public class TS036_MissionImpactTest extends BaseTest {

    @Test
    public void TS036_verifyMissionAndImpact() {

        Reporter.log("Starting TS036 - Verify Mission & Impact section", true);


        // Open Unacademy About Us page
        driver.get("https://unacademy.com/about");

        Reporter.log("About Us page opened", true);

        AboutUsPage aboutUsPage = new AboutUsPage(driver);

        // Verify Mission & Impact section
        Assert.assertTrue(
                aboutUsPage.isMissionImpactHeadingDisplayed(),
                "OUR MISSION & IMPACT heading is not displayed"
        );

        Reporter.log("Mission & Impact heading verified", true);

        // Verify mission description
        Assert.assertTrue(
                aboutUsPage.isMissionDescriptionDisplayed(),
                "Mission description is not displayed"
        );

        Reporter.log("Mission description verified", true);

        // Verify Active Learners
        Assert.assertTrue(
                aboutUsPage.isActiveLearnersDisplayed(),
                "Active Learners content is not displayed"
        );

        Reporter.log("Active Learners verified", true);

        // Verify Top Educators
        Assert.assertTrue(
                aboutUsPage.isTopEducatorsDisplayed(),
                "Top Educators content is not displayed"
        );

        Reporter.log("Top Educators verified", true);

        Reporter.log(
                "TS036 Passed - Mission & Impact section verified successfully",
                true
        );
      

    }
}