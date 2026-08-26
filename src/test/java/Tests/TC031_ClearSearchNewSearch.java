package Tests;

import org.testng.annotations.Test;

import Base.SearchBaseTest;
import Pages.SearchPage;

public class TC031_ClearSearchNewSearch extends SearchBaseTest {

    @Test
    public void TC031_ClearSearchNewSearch() {

        SearchPage searchPage = new SearchPage(driver, wait);

        // First search
        searchPage.enterSearchText("UPSC");

        searchPage.selectSearchSuggestion();

        // Clear previous search
        searchPage.clearSearch();

        // New search
        searchPage.enterSearchText("GATE");

        searchPage.selectSearchSuggestion();
    }
}