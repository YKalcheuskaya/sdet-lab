package ui.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(css = "[placeholder='Select Country']")
    private WebElement countryInput;

    @FindBy(css = ".ta-results button:nth-of-type(1)")
    private WebElement firstCountryOption;

    @FindBy(css = ".action__submit")
    private WebElement placeOrderBtn;

    public CheckoutPage selectCountry(String country) {
        type(countryInput, country);
        countryInput.sendKeys(Keys.ARROW_DOWN);
        click(firstCountryOption);
        return this;
    }

    public ConfirmationPage placeOrder() {
        click(placeOrderBtn);
        return new ConfirmationPage();
    }
}
