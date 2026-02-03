package com.ui.test;

import static com.constants.Browser.CHROME;
import static com.constants.Browser.FIREFOX;

import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LoggerUtility;

public class TestBase {

	protected HomePage homePage;
	Logger logger = LoggerUtility.getLogger(this.getClass());

	// 🎛️ CONTROL PANEL
	// Set these to TRUE or FALSE to control your entire framework

	@Parameters({ "browser", "isLambdaTest","isHeadLess" })
	@BeforeMethod(description = "Load the Home page of the website")

	public void setup(
			@Optional("chrome") String browser, 
			@Optional("false") boolean isLambdaTest,
			@Optional("true")  boolean isHeadLess, ITestResult result) {

		String testName = result.getMethod().getMethodName();

		logger.info(
				"⚙️ SETUP: Initializing Test Environment [Cloud: " + isLambdaTest + " | Headless: " + isHeadLess + "]");

		// ✅ ONE LINE TO RULE THEM ALL
		// The HomePage/BrowserUtility now handles the 'if/else' logic internally
		homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadLess, isLambdaTest, testName);

		logger.info("🛠 SETUP: Session Created Successfully ✅.");
	}

	@AfterMethod
	public void tearDown() {
		if (homePage != null && homePage.getDriver() != null) {
			homePage.quitBrowser();
		}
	}

	public BrowserUtility getInstance() {
		return homePage;
	}

}
