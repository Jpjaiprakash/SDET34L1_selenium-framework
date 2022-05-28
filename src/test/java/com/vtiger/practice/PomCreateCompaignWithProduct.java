package com.vtiger.practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sdet34L1.genericUtility.ExcelUtility;
import com.sdet34L1.genericUtility.FileUtility;
import com.sdet34L1.genericUtility.IconstantPath;
import com.sdet34L1.genericUtility.JavaUtility;
import com.sdet34L1.genericUtility.WebDriverUtility;
import com.vtiger.objectRepository.CampaignInformationPage;
import com.vtiger.objectRepository.CampaignPage;
import com.vtiger.objectRepository.CreateNewCampaignPage;
import com.vtiger.objectRepository.CreateNewProductPage;
import com.vtiger.objectRepository.CreateProductPage;
import com.vtiger.objectRepository.HomePage;
import com.vtiger.objectRepository.LOginPage;
import com.vtiger.objectRepository.ProductInformationPage;
import com.vtiger.objectRepository.SearchProductsPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PomCreateCompaignWithProduct {

	public static void main(String[] args) throws IOException {
		JavaUtility jutil=new JavaUtility();
		WebDriver driver=null;
		FileUtility.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
		ExcelUtility.openExcel(IconstantPath.EXCELFILEPATH);
		
		String url = FileUtility.getDataFromPropertyFile("url");
		String username =FileUtility.getDataFromPropertyFile("username");
		String password = FileUtility.getDataFromPropertyFile("password");
		String timeout =FileUtility.getDataFromPropertyFile("timeout");
		String browser = FileUtility.getDataFromPropertyFile("browser");

		long longTimeOut = jutil.stringToLong(timeout);
		int randomNumber=jutil.getRandomNumber(1000);
		
		String campaignname=ExcelUtility.getDataFromExcel("Sheet1", 3, 1)+randomNumber;
		String productname=ExcelUtility.getDataFromExcel("Sheet1", 4, 1)+randomNumber;
		
		switch(browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;

		default:System.out.println("please specify proper browser key");
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		break;
		}
		LOginPage loginpage=new LOginPage(driver);
		HomePage homepage=new HomePage(driver);
		CampaignPage newcampaign=new CampaignPage(driver);
		CreateNewCampaignPage campaignName=new CreateNewCampaignPage(driver);
		CreateProductPage newproduct=new CreateProductPage(driver);
		CreateNewProductPage nameofproduct=new CreateNewProductPage(driver);
		SearchProductsPage searchproduct=new SearchProductsPage(driver);
		CampaignInformationPage cip=new CampaignInformationPage(driver);
		ProductInformationPage pip=new ProductInformationPage(driver);
		
		
		
		WebDriverUtility.navigateApp(url, driver);
		WebDriverUtility.browserSetting(longTimeOut, driver);
		WebDriverUtility.explicitlyWait(driver, longTimeOut);
		
		//driver.findElement(By.name("user_name")).sendKeys(username);
		//driver.findElement(By.name("user_password")).sendKeys(password);
		//driver.findElement(By.id("submitButton")).click();
		loginpage.loginAction(username, password);
		
		
		//driver.findElement(By.xpath("//a[text()='Products']")).click();
		homepage.clickproducts();
		
		
		//driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		newproduct.clickOnCreateProduct();
		
		//driver.findElement(By.name("productname")).sendKeys(productname);
		//driver.findElement(By.xpath("//img[@title='Save [Alt+S]'])")).click();
		nameofproduct.enterProductName(productname);
		
		
		WebDriverUtility.waitUntillElementClickable(driver.findElement(By.linkText("More")));
		
		//WebElement moreLink = driver.findElement(By.linkText("More"));
		//WebDriverUtility.mouseHoverOnTheElement(moreLink, driver);
		//driver.findElement(By.name("Campaigns")).click();
		homepage.clickCampaign(driver);
		
		
		
		//driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
		newcampaign.CreateCampaign();
		
		//driver.findElement(By.name("campaignname")).sendKeys(campaignname);
		campaignName.CreateCampaignAction(campaignname);
		
		
		//driver.findElement(By.xpath("//td[contains(.,'Product') and @class='dvtCellLabel']/following-sibling::td/img")).click();
		newcampaign.CreateCampaign();
		campaignName.enterCampaignNameAndSwitchToSearchProduct(campaignname, driver);
		
		//WebDriverUtility.switchToWindowBasedTitle(driver,"Products");
		//driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(productname);
		//driver.findElement(By.xpath("//input[@class='crmbutton small create']")).click();
		//driver.findElement(By.xpath("//a[.='"+productname+"']")).click();
		searchproduct.selectProduct(productname, driver);
		WebDriverUtility.switchToWindowBasedTitle(driver,"Compaigns");
		
		//driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		campaignName.saveCampaign();
		
		
		//WebElement ActualcampaignName = driver.findElement(By.id("dtlview_Campaign Name"));
		//WebElement ActualProductName = driver.findElement(By.id("dtlview_Product Name"));
		
		jutil.assertionThroughIfCondition(cip.getCampaignName(), campaignname, "campaign with product");
		jutil.assertionThroughIfCondition(pip.getProductName(), productname, "");
		
		//WebElement administrationIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		//WebDriverUtility.mouseHoverOnTheElement(administrationIcon, driver);
		//driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		homepage.signout(driver);
		WebDriverUtility.quit(driver);	

	}

}

