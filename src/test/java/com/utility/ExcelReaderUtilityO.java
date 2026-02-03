package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.User;



public class ExcelReaderUtilityO {
	public static Iterator<User> readExcleFile(String filename) {
		
		File xlsxFile = new File(System.getProperty("user.dir") + File.separator + "testData" + File.separator + filename);
		XSSFWorkbook xssfWorkbook = null;
		XSSFSheet xssfSheet;
		Iterator<Row> rowIterator; 
		Row row;
		Cell userNameCell;
		Cell passwordCell;
		List<User> userDataList = null;
		
		
		try {
			xssfWorkbook = new XSSFWorkbook(xlsxFile);
			xssfSheet    = xssfWorkbook.getSheet("testData01");
			rowIterator  = xssfSheet.iterator();
			userDataList = new ArrayList<User>();
			
			
			rowIterator.next(); //skip column  headers
			while(rowIterator.hasNext()) {
				row = rowIterator.next();
				userNameCell =  row.getCell(0);
				passwordCell = row.getCell(1);
				User userData = new User(userNameCell.toString(),passwordCell.toString());
				userDataList.add(userData);
			}
			
			xssfWorkbook.close();
			
			
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	return userDataList.iterator();
	}
}
