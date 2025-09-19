package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.base.BaseUiTest;
import ui.pages.LoginPage;

public class SmokeLoginTest extends BaseUiTest {
    @Test
    public void loginPageLoads() {
        boolean shown = new LoginPage().openLogin(baseUrl).isLoaded();
        Assert.assertTrue(shown, "Login page is not visible");
    }
}
