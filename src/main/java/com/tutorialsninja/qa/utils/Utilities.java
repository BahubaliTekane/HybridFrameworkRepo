package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Properties;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public static final int IMPLICIT_WAIT_TIME = 10;

	public static final int PAGE_LOAD_WAIT_TIME = 5;
	
	static Properties excelProp;

	public static String generateEmailWithTimeStamp() {
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		String timeStamp = currentTimestamp.toString().replace("-", "_").replace(" ", "_").replace(":", "_")
				.replace(".", "_");
		return "admin" + timeStamp + "@xyz.com";
	}

	public static Object[][] getTestDataFromExcel(String sheetName) {
		excelProp=new Properties();
		File excelFile = new File(System.getProperty("user.dir") + "\\testdata\\TutorialsNinjaTestData.xlsx");
		XSSFWorkbook workbook = null;
		try {
			
			FileInputStream fisExcel = new FileInputStream(excelFile);
			excelProp.load(fisExcel);
			workbook = new XSSFWorkbook();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		XSSFSheet sheet = workbook.getSheet(sheetName);

		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][cols];

		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i + 1);
			for (int j = 0; j < cols; j++) {
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();

				switch (cellType) {

				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;

				}
			}

		}
		return data;
	}
	
	public static String captureScreenshot(WebDriver driver,String testName) {
		
		File srcScreenshot= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath= System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return destinationScreenshotPath;
	}
	
}
