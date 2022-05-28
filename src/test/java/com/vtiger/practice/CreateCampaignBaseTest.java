package com.vtiger.practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hpsf.HPSFException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.sdet34L1.genericUtility.BaseClass;
import com.sdet34L1.genericUtility.ExcelUtility;
import com.sdet34L1.genericUtility.FileUtility;
import com.sdet34L1.genericUtility.IconstantPath;
import com.sdet34L1.genericUtility.JavaUtility;
import com.sdet34L1.genericUtility.WebDriverUtility;
import com.vtiger.objectRepository.CampaignInformationPage;
import com.vtiger.objectRepository.CampaignPage;
import com.vtiger.objectRepository.CreateNewCampaignPage;
import com.vtiger.objectRepository.HomePage;
import com.vtiger.objectRepository.LOginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignBaseTest extends BaseClass{
	
	String campaignname;
	CampaignPage cp;
	CreateNewCampaignPage ccp;
	CampaignInformationPage cip;
	


		@Test
		public void CreateCampaignBaseTest() throws EncryptedDocumentException, IOException {
		 campaignname=ExcelUtility.getDataFromExcel("Sheet1", 0, 1)+randomNumber;
		 cp=new CampaignPage(driver);
		 ccp=new CreateNewCampaignPage(driver);
		 cip=new CampaignInformationPage(driver);
		
	
		homepage.clickCampaign(driver);
		cp.CreateCampaign();
		ccp.CreateCampaignAction(campaignname);
		jutil.assertionThroughIfCondition(cip.getCampaignName(), campaignname, "campaign");

	}

}
