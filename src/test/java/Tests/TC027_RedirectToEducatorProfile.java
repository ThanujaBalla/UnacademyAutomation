package Tests;

import org.testng.annotations.Test;

import Base.SearchBaseTest;
import Pages.SearchPage;

public class TC027_RedirectToEducatorProfile extends SearchBaseTest {

    @Test
    public void TC027_RedirectToEducatorProfile() {

        SearchPage searchPage = new SearchPage(driver, wait);

        searchPage.enterSearchText("Kiran");

        searchPage.selectEducator("Kiran Gayakwad");
    }
}