package Pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;

public class SubscriptionPage {

    private final Page page;
    private final String baseUrl;

    public SubscriptionPage(Page page, String baseUrl) {
        this.page = page;
        this.baseUrl = baseUrl;
    }

    public void open() {

        String url =
                baseUrl + "/goal/gate-ese-ee-ec/JAQRK/subscriptions";

        page.navigate(
                url,
                new Page.NavigateOptions()
                        .setWaitUntil(
                                WaitUntilState.DOMCONTENTLOADED
                        )
        );

        page.waitForTimeout(3000);
    }

    public void scrollToPlans() {

        for (int i = 0; i < 8; i++) {
            page.mouse().wheel(0, 700);
            page.waitForTimeout(400);
        }
    }

    public boolean isPlanVisible(String planName) {

        return page.getByText(
                planName,
                new Page.GetByTextOptions()
                        .setExact(true)
        ).count() > 0;
    }
}