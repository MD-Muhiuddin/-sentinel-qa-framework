package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ui.pojo.TestData;
import com.ui.pojo.User;

public class JSONReaderUtility {

    public static Iterator<User> readJSONFile(String fileName) {

    	File jsonFile = new File(System.getProperty("user.dir") + File.separator + "testData" + File.separator + fileName);
        ObjectMapper objectMapper = new ObjectMapper();

        // Always initialize to avoid NPE
        List<User> userDataList = new ArrayList<>();

        try {
           
            TestData testData = objectMapper.readValue(jsonFile, TestData.class);

            if (testData != null && testData.getData() != null) {
                userDataList.addAll(testData.getData());
            }

        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to read JSON test data: " + fileName, e);
        }

        return userDataList.iterator();
    }
}
