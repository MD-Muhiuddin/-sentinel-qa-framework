package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.utility.BrowserUtility;
import com.utility.LoggerUtility;



public class MyAccountPage extends BrowserUtility {

	public MyAccountPage() {
		// We don't need super(driver) anymore.
        // Implicitly calls super() -> BrowserUtility() -> Grabs ThreadLocal driver

	}
	
	Logger logger = LoggerUtility.getLogger(this.getClass());

	private static final By SIGN_OUT_BUTTON_LOCATOR = By.id("submit");
	private static final By ACCOUNT_USER_NAME_LOCATOR = By.xpath("//label[@id='userName-value']");
	private static final By GO_TO_BOOK_STORE = By.xpath("//button[@id='gotoStore']");

	@Override
	public boolean PageLoadedSuccessfully() {
		
		if(isElementDisplayed(ACCOUNT_USER_NAME_LOCATOR)) {
			logger.info("MyAccountPage Page Loaded Successfully✅");
			
			return true;
		}
		
		return false;
	}

	public LoginPage logOut() {
		
		if(PageLoadedSuccessfully())
		clickOn(SIGN_OUT_BUTTON_LOCATOR);
		logger.info("👋 User logged out.");
		return new LoginPage();
	}

	public String getProfileUserName() {
		if(PageLoadedSuccessfully()) {
			String userName = getVisibleText(ACCOUNT_USER_NAME_LOCATOR);
			logger.info("ProfileName 👤:"+userName);
		return userName;
		}
		return null;
	}

	public boolean signInConfirmation() {
		return isElementDisplayed(SIGN_OUT_BUTTON_LOCATOR) && isElementDisplayed(GO_TO_BOOK_STORE);
	}

}
