package com.vtiger.organizationTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.sdet34L1.genericUtility.BaseClass;
import com.sdet34L1.genericUtility.ExcelUtility;
import com.vtiger.objectRepository.CreateNewOrganizationPage;
import com.vtiger.objectRepository.OrganizationInformationPage;
import com.vtiger.objectRepository.OrganizationPage;

public class CreateOrganizationTest extends BaseClass {
	
	String orgname;
	OrganizationPage createorganization;
	CreateNewOrganizationPage organizationData;
	OrganizationInformationPage oip;
	
	@Test(groups="sanity")
	public void createOrganizationTest() throws EncryptedDocumentException, IOException {
	
	 orgname=ExcelUtility.getDataFromExcel("Sheet1", 1, 1)+randomNumber;
	 createorganization=new OrganizationPage(driver);
	 organizationData=new CreateNewOrganizationPage(driver);
	oip=new OrganizationInformationPage(driver);
	
	homepage.clickorganizationsTab();
	createorganization.clickOnOrganizationTab();
	organizationData.organizationData(orgname);
	jutil.assertionThroughIfCondition(oip.getOrganization(), orgname , "organization");
	}
	

}
