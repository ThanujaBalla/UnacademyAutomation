package Pages;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
public class GoalSelectionPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By iitJeeGoal = By.xpath("//*[@id='MIBRP']/div/div[1]");
    private By goalExploreButton =
            By.xpath("//*[@id='__next']/header/div[1]/div[1]/div[2]/div/button");

    private By neetUGCard =
            By.xpath("//*[@id='MIBRP']/div/div[1]");
    
    
    public GoalSelectionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public boolean isIITJEEDisplayed() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(iitJeeGoal)
        ).isDisplayed();
    }

    public void selectIITJEE() {
        WebElement iitJEE = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='MIBRP']/div/div[1]")
            )
        );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block: 'center', inline: 'center'});",
            iitJEE
        );

        wait.until(ExpectedConditions.elementToBeClickable(iitJEE));

        iitJEE.click();
    }
    public void openGoalSelection() {

        WebElement button = wait.until(
            ExpectedConditions.visibilityOfElementLocated(goalExploreButton)
        );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block: 'center', inline: 'center'});",
            button
        );

        wait.until(ExpectedConditions.elementToBeClickable(button));

        button.click();
    }

    public void selectNEETUG() {

        WebElement neet = wait.until(
            ExpectedConditions.visibilityOfElementLocated(neetUGCard)
        );

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block: 'center', inline: 'center'});",
            neet
        );

        wait.until(ExpectedConditions.elementToBeClickable(neet));

        neet.click();
    }
}