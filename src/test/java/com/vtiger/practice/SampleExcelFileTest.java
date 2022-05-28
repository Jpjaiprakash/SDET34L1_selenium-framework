package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class SampleExcelFileTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		//Step1: Specify the path
		FileInputStream fis=new FileInputStream("./src/test/resources/WorksheetTest.xlsx");
		
		//Step2: Open excel
		Workbook w=WorkbookFactory.create(fis);
		
		//step3: Specify sheetname
		Sheet s = w.getSheet("Sheet1");
		
		//Step4: Specify rowcount
		Row r = s.getRow(0);
		
		//Step5: Specify cellcount
		Cell c = r.getCell(1);
		
		String data = c.getStringCellValue();
		
		System.out.println(data);
		
		
		

	}

}
