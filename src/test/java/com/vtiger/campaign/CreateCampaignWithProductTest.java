package com.vtiger.campaign;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.sdet34L1.genericUtility.BaseClass;
import com.sdet34L1.genericUtility.ExcelUtility;
import com.sdet34L1.genericUtility.WebDriverUtility;
import com.vtiger.objectRepository.CampaignInformationPage;
import com.vtiger.objectRepository.CampaignPage;
import com.vtiger.objectRepository.CreateNewCampaignPage;
import com.vtiger.objectRepository.CreateNewProductPage;
import com.vtiger.objectRepository.CreateProductPage;
import com.vtiger.objectRepository.ProductInformationPage;
import com.vtiger.objectRepository.SearchProductsPage;

public class CreateCampaignWithProductTest extends BaseClass {
	CampaignPage newcampaign;
	CreateNewCampaignPage campaignName;
	CreateProductPage newproduct;
	CreateNewProductPage nameofproduct;
	SearchProductsPage searchproduct;
	CampaignInformationPage campaigninformationpage;
	ProductInformationPage productinformationpage;
	String campaignname;
	String productname;

	@Test
	public void CreateCampaignWithProductBaseTest() throws EncryptedDocumentException, IOException {
		newcampaign=new CampaignPage(driver);
		campaignName=new CreateNewCampaignPage(driver);
		newproduct=new CreateProductPage(driver);
		nameofproduct=new CreateNewProductPage(driver);
		searchproduct=new SearchProductsPage(driver);
		campaigninformationpage=new CampaignInformationPage(driver);
		productinformationpage=new ProductInformationPage(driver);
		campaignname=ExcelUtility.getDataFromExcel("Sheet1", 3, 1)+randomNumber;
		productname=ExcelUtility.getDataFromExcel("Sheet1", 4, 1)+randomNumber;

		homepage.clickproducts();
		newproduct.clickOnCreateProduct();
		nameofproduct.enterProductName(productname);
		WebDriverUtility.waitUntillElementClickable(driver.findElement(By.linkText("More")));
		homepage.clickCampaign(driver);
		newcampaign.CreateCampaign();
		campaignName.CreateCampaignAction(campaignname);
		newcampaign.CreateCampaign();
		WebDriverUtility.explicitlyWait(driver, longTimeOut);
		campaignName.enterCampaignNameAndSwitchToSearchProduct(campaignname, driver);
		searchproduct.selectProduct(productname, driver);
		WebDriverUtility.switchToWindowBasedTitle(driver,"Compaigns");
		campaignName.saveCampaign();
		jutil.assertionThroughIfCondition(campaigninformationpage.getCampaignName(), campaignname, "campaign with product");
		jutil.assertionThroughIfCondition(productinformationpage.getProductName(), productname, "");

	}
}
