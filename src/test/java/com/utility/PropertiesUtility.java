package com.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.constants.Env;

public class PropertiesUtility {
	//read properties file!!
	
	public static String readProperty(Env env, String propertyName)  {
		
		FileReader fileReader = null;
		Properties properties = new Properties();
		
		File proFile = new File(System.getProperty("user.dir") + File.separator + "config" + File.separator + env + ".properties");
		
		try {
			fileReader = new FileReader(proFile);
			properties.load(fileReader);
		} catch (IOException e) {

			 throw new RuntimeException("❌ Failed to read properties", e);
			
		}
		return properties.getProperty(propertyName.toUpperCase());
	}
}
