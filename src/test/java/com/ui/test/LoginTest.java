package com.ui.test;


import com.ui.pages.MyAccountPage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;

import static com.constants.Browser.*;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/*good Test case features !!!
 * 1.Test Script should be small
 * 2.you can't write conditional statement,loops,try catch, in your test
 * 3.TestScripts----> Test Steps
 * 4.Reduce the use of local variables
 * 5.At least one assertion
  */



@Listeners(com.ui.listeners.TestListener.class) // i can attatck multiple listeners
public class LoginTest extends TestBase{
	
	
	
	@Test(description = "Verifies with the valid user is able to login into the application ",
			groups = {"Login Test","sanity"},dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
			dataProvider = "LoginTestJsonDataProvider",retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
	public void loginJsonTest(User user ){
		 
		  MyAccountPage myAccountPage = homePage.goToLoginPage().doLoginWith(user.getUserName(),user.getPassword());
		 
		  Assert.assertEquals(myAccountPage.getProfileUserName(), user.getUserName());
		  
	}
	
	@Test(description = "Verifies with the valid and invalid user login test using CSV Data",
			groups = {"Login Test","sanity"},dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,dataProvider = "LoginTestCSVDataProvider"
			,retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
	public void loginCSVTest(User user ){
		 
		  
		  MyAccountPage myAccountPage = homePage.goToLoginPage().doLoginWith(user.getUserName(),user.getPassword());
		  
		  boolean isPageLoaded = myAccountPage.PageLoadedSuccessfully();
		  
		    Assert.assertTrue(isPageLoaded, "❌ Login Failed: 'My Account' page did not load successfully.");

		    String actualUserName = myAccountPage.getProfileUserName();
		    Assert.assertEquals(actualUserName, user.getUserName(), "❌ Username Mismatch on Profile Page:");

		
	}
	
//	@Test(description = "Verifies with the valid and invalid user is able to login into the application  using EXcel data",
//			groups = {"Login Test","sanity"},dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,dataProvider = "LoginTestExcelDataProvider")
//	public void loginExcelTest(User user ){
//		
//		  MyAccountPage myAccountPage = homePage.goToLoginPage().doLoginWith(user.getUserName(),user.getPassword());
//		  Assert.assertEquals(myAccountPage.getProfileUserName(), user.getUserName());
//		
//		
//	}

}
