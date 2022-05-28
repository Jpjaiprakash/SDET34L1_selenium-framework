package com.vtiger.contactsTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.sdet34L1.genericUtility.BaseClass;
import com.sdet34L1.genericUtility.ExcelUtility;
import com.sdet34L1.genericUtility.WebDriverUtility;
import com.vtiger.objectRepository.ContactsPage;
import com.vtiger.objectRepository.CreateNewContactPage;
import com.vtiger.objectRepository.CreateNewOrganizationPage;
import com.vtiger.objectRepository.OrganizationInformationPage;
import com.vtiger.objectRepository.OrganizationPage;
import com.vtiger.objectRepository.SearchOrgPage;

public class CreateContactWithOrganizationTest extends BaseClass {
	String orgname;
	String firstname;
	String lastname;
	OrganizationPage createorganization;
	CreateNewOrganizationPage organizationData;
	ContactsPage newcontactbtn;
	CreateNewContactPage data;
	SearchOrgPage orglookup;
	
	@Test
	public void CreateContactWithOrganizationTest() throws EncryptedDocumentException, IOException, InterruptedException {
		
		 orgname=ExcelUtility.getDataFromExcel("Sheet1", 7, 1)+randomNumber;
		 firstname=ExcelUtility.getDataFromExcel("Sheet1", 0, 1)+randomNumber;
		 lastname=ExcelUtility.getDataFromExcel("Sheet1", 1, 1)+randomNumber;
		
		 createorganization=new OrganizationPage(driver);
		 organizationData=new CreateNewOrganizationPage(driver);
		 
		 newcontactbtn=new ContactsPage(driver);
		 data=new CreateNewContactPage(driver);
		 orglookup=new SearchOrgPage(driver);
		 
		homepage.clickorganizationsTab();
		createorganization.clickOnOrganizationTab();
		organizationData.organizationData(orgname);
		organizationData.organizationSave();
		
		homepage.clickcontactsTab();
		newcontactbtn.clickcreatecontact();
		data.CreateNewContact1(firstname, lastname);
		orglookup.selectorganization(orgname, driver);
		data.ContactSaveBtn();
		
		
	}

}
