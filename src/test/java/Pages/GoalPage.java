package Pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;

public class GoalPage {

    private final Page page;
    private final String url;

    public GoalPage(Page page, String baseUrl) {
        this.page = page;
        this.url = baseUrl + "/goal/gate-ese-ee-ec/JAQRK";
    }

    public void open() {

        page.navigate(
                url,
                new Page.NavigateOptions()
                        .setWaitUntil(WaitUntilState.DOMCONTENTLOADED)
        );
    }

    public void scrollDown(int pixels) {

        page.mouse().wheel(0, pixels);
        page.waitForTimeout(700);
    }

    public void openBatches() {

        page.navigate(
                url + "/batches",
                new Page.NavigateOptions()
                        .setWaitUntil(WaitUntilState.DOMCONTENTLOADED)
        );
    }

    public void openEducators() {

        page.navigate(
                url + "/educators",
                new Page.NavigateOptions()
                        .setWaitUntil(WaitUntilState.DOMCONTENTLOADED)
        );
    }

    public void openSubscriptionPlans() {

        page.navigate(
                url + "/subscriptions",
                new Page.NavigateOptions()
                        .setWaitUntil(WaitUntilState.DOMCONTENTLOADED)
        );
    }
}