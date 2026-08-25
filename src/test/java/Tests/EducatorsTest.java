package Tests;

import Base.PlaywrightBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.EducatorsPage;
import Utilities.ConfigReader;

public class EducatorsTest extends PlaywrightBaseTest {
    @Test
    public void educatorsPageLoadsAndScrolls() {
        EducatorsPage educators = new EducatorsPage(page, ConfigReader.getProperty("baseUrl"));
        educators.open();
        Assert.assertTrue(educators.isHeaderVisible(), "Educators heading should be visible");
        educators.scrollThroughCards();
        Assert.assertTrue(educators.visibleLinks() > 0, "Educator page should contain links");
    }
}
