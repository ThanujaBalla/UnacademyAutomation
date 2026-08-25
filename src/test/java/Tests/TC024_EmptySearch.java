package Tests;

import org.testng.annotations.Test;

import Base.SearchBaseTest;
import Pages.SearchPage;

public class TC024_EmptySearch extends SearchBaseTest {

    @Test
    public void TC024_EmptySearch() {

        SearchPage searchPage = new SearchPage(driver, wait);

        searchPage.clearSearch();
    }
}