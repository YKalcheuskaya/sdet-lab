package ui.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.base.DriverFactory;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final By overlay = By.cssSelector(".ngx-spinner-overlay");

    protected BasePage() {
        this.driver = DriverFactory.get();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected void waitForOverlayToDisappear() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(overlay));
        } catch (TimeoutException ignored) {
        }
    }

    protected String textOf(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    protected void safeClick(WebElement el) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(el)).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
    }

    protected String getText(WebElement el) {
        return visible(el).getText();
    }

    protected void open(String url) {
        driver.get(url);
    }

    protected void click(WebElement e) {
        wait.until(ExpectedConditions.elementToBeClickable(e)).click();
    }

    protected void type(WebElement e, String t) {
        wait.until(ExpectedConditions.visibilityOf(e)).clear();
        e.sendKeys(t);
    }

    protected WebElement visible(WebElement e) {
        return wait.until(ExpectedConditions.visibilityOf(e));
    }
}
