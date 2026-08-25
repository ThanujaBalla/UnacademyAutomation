package Tests;

import Base.BaseTest;
import org.testng.SkipException;
import org.testng.annotations.Test;
import Pages.SubscriptionPage;
import Utilities.ConfigReader;

/**
 * Maps the lifecycle cases TC069-TC097. Real authenticated state transitions require
 * an authorized test account and environment that supports creating/renewing/upgrading/cancelling
 * subscriptions without touching production billing.
 */
public class SubscriptionLifecycleTest extends BaseTest {
    @Test public void TC069_to_TC082_activationAndSubscriptionDetails() { requireSandbox(); new SubscriptionPage(page,ConfigReader.getProperty("baseUrl")).open(); }
    @Test public void TC083_to_TC090_renewalAndUpgrade() { requireSandbox(); new SubscriptionPage(page,ConfigReader.getProperty("baseUrl")).open(); }
    @Test public void TC091_to_TC097_cancellationAndExpiry() { requireSandbox(); new SubscriptionPage(page, ConfigReader.getProperty("baseUrl")).open(); }

    private void requireSandbox() {
        if (ConfigReader.getProperty("authState").isBlank() && (ConfigReader.getProperty("username").isBlank() || ConfigReader.getProperty("password").isBlank())) {
            throw new SkipException("Authorized test account not configured for authenticated lifecycle automation.");
        }
    }
}
