package Tests;

import org.testng.annotations.Test;

import Base.SearchBaseTest;
import Pages.SearchPage;

public class TC026_TrendingEducators extends SearchBaseTest {

    @Test
    public void TC026_TrendingEducators() {

        SearchPage searchPage = new SearchPage(driver, wait);

        searchPage.verifyTrendingSearchesDisplayed();
    }
}