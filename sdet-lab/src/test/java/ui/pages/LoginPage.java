package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "userEmail")
    private WebElement email;
    @FindBy(id = "userPassword")
    private WebElement password;
    @FindBy(id = "login")
    private WebElement loginBtn;

    // тост с ошибкой логина
    private final By toast = By.cssSelector("#toast-container");

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

    /**
     * Логинимся с неверными данными и возвращаем текст тоста
     */
    public String loginExpectingError(String user, String pass) {
        type(email, user);
        type(password, pass);
        click(loginBtn);
        waitForOverlayToDisappear();
        return textOf(toast); // ждём тост и читаем текст
    }

    public boolean isLoaded() {
        return visible(email).isDisplayed();
    }
}
