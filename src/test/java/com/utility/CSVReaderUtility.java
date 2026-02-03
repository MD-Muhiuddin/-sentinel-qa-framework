package com.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;



public class CSVReaderUtility {

	public static Iterator<User> readCSVFile(String fileName) {
		
		File csvFile = new File(System.getProperty("user.dir") + File.separator + "testData" + File.separator + fileName);
		
		FileReader fileReader = null;
		CSVReader  csvReader ;
		String [] line ;
		List<User> userDataList = null;
		User userData;
		
		try {
			fileReader = new FileReader(csvFile);
			csvReader  = new CSVReader(fileReader);
			
			csvReader.readNext();//header skipping purpose
			
			
			userDataList = new ArrayList<User>();
			
			while((line = csvReader.readNext()) != null) {				
				userData = new User(line[0],line[1]);
				userDataList.add(userData);
			}
			
//			for(User eachUserData : userDataList) {
//				System.out.println(eachUserData);
//			}
//			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (CsvValidationException | IOException e) {
			e.printStackTrace();
		}  		
	
	 return userDataList.iterator();	
	}
}
