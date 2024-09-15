package org.hquijano.utilityclasses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
    public static ExtentReports getReportObject() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("src/test/resources/extent/report.html");
        reporter.config().setReportName("Heroku TestNG Automation");
        reporter.config().setDocumentTitle("Heroku TestNG Automation Report");
        ExtentReports reports = new ExtentReports();
        reports.attachReporter(reporter);
        reports.setSystemInfo("Environment", "QA");
        return reports;
    }
}