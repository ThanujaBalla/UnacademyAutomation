package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseTest;

public class DirectDeepLinkTest extends BaseTest {

    @Test
    public void verifyScholarshipTestDeepLinkForGuestUser() {

        // Direct deep-link URL
        String deepLink =
                "https://unacademy.com/goal/jee-main-and-advanced-preparation/TMUVD/scholarship-test/5Q2Q1XV4Q8";

        // Expected base scholarship-test page
        String expectedPage =
                "https://unacademy.com/goal/jee-main-and-advanced-preparation/TMUVD/scholarship-test";

        // Open the deep link
        driver.get(deepLink);

        // Verify the page loaded as the scholarship-test page
        Assert.assertTrue(
                driver.getCurrentUrl().contains(expectedPage),
                "Scholarship Test page did not load correctly"
        );

        // Verify the original deep-link ID is still present in the browser URL
        Assert.assertTrue(
                driver.getCurrentUrl().contains("5Q2Q1XV4Q8"),
                "Original deep-link ID is missing from the browser URL"
        );

        // Verify we did not get redirected to login
        Assert.assertFalse(
                driver.getCurrentUrl().contains("/login"),
                "Guest user was redirected to login"
        );

        System.out.println(
                "TC034 PASSED - Scholarship Test deep link opened the exam page for guest user."
        );
    }
}