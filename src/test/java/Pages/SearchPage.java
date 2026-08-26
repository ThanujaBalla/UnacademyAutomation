package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Search input
    private By searchInput = By.cssSelector(
            "input[placeholder='Search courses, test series and educators']"
    );

    // Search suggestions in dropdown
    private By searchSuggestions = By.cssSelector(
            "a[href^='/search/']"
    );

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void enterSearchText(String text) {

        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchInput)
        );

        searchBox.click();
        searchBox.clear();
        searchBox.sendKeys(text);

        System.out.println("Search text entered: " + text);
    }

    public void selectSearchSuggestion() {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchSuggestions)
        );

        List<WebElement> suggestions =
                driver.findElements(searchSuggestions);

        System.out.println(
                "Search suggestions found: " + suggestions.size()
        );

        if (!suggestions.isEmpty()) {

            WebElement firstSuggestion = suggestions.get(0);

            System.out.println(
                    "Selecting suggestion: " +
                    firstSuggestion.getText()
            );

            wait.until(
                    ExpectedConditions.elementToBeClickable(firstSuggestion)
            ).click();

        } else {
            throw new RuntimeException(
                    "No search suggestions were displayed."
            );
        }
    }
    
    public void clearSearch() {

        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchInput)
        );

        searchBox.click();
        searchBox.clear();

        System.out.println("Search field cleared.");
    }
    
    public void verifySearchSuggestionsDisplayed() {

        List<WebElement> suggestions = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        searchSuggestions
                )
        );

        System.out.println(
                "Number of suggestions displayed: " + suggestions.size()
        );

        if (suggestions.isEmpty()) {
            throw new RuntimeException(
                    "No search suggestions were displayed."
            );
        }

        for (WebElement suggestion : suggestions) {
            System.out.println(
                    "Suggestion: " + suggestion.getText()
            );
        }
    }
    public void verifyTrendingSearchesDisplayed() {

        // Click the search box
        WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(searchInput)
        );

        searchBox.click();

        // Trending heading
        By trendingHeading = By.xpath(
                "//*[normalize-space(text())='Trending']"
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(trendingHeading)
        );

        System.out.println("Trending section is displayed.");

        // Trending items
        By trendingItems = By.cssSelector(
                "a[href^='/search/']"
        );

        List<WebElement> items = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        trendingItems
                )
        );

        if (items.isEmpty()) {
            throw new RuntimeException(
                    "No trending search items were displayed."
            );
        }

        System.out.println(
                "Trending items displayed: " + items.size()
        );

        for (WebElement item : items) {

            String text = item.getText().trim();

            if (!text.isEmpty()) {
                System.out.println(
                        "Trending item: " + text
                );
            }
        }
    }
    
    public void selectEducator(String educatorName) {

        By educator = By.xpath(
                "//a[starts-with(@href, '/@')]"
                + "[.//p[normalize-space()='" + educatorName + "']]"
        );

        WebElement educatorElement = wait.until(
                ExpectedConditions.elementToBeClickable(educator)
        );

        System.out.println(
                "Selecting educator: " + educatorName
        );

        educatorElement.click();
    }
}