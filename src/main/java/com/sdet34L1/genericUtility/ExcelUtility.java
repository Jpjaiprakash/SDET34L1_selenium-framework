package com.sdet34L1.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;

/**
 * This class is used o maintain all excel specific common methods
 * @author User
 *
 */

public class ExcelUtility {
	static Workbook wb;
	/**
	 * This method is used to fetch the data from excel sheet
	 * @throws IOException 
	 * @throws EncryptedDocumentException 
	 */
	
	public static String getDataFromExcel(String sheetName, int rowNumber,int cellNumber) throws EncryptedDocumentException, IOException {

		String data = wb.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).getStringCellValue();
		return(data);
	}
	
	
	public static void openExcel(String filePath) throws EncryptedDocumentException, IOException {
	FileInputStream fisExcel=new FileInputStream(filePath);
	wb=WorkbookFactory.create(fisExcel);

	}
	
	/**
	 * THis method is used to set the value into Excel
	 */
	public static void setDataIntoExcel(String sheetName, int rowNumber,int cellNumber, String value) {
		wb.getSheet(sheetName).getRow(rowNumber).createCell(cellNumber).setCellValue(value);
	}
	
	/**
	 * This method is used to save the data in excel
	 * @throws FileNotFoundException 
	 */
	public static  void saveExcelData(String fileSavePath) throws FileNotFoundException {
		FileOutputStream fos=new FileOutputStream(fileSavePath);
		try {
			wb.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
		public static void closeExcel() {
			try {
			wb.close();
			}catch(IOException e) {
				e.printStackTrace();
				System.out.println("while closing excel some exceptions will occur please check");
			}
			
		}
		
		
		public static Object[][] getMultipleDataFromExcel(String sheetName){
			Sheet sh=wb.getSheet(sheetName);

			Object[][] arr = new Object[sh.getLastRowNum()][sh.getRow(0).getLastCellNum()];
			for(int i=0; i<sh.getLastRowNum();i++)
			{
				for(int j=0;j<sh.getRow(i).getLastCellNum();j++) 
				{
					arr[i][j]=sh.getRow(i).getCell(j).getStringCellValue();

				}
		}
			return arr;
		
		}


		public static void quitBrowser(WebDriver driver) {
			driver.quit();
			
		}
		
	}

