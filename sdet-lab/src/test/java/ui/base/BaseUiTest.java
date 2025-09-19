package ui.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseUiTest {
    protected WebDriver driver;
    protected String baseUrl;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        ui.base.DriverFactory.init();
        driver = ui.base.DriverFactory.get();
        baseUrl = System.getProperty("baseUrl", "https://rahulshettyacademy.com/client");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        ui.base.DriverFactory.quit();
    }
}
