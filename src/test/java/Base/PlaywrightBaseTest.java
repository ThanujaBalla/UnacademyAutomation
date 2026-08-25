package Base;

public abstract class PlaywrightBaseTest extends BaseTest {

    @Override
    protected String getEngine() {
        return "playwright";
    }
}