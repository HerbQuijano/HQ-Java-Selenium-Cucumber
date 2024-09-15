package org.hquijano.testcomponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.hquijano.utilityclasses.ExtentReporterNG;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.log4testng.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Listeners extends BaseTest implements ITestListener {
    ExtentReports reports = ExtentReporterNG.getReportObject();
    ExtentTest test;
    Logger logger;

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        test = reports.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        test.log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        test.log(Status.FAIL, "Test failed");

        try {
            BaseTest baseTest = (BaseTest) result.getInstance();
            String screenshotPath = baseTest.getScreenshot(result.getName());
            Path source = Paths.get(screenshotPath);
            test.addScreenCaptureFromPath(source.toString());
            //baseTest.getScreenshot(result.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        reports.flush();
    }
}
