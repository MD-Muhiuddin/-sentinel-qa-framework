package com.ui.listeners;

import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.Status;
import com.ui.test.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;

public class TestListener implements ITestListener {

    Logger logger = LoggerUtility.getLogger(this.getClass());

    @Override
    public void onStart(ITestContext context) {
        logger.info("╔════════════════════════════════════════════════════════════════════════════════╗");
        logger.info("║         🌟           TEST SUITE EXECUTION STARTED          🌟                  ║");
        logger.info("╚════════════════════════════════════════════════════════════════════════════════╝");
        ExtentReporterUtility.setupSparkReporter("report.html");
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("----------------------------------------------------------------------------------");
        logger.info("🚀 TEST STARTED: " + result.getMethod().getMethodName());
        logger.info("   Description : " + result.getMethod().getDescription());
        logger.info("   Groups      : " + Arrays.toString(result.getMethod().getGroups()));
        logger.info("----------------------------------------------------------------------------------");
        
        ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("🟢[TEST 🅿 🅰 🆂 🆂 🅴 🅳 : " + result.getMethod().getMethodName()+"🟢");
        ExtentReporterUtility.getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("❌ TEST 🅵 🅰 🅸 🅻 🅴 🅳 : " + result.getMethod().getMethodName()+"❌");
        logger.error("   Reason      : " + result.getThrowable().getMessage());


        ExtentReporterUtility.getTest().log(Status.FAIL, result.getThrowable().getMessage());

        // --- SCREENSHOT LOGIC ---
        try {
            Object testClass = result.getInstance();
            BrowserUtility browserUtility = ((TestBase) testClass).getInstance();
            
            logger.info("📸 Attempting to capture screenshot...");
            String screenShotPath = browserUtility.takeScreenShot(result.getMethod().getMethodName());
            
            if (screenShotPath != null) {
                ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenShotPath);
                logger.info("   Screenshot attached successfully: " + screenShotPath);
            } else {
                logger.warn("   Screenshot was NULL (Browser might be closed or frozen).");
            }
        } catch (Exception e) {
            logger.error("   Failed to attach screenshot due to error: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("⚠️ [TEST SKIPPED]: " + result.getMethod().getMethodName()+"Test skipped due to an initial failure; re-executing....");
        ExtentReporterUtility.getTest().log(Status.SKIP, "Test marked as SKIPPED because of a prior failure; retry mechanism triggered.");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("╔════════════════════════════════════════════════════════════════════════════════╗");
        logger.info("║   🟢🌟                 TEST SUITE EXECUTION FINISHED           🌟🟢            ║");
        logger.info("╚════════════════════════════════════════════════════════════════════════════════╝");
        ExtentReporterUtility.flushReport();
    }
}
