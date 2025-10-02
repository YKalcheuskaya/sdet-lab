package ui.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> TL = new ThreadLocal<>();

    public static void init() {
        if (TL.get() == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions opts = new ChromeOptions();
            // opts.addArguments("--headless=new"); // включишь в CI
            TL.set(new ChromeDriver(opts));
            TL.get().manage().window().maximize();
        }
    }

    public static WebDriver get() {
        return TL.get();
    }

    public static void quit() {
        if (TL.get() != null) {
            TL.get().quit();
            TL.remove();
        }
    }
}
