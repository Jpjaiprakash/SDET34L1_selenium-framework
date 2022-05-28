package com.vtiger.DocumentsTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.sdet34L1.genericUtility.BaseClass;
import com.sdet34L1.genericUtility.ExcelUtility;
import com.vtiger.objectRepository.CreateNewDocumentPAge;
import com.vtiger.objectRepository.DocumentPage;
import com.vtiger.objectRepository.EnterDataToCreateDocument;

public class CreateDocumentTest extends BaseClass {
	
	String documentTitle;
	String documentpath;
	String documentdescription;
	DocumentPage documentTab;
	CreateNewDocumentPAge newDocument;
	EnterDataToCreateDocument titlename;
	
	
	
	@Test
	public void CreatedocumentTest() throws EncryptedDocumentException, IOException {
		
		 documentTitle =ExcelUtility.getDataFromExcel("document", 1, 1)+randomNumber;
		documentpath =ExcelUtility.getDataFromExcel("document", 1, 2)+randomNumber;
		 documentdescription =ExcelUtility.getDataFromExcel("document", 1, 3)+randomNumber;
		
		 documentTab=new DocumentPage(driver);
		 newDocument=new CreateNewDocumentPAge(driver);
		 titlename=new EnterDataToCreateDocument(driver);
		
		documentTab.clickOnDocumentsTab();
		newDocument.clickOnCreateDocument();
		titlename.EnterDocumentTitle(documentTitle);
		titlename.SwitchToTF(driver, 0, documentdescription);
		titlename.clickOnBoldBtn();
		titlename.clickOnItalicBtn();
		titlename.clickOnFilenameBtn(documentpath);

		titlename.clickOnDocumentSaveBtn();
		
		
		
		
		
		
		
	}
	
	
	

}
