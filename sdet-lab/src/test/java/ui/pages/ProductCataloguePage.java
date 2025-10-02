package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class ProductCataloguePage extends BasePage {

    @FindBy(css = ".card-body")
    private List<WebElement> productCards;

    @FindBy(css = "[routerlink*='cart']")
    private WebElement cartIcon;

    private final By cardTitle = By.cssSelector("b");
    private final By addBtn = By.cssSelector("button.btn.w-10"); // актуальная кнопка «Add To Cart»
    private final By toast = By.cssSelector("#toast-container");
    private final By spinner = By.cssSelector(".ng-animating");

    /**
     * Ждём загрузку сетки товаров
     */
    public ProductCataloguePage waitUntilLoaded() {
        wait.withTimeout(Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElements(productCards));
        return this;
    }

    /**
     * Добавляем конкретный товар и ждём тост + исчезновение спиннера
     */
    public ProductCataloguePage addProductToCart(String productName) {
        waitUntilLoaded();
        for (WebElement card : productCards) {
            String name = card.findElement(cardTitle).getText().trim();
            if (name.equalsIgnoreCase(productName)) {
                WebElement add = card.findElement(addBtn);
                click(add);
                wait.until(ExpectedConditions.visibilityOfElementLocated(toast));
                wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
                waitForOverlayToDisappear();
                break;
            }
        }
        return this;
    }

    public CartPage goToCart() {
        waitForOverlayToDisappear();
        safeClick(cartIcon);
        //click(cartIcon);
        return new CartPage();
    }
}
