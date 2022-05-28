package com.vtiger.campaign;

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
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class CreateCampaignTest extends BaseClass{
	
	
	
	/*String campaignname;
	CampaignPage campaignpage;
	CreateNewCampaignPage createcampaignpage;
	CampaignInformationPage campaigninformationpage;
	
*/

		/*@Test(groups="sanity", description="testng:-CreateCampaignTest ")
		@Description("Descriprtion:- CreateCampaignTest")
		@Epic("Epic:-CreateCampaignTest")
		@Story("Story:-CreateCampaignTest")
		@Step("Step:-CreateCampaignTest")
		@Severity(SeverityLevel.BLOCKER)
		*/
		@Test(groups="sanity")
		public void CreateCampaignBaseTest() throws EncryptedDocumentException, IOException {
			String campaignname=ExcelUtility.getDataFromExcel("Sheet1", 0, 1)+randomNumber;
			CampaignPage campaignpage=new CampaignPage(driver);
			CreateNewCampaignPage createcampaignpage=new CreateNewCampaignPage(driver);
			CampaignInformationPage campaigninformationpage=new CampaignInformationPage(driver);
		
	
		homepage.clickCampaign(driver);
		campaignpage.CreateCampaign();
		createcampaignpage.CreateCampaignAction(campaignname);
		jutil.assertionThroughIfCondition(campaigninformationpage.getCampaignName(), campaignname, "campaign");

	}

}
