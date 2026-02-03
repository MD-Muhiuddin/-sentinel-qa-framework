package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.utility.BrowserUtility;
import com.utility.LoggerUtility;

public class LoginPage extends BrowserUtility {
	
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	public LoginPage() {
	
		
	}
	
	private static final By EMAIL_TEXT_FIELD_LOCATOR = By.id("userName");
	
	private static final By PASSWORD_TEXT_FIELD_LOCATOR = By.id("password");
	
	private static final By SUBMIT_BUTTON_LOCATOR = By.id("login");
	
	
	
	
	@Override
    public boolean PageLoadedSuccessfully() {    
        
        return isElementDisplayed(EMAIL_TEXT_FIELD_LOCATOR) 
            && isElementDisplayed(PASSWORD_TEXT_FIELD_LOCATOR) 
            && isElementDisplayed(SUBMIT_BUTTON_LOCATOR);
    }
	
	public MyAccountPage doLoginWith(String userName,String password) {
		
		if(PageLoadedSuccessfully()) {
			logger.info("Login page Loaded successfully... ");
			enterText(EMAIL_TEXT_FIELD_LOCATOR, userName);
			enterText(PASSWORD_TEXT_FIELD_LOCATOR,password);
			clickOn(SUBMIT_BUTTON_LOCATOR);
		}
		else {
			logger.warn("\"❌ Login Page Elements missing! Cannot attempt login.");
		}

		return new MyAccountPage();
	}
	

}
