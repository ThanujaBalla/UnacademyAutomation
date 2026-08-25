package Base;

import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

public class SearchBaseTest extends BaseTest {

    protected WebDriverWait wait;

    private By searchInput = By.cssSelector(
            "input[placeholder='Search courses, test series and educators']"
    );

    @BeforeMethod
    public void searchSetup() {

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );

        driver.get("https://unacademy.com/");

        System.out.println();
        System.out.println("======================================");
        System.out.println("Search Test Setup");
        System.out.println("======================================");

        System.out.println(
                "Current URL: " + driver.getCurrentUrl()
        );

        System.out.println(
                "Page Title: " + driver.getTitle()
        );

        /*
         * First check whether the Search box is already available.
         *
         * If it is available, the user is already able to use
         * Search and no login is required.
         */
        try {

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            searchInput
                    )
            );

            System.out.println();
            System.out.println(
                    "Search box is available."
            );

            System.out.println(
                    "Login is not required."
            );

            System.out.println(
                    "Continuing Search test..."
            );

        } catch (Exception e) {

            /*
             * Search box was not available.
             *
             * This can happen when Unacademy displays the
             * login/authentication screen.
             *
             * Do NOT start the Search test yet.
             */
            System.out.println();
            System.out.println(
                    "Search box is not available."
            );

            System.out.println(
                    "Login/authentication may be required."
            );

            System.out.println();
            System.out.println(
                    "Please login to Unacademy manually."
            );

            System.out.println(
                    "Complete the OTP/login process."
            );

            System.out.println();
            System.out.println(
                    "After login is completely finished,"
            );

            System.out.println(
                    "press ENTER in the Eclipse Console."
            );

            System.out.println();
            System.out.println(
                    "======================================"
            );

            Scanner scanner = new Scanner(System.in);

            scanner.nextLine();

            System.out.println();
            System.out.println(
                    "ENTER received."
            );

            System.out.println(
                    "Continuing Search test..."
            );

            System.out.println(
                    "Current URL after login: "
                    + driver.getCurrentUrl()
            );

            System.out.println(
                    "======================================"
            );
        }
    }
}