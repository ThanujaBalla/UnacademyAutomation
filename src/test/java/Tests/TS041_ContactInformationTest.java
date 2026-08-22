package Tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.AboutUsPage;

public class TS041_ContactInformationTest extends BaseTest {

    @Test
    public void TS041_verifyContactInformation() {

        Reporter.log(
                "Starting TS041 - Verify Contact Information on About Us page",
                true
        );

        // Open Unacademy About Us page
        driver.get("https://unacademy.com/about");

        Reporter.log(
                "Unacademy About Us page opened",
                true
        );

        AboutUsPage aboutUsPage = new AboutUsPage(driver);

        // Verify Public Relations contact
        Assert.assertTrue(
                aboutUsPage.isPublicRelationsDisplayed(),
                "Public relations section is not displayed"
        );

        Reporter.log(
                "Public relations section verified successfully",
                true
        );

        Assert.assertTrue(
                aboutUsPage.isPressEmailDisplayed(),
                "Press email is not displayed"
        );

        Reporter.log(
                "Press email verified successfully",
                true
        );

        // Verify Contact Us section
        Assert.assertTrue(
                aboutUsPage.isContactUsDisplayed(),
                "Contact us section is not displayed"
        );

        Reporter.log(
                "Contact us section verified successfully",
                true
        );

        Assert.assertTrue(
                aboutUsPage.isHelpEmailDisplayed(),
                "Help email is not displayed"
        );

        Reporter.log(
                "Help email verified successfully",
                true
        );

        Reporter.log(
                "TS041 Passed - Contact information verified successfully",
                true
        );
    }
}