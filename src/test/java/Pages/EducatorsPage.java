package Pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitUntilState;

public class EducatorsPage {

    private final Page page;
    private final String url;

    public EducatorsPage(Page page, String baseUrl) {
        this.page = page;
        this.url = baseUrl + "/goal/gate-ese-ee-ec/JAQRK/educators";
    }

    public void open() {
        page.navigate(
                url,
                new Page.NavigateOptions()
                        .setWaitUntil(WaitUntilState.DOMCONTENTLOADED)
        );
    }

    public boolean isHeaderVisible() {

        Locator header = page.getByRole(
                AriaRole.HEADING,
                new Page.GetByRoleOptions()
                        .setName("GATE & ESE - EE, EC Educators")
        );

        return header.count() > 0
                && header.first().isVisible();
    }

    public void scrollThroughCards() {

        for (int i = 0; i < 5; i++) {
            page.mouse().wheel(0, 900);
            page.waitForTimeout(600);
        }
    }

    public int visibleLinks() {
        return page.locator("a").count();
    }
}