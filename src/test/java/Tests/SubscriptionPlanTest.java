package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.SubscriptionPage;
import Utilities.ConfigReader;
public class SubscriptionPlanTest extends BaseTest {

    @Test(
        description = "Verify Plus, Iconic and Iconic Pro subscription plans"
    )
    public void subscriptionPlansAreDisplayed() {

        SubscriptionPage subscription =
                new SubscriptionPage(
                        page,
                        ConfigReader.getProperty("baseUrl")
                );

        subscription.open();
        subscription.scrollToPlans();

        String body = page.locator("body").innerText();

        System.out.println("===== SUBSCRIPTION PAGE =====");
        System.out.println(body);
        System.out.println("=============================");

        // Plan names
        Assert.assertTrue(
                body.contains("Plus"),
                "Plus plan is not displayed"
        );

        Assert.assertTrue(
                body.contains("Iconic"),
                "Iconic plan is not displayed"
        );

        Assert.assertTrue(
                body.contains("Iconic Pro"),
                "Iconic Pro plan is not displayed"
        );

        // CTA buttons
        Assert.assertTrue(
                body.contains("Get Plus"),
                "Get Plus button is not displayed"
        );

        Assert.assertTrue(
                body.contains("Get Iconic"),
                "Get Iconic button is not displayed"
        );

        Assert.assertTrue(
                body.contains("Get Iconic Pro"),
                "Get Iconic Pro button is not displayed"
        );

        // Verify that prices are present without assuming
        // a hard-coded amount.
        Assert.assertTrue(
                body.matches("(?s).*₹\\s*[0-9,]+\\s*/\\s*month.*"),
                "Subscription price is not displayed"
        );

        System.out.println("Subscription plans verified successfully.");
    }
}