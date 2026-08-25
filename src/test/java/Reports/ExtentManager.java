package Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public final class ExtentManager {

    private ExtentManager() {
    }

    public static ExtentReports createReport() {

        String reportPath =
                System.getProperty("user.dir")
                + File.separator
                + "Reports"
                + File.separator
                + "ExtentTestReports.html";

        File reportFile = new File(reportPath);

        File parent = reportFile.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        ExtentSparkReporter reporter =
                new ExtentSparkReporter(reportPath);

        reporter.config().setReportName(
                "Unacademy Automation Test Report"
        );

        reporter.config().setDocumentTitle(
                "Unacademy Test Execution Report"
        );

        reporter.config().setTimeStampFormat(
                "dd-MM-yyyy HH:mm:ss"
        );

        ExtentReports extent =
                new ExtentReports();

        extent.attachReporter(reporter);

        extent.setSystemInfo(
                "Tester",
                "Unacademy Testing Team"
        );

        extent.setSystemInfo(
                "Application",
                "Unacademy"
        );

        extent.setSystemInfo(
                "Framework",
                "Selenium + Cucumber + TestNG"
        );

        extent.setSystemInfo(
                "Environment",
                "QA"
        );

        return extent;
    }
}