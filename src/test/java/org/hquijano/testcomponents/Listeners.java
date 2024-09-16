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

    // This line declares a ThreadLocal variable named extentTestInstance that
    // holds instances of ExtentTest.
    // ThreadLocal ensures that each thread has its own instance of ExtentTest
    ThreadLocal<ExtentTest> extentTestInstance = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        test = reports.createTest(result.getMethod().getMethodName());
        // This sets the ExtentTest instance in the ThreadLocal variable,
        // ensuring that each thread has its own instance of ExtentTest.
        extentTestInstance.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        // The get() method retrieves the ExtentTest instance from the ThreadLocal variable.
        extentTestInstance.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        extentTestInstance.get().log(Status.FAIL, "Test failed");
        extentTestInstance.get().fail(result.getThrowable());

        try {
            BaseTest baseTest = (BaseTest) result.getInstance();
            String screenshotPath = baseTest.getScreenshot(result.getName());
            Path source = Paths.get(screenshotPath);
            extentTestInstance.get().addScreenCaptureFromPath(source.toString());
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
