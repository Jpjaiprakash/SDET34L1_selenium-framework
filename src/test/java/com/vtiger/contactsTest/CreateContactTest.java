package com.vtiger.contactsTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.sdet34L1.genericUtility.BaseClass;
import com.sdet34L1.genericUtility.ExcelUtility;
import com.vtiger.objectRepository.ContactInformationPage;
import com.vtiger.objectRepository.ContactsPage;
import com.vtiger.objectRepository.CreateNewContactPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class CreateContactTest extends BaseClass {
	
	/*String firstname;
	String lastname;
	ContactsPage newcontactbtn;
	CreateNewContactPage data;
	ContactInformationPage valid;
	*/
	
	/*@Test(groups="sanity", description="testng:-CreateContactTest ")
	@Description("Descriprtion:- CreateContactTest")
	@Epic("Epic:-CreateContactTest")
	@Story("Story:-CreateContactTest")
	@Step("Step:-CreateContactTest")
	@Severity(SeverityLevel.BLOCKER)
	*/
	@Test(groups="sanity")
	public void createContact() throws EncryptedDocumentException, IOException {
	
		String firstname=ExcelUtility.getDataFromExcel("Sheet1", 0, 1)+randomNumber;
		String lastname=ExcelUtility.getDataFromExcel("Sheet1", 1, 1)+randomNumber;
		ContactsPage newcontactbtn=new ContactsPage(driver);
		CreateNewContactPage  data=new CreateNewContactPage(driver);
		ContactInformationPage valid=new ContactInformationPage(driver);

	homepage.clickcontactsTab();
	newcontactbtn.clickcreatecontact();
	data.CreateNewContactData(firstname, lastname);
	jutil.assertionThroughIfCondition(valid.getFirstName(), firstname, "contact");
	jutil.assertionThroughIfCondition(valid.getLastName(), lastname, "");
	}	
}
