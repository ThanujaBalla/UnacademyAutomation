package Tests;

import org.testng.annotations.Test;

import Base.SearchBaseTest;
import Pages.SearchPage;

public class TC020_SearchValidCourse extends SearchBaseTest {

    @Test
    public void TC020_SearchValidCourse() {

        SearchPage searchPage = new SearchPage(driver, wait);

        searchPage.enterSearchText("UPSC");

        searchPage.selectSearchSuggestion();
    }
}