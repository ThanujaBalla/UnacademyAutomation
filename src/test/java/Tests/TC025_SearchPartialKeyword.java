package Tests;

import org.testng.annotations.Test;

import Base.SearchBaseTest;
import Pages.SearchPage;

public class TC025_SearchPartialKeyword extends SearchBaseTest {

    @Test
    public void TC025_SearchPartialKeyword() {

        SearchPage searchPage = new SearchPage(driver, wait);

        searchPage.enterSearchText("Ja");

        searchPage.verifySearchSuggestionsDisplayed();
    }
}