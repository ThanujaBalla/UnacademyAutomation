package Tests;

import org.testng.annotations.Test;

import Base.SearchBaseTest;
import Pages.SearchPage;

public class TC021_SearchValidInstructor extends SearchBaseTest {

    @Test
    public void TC021_SearchValidInstructor() {

        SearchPage searchPage = new SearchPage(driver, wait);

        searchPage.enterSearchText("Roman Saini");
    }
}