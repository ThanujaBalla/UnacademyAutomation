package Tests;

import org.testng.annotations.Test;

import Base.SearchBaseTest;
import Pages.SearchPage;

public class TC028_SearchSpecialCharacters extends SearchBaseTest {

    @Test
    public void TC028_SearchSpecialCharacters() {

        SearchPage searchPage = new SearchPage(driver, wait);

        searchPage.enterSearchText("@#$%");

        searchPage.selectSearchSuggestion();
    }
}