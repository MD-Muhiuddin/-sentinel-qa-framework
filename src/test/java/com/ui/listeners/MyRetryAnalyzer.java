package com.ui.listeners;

import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Env;
import com.utility.JsonConfigUtility;
import com.utility.LoggerUtility;
import com.utility.PropertiesUtility;

public class MyRetryAnalyzer implements IRetryAnalyzer { // IRetryAnalyzer --> TestNG library

	// from json environment
	private static final int MAX_NUMBER_OF_ATTEMPTS = JsonConfigUtility.getEnvData("DEV").getMAX_NUMBER_OF_ATTEMPTS();

	// from properties(optional)
	// private static final int MAX_NUMBER_OF_ATTEMPTS =
	// Integer.parseInt(PropertiesUtility.readProperty(Env.QA,"MAX_NUMBER_OF_ATTEMPTS"));

	private int currentAttempt = 1;
	Logger logger = LoggerUtility.getLogger(this.getClass());

	@Override
	public boolean retry(ITestResult result) { // ITestResult gives me the Information about the test
		if (currentAttempt <= MAX_NUMBER_OF_ATTEMPTS) {
			logger.info("----------------------------------------------------------------------------------");
			logger.warn("🔄 RETRYING TEST: " + result.getMethod().getMethodName());
			logger.info("   Attempt No   : " + currentAttempt + " / " + MAX_NUMBER_OF_ATTEMPTS);
			logger.info("----------------------------------------------------------------------------------");
			currentAttempt++;
			return true;
		}
		return false;
	}

}
