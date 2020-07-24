package tdd;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UserNamePassWord {
	
	// From this example you can learn logger ,data driven testing , POI , some FILE IO and Usage of DataProvider
	@DataProvider(name = "getUserNamePassword")
	Object[][] getUserLoginData() throws FileNotFoundException, IOException {
		Logger logger = Logger.getLogger(UserNamePassWord.class);
		String [][] userNamePasswordTable=new String[2][2];
		String UserName;
		String Password;
		logger.info("test started");
		XSSFWorkbook book = new XSSFWorkbook(new FileInputStream("E://selenium.xlsx"));
		XSSFSheet sheet1 = book.getSheet("Sheet1");
		int numberOfRows = sheet1.getLastRowNum();
		// XSSFCell cell1 = sheet1.getRow(1).getCell(0);
		// System.out.println("User Name : " + cell1.getStringCellValue());
		// XSSFCell cell2 = sheet1.getRow(1).getCell(1);
		// System.out.println(cell2.getNumericCellValue());
		// System.out.println("Pass word :" +cell2.getStringCellValue());
		int i = 0;
		int j=0;
		int rowindex = 1;
		int columnindex = 0;
		while (i < numberOfRows) {
			UserName = sheet1.getRow(rowindex).getCell(columnindex).getStringCellValue();
			Password = sheet1.getRow(rowindex).getCell(++columnindex).getStringCellValue();
			rowindex++;
			columnindex = 0;
			//System.out.println("User Name : " + UserName);
			userNamePasswordTable[i][j]=UserName;
			//System.out.println("Pass word :" + Password);
			userNamePasswordTable[i][j+1]=Password;
			i++;
		}
		// cell2.setCellValue(34);
		// FileOutputStream fileOut = new FileOutputStream("E://selenium.xlsx");
		//book.write(fileOut);
		book.close();
		// fileOut.flush();
		// fileOut.close();
		logger.info("Data providing complete");
		return userNamePasswordTable;
		

	}
	@Test(dataProvider="getUserNamePassword")
	void PrintUserNamePassword(String UserName,String Password) {
		System.out.println("User Name "+UserName);
		System.out.println("Password "+ Password);
	}
	
}
