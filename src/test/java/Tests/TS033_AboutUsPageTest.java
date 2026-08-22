package Tests;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.AboutUsPage;

public class TS033_AboutUsPageTest extends BaseTest {

    @Test
    public void TS033_verifyAboutUsPageLoads() {

        Reporter.log("Starting TS033 - Verify About Us page loads", true);

        driver.get("https://unacademy.com/about");

        Reporter.log("About Us page opened", true);

        AboutUsPage aboutUsPage = new AboutUsPage(driver);

        Assert.assertTrue(
                aboutUsPage.isMissionImpactDisplayed(),
                "About Us page did not load successfully"
        );

        Reporter.log(
                "TS033 Passed - About Us page loaded successfully",
                true
        );
    }
}
