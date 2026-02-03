package com.utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.Logger; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.constants.Env;

public class LamdaTestUtility {
    
    // Best Practice: Add Logger
    private static final Logger logger = LoggerUtility.getLogger(LamdaTestUtility.class);
    public static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
    
    
    //  FACTORY METHOD: Just creates and returns the object
    public static WebDriver initializeLambdaTestSession(String browserName, String testName, boolean isHeadLess) {
    	
    	String AccessToken = PropertiesUtility.readProperty(Env.DEV, "accesstoken");
    	String UserName = PropertiesUtility.readProperty(Env.DEV, "userName");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion", "latest");
        
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("user", UserName); 
        ltOptions.put("accessKey", AccessToken);
        ltOptions.put("build", "Selenium 4 Framework");
        ltOptions.put("name", testName);
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "latest");

        // ✅ LOGIC CHECK: Toggles Video/Visuals based on Headless flag
        if (isHeadLess) {
            ltOptions.put("visual", false);
            ltOptions.put("video", false);
        } else {
            ltOptions.put("visual", true);
            ltOptions.put("video", true);
        }

        capabilities.setCapability("LT:Options", ltOptions);

        try {
            logger.info("☁️  Connecting to LambdaTest Hub...");
            // Simply return the driver. BrowserUtility will store it in ThreadLocal.
            return new RemoteWebDriver(new URL(HUB_URL), capabilities);
        } catch (MalformedURLException e) {
            logger.error("🚨 Invalid Hub URL", e);
            return null;
        }
    }
    
    
}
