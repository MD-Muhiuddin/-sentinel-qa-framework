package com.utility;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ui.pojo.Config;
import com.ui.pojo.Environment;

public class JsonConfigUtility {

    private static Config configData;

    // Private constructor to prevent instantiation
    private JsonConfigUtility() {}

    public static Environment getEnvData(String string) {
    	
    	ObjectMapper mapper = new ObjectMapper();
    	File file = new File(System.getProperty("user.dir") + File.separator + "config" + File.separator + "config.json");
            try {
                
                configData = mapper.readValue(file, Config.class);
                
                
            } catch (IOException e) {
                throw new RuntimeException("❌ Failed to read config.json", e);
            }
        
        return configData.getEnvironments().get(string);
        
    }

  
}








