package Tests;

import Base.BaseTest;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.github.dockerjava.api.model.Config;

import Pages.GoalPage;
import Pages.LoginPageA;
import Pages.PaymentPage;
import Pages.SubscriptionPage;
import Utilities.ConfigReader;

/**
 * Full sequence when an authorized test account + permitted payment sandbox are configured.
 * The test is intentionally guarded: it must never submit dummy data to production.
 */
public class AuthenticatedSubscriptionE2ETest extends BaseTest {
    @Test
    public void newSubscriptionEndToEnd() {
        GoalPage goal = new GoalPage(page, ConfigReader.getProperty("baseUrl"));
        goal.open();
        goal.scrollDown(900);

        LoginPageA login = new LoginPageA(page);
        if (!login.hasLoginForm()) {
            login.clickLoginIfPresent();
        }

        String paymentMode = ConfigReader.getProperty("paymentMode");
        if (ConfigReader.getProperty("authState").isBlank() && (ConfigReader.getProperty("username").isBlank() || ConfigReader.getProperty("password").isBlank())) {
            throw new SkipException("Authorized login not configured. Set authState=path/to/storage-state.json or UNACADEMY_USERNAME/UNACADEMY_PASSWORD, then record the account-specific login/OTP flow if required.");
        }

        if ("stub".equalsIgnoreCase(paymentMode)) {
            // We can make the entire browser-visible payment leg deterministic while the account/login leg is configured.
            new SubscriptionPage(page, ConfigReader.getProperty("baseUrl")).open();
            PaymentPage payment = new PaymentPage(page);
            payment.openStub("UPI");
            if (!"SUCCESS".equals(payment.result())) throw new AssertionError("Expected successful test payment");
            return;
        }

        if ("sandbox".equalsIgnoreCase(paymentMode) && !ConfigReader.getProperty("paymentSandboxUrl").isBlank()) {
            page.navigate(ConfigReader.getProperty("paymentSandboxUrl"));
            page.waitForTimeout(500);
            // Sandbox UI selectors should be implemented in PaymentPage after the approved sandbox flow is supplied.
            throw new SkipException("Sandbox URL configured. Add the approved sandbox payment selectors in PaymentPage before execution.");
        }

        throw new SkipException("Use paymentMode=stub for deterministic UI automation or configure an approved sandbox payment environment.");
    }
}
