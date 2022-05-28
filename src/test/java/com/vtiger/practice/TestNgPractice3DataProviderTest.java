package com.vtiger.practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sdet34L1.genericUtility.ExcelUtility;
import com.sdet34L1.genericUtility.IconstantPath;

public class TestNgPractice3DataProviderTest {
	
	@Test(dataProvider="loginData")
	public void TestNgPractice3DataProviderTest(String username,String password) {
		Reporter.log(username+"  ------->    "+password,true);
		
	}
	
	@DataProvider
	public Object[][]loginData() throws EncryptedDocumentException, IOException{
		ExcelUtility.openExcel(IconstantPath.EXCELFILEPATH7);
		return ExcelUtility.getMultipleDataFromExcel("multipledata");
	}

}
