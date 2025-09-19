package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmationPage extends BasePage {

    @FindBy(css = ".hero-primary")
    private WebElement confirmationHeader;

    public String successMessage() {
        return getText(confirmationHeader);
    }
}
