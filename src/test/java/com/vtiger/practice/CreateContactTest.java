package com.vtiger.practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.sdet34L1.genericUtility.BaseClass;
import com.sdet34L1.genericUtility.ExcelUtility;
import com.vtiger.objectRepository.ContactInformationPage;
import com.vtiger.objectRepository.ContactsPage;
import com.vtiger.objectRepository.CreateNewContactPage;

public class CreateContactTest extends BaseClass {
	
	String firstname;
	String lastname;
	ContactsPage newcontactbtn;
	CreateNewContactPage data;
	ContactInformationPage valid;
	
	@Test(groups="sanity")
	public void createContact() throws EncryptedDocumentException, IOException {
	
	 firstname=ExcelUtility.getDataFromExcel("Sheet1", 0, 1)+randomNumber;
	 lastname=ExcelUtility.getDataFromExcel("Sheet1", 1, 1)+randomNumber;
	newcontactbtn=new ContactsPage(driver);
	 data=new CreateNewContactPage(driver);
	 valid=new ContactInformationPage(driver);

	homepage.clickcontactsTab();
	newcontactbtn.clickcreatecontact();
	data.CreateNewContactData(firstname, lastname);
	jutil.assertionThroughIfCondition(valid.getFirstName(), firstname, "contact");
	jutil.assertionThroughIfCondition(valid.getLastName(), lastname, "");
	}	
}
