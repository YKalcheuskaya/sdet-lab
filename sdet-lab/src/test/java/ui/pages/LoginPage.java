package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "userEmail")    private WebElement email;
    @FindBy(id = "userPassword") private WebElement password;
    @FindBy(id = "login")        private WebElement loginBtn;

    public LoginPage openLogin(String baseUrl) {
        open(baseUrl + "/login");
        return this;
    }

    public ProductCataloguePage loginAs(String user, String pass) {
        type(email, user);
        type(password, pass);
        click(loginBtn);
        return new ProductCataloguePage().waitUntilLoaded();
    }


    public boolean isLoaded() { return visible(email).isDisplayed(); }
}
