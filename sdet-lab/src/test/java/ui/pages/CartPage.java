package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = ".cartSection h3")
    private List<WebElement> cartItems;

    @FindBy(css = ".totalRow button")
    private WebElement checkoutBtn;

    public boolean contains(String productName) {
        waitForOverlayToDisappear();
        wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));
        return cartItems.stream().anyMatch(el -> el.getText().equalsIgnoreCase(productName));
    }


    public CheckoutPage proceedToCheckout() {
        click(checkoutBtn);
        return new CheckoutPage();
    }
}
