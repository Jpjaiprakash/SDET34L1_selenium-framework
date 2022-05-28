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
import com.vtiger.objectRepository.HomePage;
import com.vtiger.objectRepository.LOginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PomCreateCampaign {

	public static void main(String[] args) throws InterruptedException, IOException {
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
		
		String campaignname=ExcelUtility.getDataFromExcel("Sheet1", 0, 1)+randomNumber;
		
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
		
		
		WebDriverUtility.browserSetting(longTimeOut, driver);
	
		
		
		//Login to vtiger
		LOginPage lp=new LOginPage(driver);
		HomePage hp=new HomePage(driver);
		CampaignPage cp=new CampaignPage(driver);
		CreateNewCampaignPage ccp=new CreateNewCampaignPage(driver);
		CampaignInformationPage cip=new CampaignInformationPage(driver);
		
		
		WebDriverUtility.navigateApp(url, driver);
		lp.loginAction(username, password);
		hp.clickCampaign(driver);
		
		

		//WebElement moreLink=driver.findElement(By.linkText("More"));
		//WebDriverUtility.mouseHoverOnTheElement(moreLink,driver);
		//driver.findElement(By.name("Campaigns")).click();
		//river.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
		
		cp.CreateCampaign();
		
		//To add campaign name
		//driver.findElement(By.name("campaignname")).sendKeys(campaignname);
		//driver.findElement(By.name("assigntype")).click();
		ccp.CreateCampaignAction(campaignname);
		
		
		
		//TO click on save button
		//driver.findElement(By.name("button")).click();
		
		//To validate contact name
	    
	    jutil.assertionThroughIfCondition(cip.getCampaignName(), campaignname, "campaign");
				
		Thread.sleep(5000);
		
		
		hp.signout(driver);
		WebDriverUtility.quit(driver);
		
	}

	}


