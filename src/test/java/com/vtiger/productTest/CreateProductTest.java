package com.vtiger.productTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.sdet34L1.genericUtility.BaseClass;
import com.sdet34L1.genericUtility.ExcelUtility;
import com.vtiger.objectRepository.CampaignInformationPage;
import com.vtiger.objectRepository.CampaignPage;
import com.vtiger.objectRepository.CreateNewCampaignPage;
import com.vtiger.objectRepository.CreateNewProductPage;
import com.vtiger.objectRepository.CreateProductPage;
import com.vtiger.objectRepository.ProductInformationPage;

public class CreateProductTest extends BaseClass {
	
	String fis1;
	CreateProductPage newproduct;
	CreateNewProductPage nameofproduct;
	ProductInformationPage productinformationpage;
	
	
	@Test
	public void CreateProductFromExcelandPfBaseTest() throws EncryptedDocumentException, IOException {
	 fis1=ExcelUtility.getDataFromExcel("Sheet1", 1, 1)+randomNumber;
	newproduct=new CreateProductPage(driver);
	nameofproduct=new CreateNewProductPage(driver);
	productinformationpage=new ProductInformationPage(driver);
	
	
	
	homepage.clickproducts();
	newproduct.clickOnCreateProduct();
	nameofproduct.enterProductName(fis1);
	jutil.assertionThroughIfCondition(productinformationpage.getProductName(), fis1, "product");

}
}
