package com.vtiger.LeadsTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.sdet34L1.genericUtility.BaseClass;
import com.sdet34L1.genericUtility.ExcelUtility;
import com.vtiger.objectRepository.ConvertLeadPage;
import com.vtiger.objectRepository.CreateNewLeadPage;
import com.vtiger.objectRepository.LeadInformationPage;
import com.vtiger.objectRepository.LeadsPage;
import com.vtiger.objectRepository.ValidationTestcasesPage;

public class TC32ConvertLeadWithValidAmountTest extends BaseClass {
	
	String lastname;
	String companyname;
	String closingdate;
	String amount;
	LeadsPage lead;
	LeadInformationPage information;
	ConvertLeadPage convert;
	CreateNewLeadPage newlead;
	ValidationTestcasesPage validate;
	
	@Test
	public void ConvertLeadWithValidAmountTest() throws EncryptedDocumentException, IOException, InterruptedException {
		 lastname=ExcelUtility.getDataFromExcel("TC31", 1, 1)+randomNumber;
		 companyname=ExcelUtility.getDataFromExcel("TC31", 2, 1)+randomNumber;
		 closingdate=ExcelUtility.getDataFromExcel("TC32", 0, 1)+randomNumber;
		 amount=ExcelUtility.getDataFromExcel("TC32", 1, 1)+randomNumber;
		
		 lead=new LeadsPage(driver);
		 information=new LeadInformationPage(driver);
		convert=new ConvertLeadPage(driver);
		 newlead=new CreateNewLeadPage(driver);
		 validate=new ValidationTestcasesPage(driver);
		
		homepage.clickLeadsTab();
		lead.clickOnLead();
		newlead.enterlastnameTf(lastname);
		newlead.entercompanynameTf(companyname);
		newlead.leadsaveBtn();
		information.clickOnConvertLeadBtn();
		Thread.sleep(10000);
		convert.clickOpportunityCheckBox();
		convert.closingDateofLead(closingdate);
		convert.amountTf(amount);
		convert.clickOnConvertLeadSaveBtn();
		jutil.assertionThroughIfCondition(validate.getleadlastname(), lastname, "organization");
	}

}
