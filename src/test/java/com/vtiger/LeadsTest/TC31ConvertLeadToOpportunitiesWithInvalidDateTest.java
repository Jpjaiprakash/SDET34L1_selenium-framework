package com.vtiger.LeadsTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.sdet34L1.genericUtility.BaseClass;
import com.sdet34L1.genericUtility.ExcelUtility;
import com.sdet34L1.genericUtility.WebDriverUtility;
import com.vtiger.objectRepository.ConvertLeadPage;
import com.vtiger.objectRepository.CreateNewLeadPage;
import com.vtiger.objectRepository.LeadInformationPage;
import com.vtiger.objectRepository.LeadsPage;

public class TC31ConvertLeadToOpportunitiesWithInvalidDateTest extends BaseClass{
	String closedate;
	String lastname;
	String companyname;
	LeadsPage lead;
	LeadInformationPage information;
	ConvertLeadPage convert;
	CreateNewLeadPage newlead;
	
	
@Test
public void ConvertLeadToOpportunitiesWithInvalidDateTest() throws EncryptedDocumentException, IOException, InterruptedException {
	
	closedate=ExcelUtility.getDataFromExcel("TC31", 0, 1)+randomNumber;
	 lastname=ExcelUtility.getDataFromExcel("TC31", 1, 1)+randomNumber;
  companyname=ExcelUtility.getDataFromExcel("TC31", 2, 1)+randomNumber;
	
	 lead=new LeadsPage(driver);
	 information=new LeadInformationPage(driver);
	 convert=new ConvertLeadPage(driver);
	 newlead=new CreateNewLeadPage(driver);
	homepage.clickLeadsTab();
	lead.clickOnLead();
	newlead.enterlastnameTf(lastname);
	newlead.entercompanynameTf(companyname);
	newlead.leadsaveBtn();
	information.clickOnConvertLeadBtn();
	Thread.sleep(10000);
	convert.clickOpportunityCheckBox();
	convert.closingDateofLead(closedate);
	convert.clickOnConvertLeadSaveBtn();
	
	
}
}
