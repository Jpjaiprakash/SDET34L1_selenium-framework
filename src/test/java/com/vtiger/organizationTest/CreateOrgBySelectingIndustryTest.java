package com.vtiger.organizationTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.sdet34L1.genericUtility.BaseClass;
import com.sdet34L1.genericUtility.ExcelUtility;
import com.vtiger.objectRepository.CreateNewOrganizationPage;
import com.vtiger.objectRepository.OrganizationInformationPage;
import com.vtiger.objectRepository.OrganizationPage;

public class CreateOrgBySelectingIndustryTest extends BaseClass {
	String data;
	OrganizationPage createorganization;
	CreateNewOrganizationPage organizationData;
	OrganizationInformationPage oip;
	
	@Test
	public void CreateorganizationTest() throws EncryptedDocumentException, IOException {
		 data=ExcelUtility.getDataFromExcel("Sheet1", 1, 1)+randomNumber;
		createorganization=new OrganizationPage(driver);
		 organizationData=new CreateNewOrganizationPage(driver);
		 oip=new OrganizationInformationPage(driver);
		
		homepage.clickorganizationsTab();
		createorganization.clickOnOrganizationTab();
		organizationData.organizationData(data);
		organizationData.industryDrop("Education");
		organizationData.typeDrop("Press");
		organizationData.organizationSave();
		jutil.assertionThroughIfCondition(oip.getNewOrganizationName(), data, "organization is created");
	}

}
