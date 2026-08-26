package Pages;

import com.microsoft.playwright.*;
import Utilities.TestData;

/**
 * Account-specific login is intentionally isolated here. The page object supports
 * several common accessible labels, but the exact OTP/login journey must be recorded
 * from the authorized test account because production authentication can change.
 */
public class LoginPageA {
    private final Page page;
    public LoginPageA(Page page) { this.page = page; }

    public void clickLoginIfPresent() {
        Locator l = page.getByText("Log in", new Page.GetByTextOptions().setExact(true)).first();
        if (l.count() > 0 && l.isVisible()) l.click();
    }

    public boolean hasLoginForm() {
        return page.getByText("Log in", new Page.GetByTextOptions().setExact(true)).count() > 0
                || page.locator("input[type='tel']").count() > 0
                || page.locator("input[type='email']").count() > 0;
    }

    public void fillIfSimpleLoginFormExists() {
        String username = TestData.username();
        String password = TestData.password();
        if (username.isBlank() || password.isBlank()) {
            throw new IllegalStateException("Set UNACADEMY_USERNAME and UNACADEMY_PASSWORD for an authorized test account.");
        }
        Locator email = page.locator("input[type='email']").first();
        if (email.count() > 0 && email.isVisible()) email.fill(username);
        Locator pwd = page.locator("input[type='password']").first();
        if (pwd.count() > 0 && pwd.isVisible()) pwd.fill(password);
    }
}
