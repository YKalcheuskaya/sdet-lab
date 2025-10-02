package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static synchronized ExtentReports getReporter() {
        if (extent == null) {
            // Путь к отчёту: ./target/extent-report/extent.html
            String reportPath = System.getProperty("user.dir") + "/target/extent-report/extent.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("UI/API Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // Общие системные данные (видно в отчёте)
            extent.setSystemInfo("Project", "sdet-lab");
            extent.setSystemInfo("Environment", System.getProperty("env", "local"));
            extent.setSystemInfo("Executor", System.getProperty("user.name"));
        }
        return extent;
    }

    public static synchronized void flush() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static synchronized ExtentTest getTest() {
        return test.get();
    }

    public static synchronized void setTest(ExtentTest t) {
        test.set(t);
    }

    public static synchronized void unload() {
        test.remove();
    }
}



