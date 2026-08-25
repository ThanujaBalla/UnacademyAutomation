package Tests;

import org.testng.annotations.Test;

import Base.SearchBaseTest;
import Pages.SearchPage;

public class TC023_SearchInvalidKeyword extends SearchBaseTest {

    @Test
    public void TC023_SearchInvalidKeyword() {

        SearchPage searchPage = new SearchPage(driver, wait);

        searchPage.enterSearchText("xyz@123");

        searchPage.selectSearchSuggestion();
    }
}