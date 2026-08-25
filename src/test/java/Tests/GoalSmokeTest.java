package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.GoalPage;
import Utilities.ConfigReader;
public class GoalSmokeTest extends BaseTest {
    @Test
    public void goalPageAndBatchesLoad() {
        GoalPage goal = new GoalPage(page, ConfigReader.getProperty("baseUrl"));
        goal.open();
        Assert.assertTrue(page.url().contains("/goal/gate-ese-ee-ec/JAQRK"));
        goal.scrollDown(900);
        goal.openBatches();
        Assert.assertTrue(page.url().contains("/batches"));
        Assert.assertTrue(page.locator("body").innerText().toLowerCase().contains("batch"));
    }
}
