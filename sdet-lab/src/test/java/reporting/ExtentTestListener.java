package reporting;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentTestListener implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        ExtentManager.getReporter(); // инициализация
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentManager.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        ExtentTest test = ExtentManager.getReporter().createTest(className + " :: " + testName);
        ExtentManager.setTest(test);

        // Логи: параметры дата-провайдера, если есть
        Object[] params = result.getParameters();
        if (params != null && params.length > 0) {
            test.log(Status.INFO, "Parameters: " + java.util.Arrays.toString(params));
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().pass("Test passed ✅");
        ExtentManager.unload();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = ExtentManager.getTest();
        test.fail(result.getThrowable());

        // Пытаемся достать WebDriver из теста или BaseTest (часто кладут в ITestContext)
        WebDriver driver = extractDriver(result);
        if (driver != null) {
            String screenshotPath = takeScreenshot(driver, result.getMethod().getMethodName());
            try {
                test.fail("Screenshot on failure",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } catch (Exception ignore) {
            }
        } else {
            test.info("WebDriver is null, screenshot not attached.");
        }
        ExtentManager.unload();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.getTest().skip("Test skipped ⚠️");
        ExtentManager.unload();
    }

    // --- helpers ---

    private WebDriver extractDriver(ITestResult result) {
        // Популярные варианты:
        // 1) поле driver в BaseTest
        try {
            Object instance = result.getInstance();
            java.lang.reflect.Field f = instance.getClass().getSuperclass().getDeclaredField("driver");
            f.setAccessible(true);
            return (WebDriver) f.get(instance);
        } catch (Exception ignored) {
        }

        // 2) driver в самом тестовом классе
        try {
            Object instance = result.getInstance();
            java.lang.reflect.Field f = instance.getClass().getDeclaredField("driver");
            f.setAccessible(true);
            return (WebDriver) f.get(instance);
        } catch (Exception ignored) {
        }

        // 3) из ITestContext (если где-то положили)
        try {
            ITestContext ctx = result.getTestContext();
            Object d = ctx.getAttribute("driver");
            if (d instanceof WebDriver) return (WebDriver) d;
        } catch (Exception ignored) {
        }

        return null;
    }

    private String takeScreenshot(WebDriver driver, String methodName) {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String folder = System.getProperty("user.dir") + "/target/extent-report/screenshots";
            Path dir = Paths.get(folder);
            if (!Files.exists(dir)) Files.createDirectories(dir);

            String filePath = folder + "/" + methodName + "_" + timestamp + ".png";
            byte[] src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Files.write(Paths.get(filePath), src);
            return filePath;
        } catch (Exception e) {
            return null;
        }
    }
}
