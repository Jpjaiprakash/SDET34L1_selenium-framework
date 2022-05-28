package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchMultipleDataByExcelTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		//String[] arr={"hi","welcome","to","tyss"};

		FileInputStream fis=new FileInputStream("./src/test/resources/multiple.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet("multipledata");

		String[][] arr = new String[sh.getRow(1).getLastCellNum()][sh.getRow(1).getLastCellNum()];
		for(int i=1; i<sh.getLastRowNum();i++) {
			for(int j=5;j<sh.getRow(i).getLastCellNum();j++) {
				arr[i][j]=sh.getRow(i).getCell(j).getStringCellValue();

			}

		}

		System.out.println(arr[1][5]);
		System.out.println(arr[1][6]);
		System.out.println(arr[2][5]);
		System.out.println(arr[2][6]);
		System.out.println(arr[3][5]);
		System.out.println(arr[3][6]);


	}

}
