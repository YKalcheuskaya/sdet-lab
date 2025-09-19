package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ui.base.BaseUiTest;
import ui.pages.*;

public class E2ECheckoutTest extends BaseUiTest {

    @Test
    public void canPlaceOrder() {
        // передай логин/пароль через Maven: -Demail= -Dpassword=
        String email = System.getProperty("email", "");
        String password = System.getProperty("password", "");
        String product = System.getProperty("product", "ZARA COAT 3");

        ProductCataloguePage catalogue = new LoginPage()
                .openLogin(baseUrl)
                .loginAs(email, password);

        CartPage cart = catalogue.addProductToCart(product).goToCart();
        Assert.assertTrue(cart.contains(product), "Product not found in cart");

        ConfirmationPage confirmation = cart
                .proceedToCheckout()
                .selectCountry("United States")
                .placeOrder();

        Assert.assertTrue(
                confirmation.successMessage().toLowerCase().contains("thank"),
                "No success confirmation"
        );
    }
}

