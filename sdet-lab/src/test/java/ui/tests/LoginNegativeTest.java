package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.base.BaseUiTest;
import ui.pages.LoginPage;

public class LoginNegativeTest extends BaseUiTest {

    @Test
    public void wrongPasswordShowsErrorToast() {
        String email = System.getProperty("email", System.getenv("UI_EMAIL"));
        String bad = System.getProperty("badPassword", "DefinitelyWrong123!");
        String msg = new LoginPage()
                .openLogin(baseUrl)
                .loginExpectingError(email, bad);

        Assert.assertTrue(msg.toLowerCase().contains("incorrect"),
                "Expected error toast, but got: " + msg);
    }
}
