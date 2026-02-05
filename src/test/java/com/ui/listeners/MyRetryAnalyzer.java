package com.ui.listeners;

import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import com.utility.JsonConfigUtility;
import com.utility.LoggerUtility;

public class MyRetryAnalyzer implements IRetryAnalyzer {

    // 1. We initialize this variable dynamically inside the Constructor or a static block
    private static int maxRetryCount;

    static {
        // PRIORITY 1: Check if "retryCount" is passed directly from Maven (-DretryCount=3)
        String directRetry = System.getProperty("retryCount");
        
        if (directRetry != null) {
            maxRetryCount = Integer.parseInt(directRetry);
            
        } else {
            // PRIORITY 2: Check "ENV" variable and read from Config.json
            String env = System.getProperty("ENV");
            
            // Default to "DEV" if running from IDE without settings
            if (env == null || env.isEmpty()) {
                env = "DEV";
            }
            
            // Fetch from JSON (e.g., QA -> 2 retries)
            try {
                maxRetryCount = JsonConfigUtility.getEnvData(env.toUpperCase()).getMAX_NUMBER_OF_ATTEMPTS();
            } catch (Exception e) {
                maxRetryCount = 1; // Safety Fallback if JSON fails
            }
        }
    }

    private int currentAttempt = 1;
    Logger logger = LoggerUtility.getLogger(this.getClass());

    @Override
    public boolean retry(ITestResult result) {
        if (currentAttempt <= maxRetryCount) {
            logger.info("***********************************************************************************");
            logger.warn("🔄 RETRYING TEST: " + result.getMethod().getMethodName());
            logger.info("   Attempt No    : " + currentAttempt + " / " + maxRetryCount);
            logger.info("************************************************************************************");
            currentAttempt++;
            return true;
        }
        return false;
    }
}