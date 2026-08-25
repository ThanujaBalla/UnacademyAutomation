package Tests;

import org.testng.annotations.Test;

import Base.SearchBaseTest;
import Pages.SearchPage;

public class TC030_SearchResultLoadTime extends SearchBaseTest {

    @Test
    public void TC030_SearchResultLoadTime() {

        SearchPage searchPage = new SearchPage(driver, wait);

        long startTime = System.currentTimeMillis();

        searchPage.enterSearchText("UPSC");

        searchPage.selectSearchSuggestion();

        long endTime = System.currentTimeMillis();

        long responseTime = endTime - startTime;

        System.out.println(
                "Search result load time: "
                + responseTime
                + " ms"
        );
    }
}