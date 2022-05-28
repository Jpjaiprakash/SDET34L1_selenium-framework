package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.sdet34L1.genericUtility.ExcelUtility;
import com.sdet34L1.genericUtility.FileUtility;
import com.sdet34L1.genericUtility.IconstantPath;
import com.sdet34L1.genericUtility.JavaUtility;
import com.sdet34L1.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UsingGenericUtilityCreateCampaign {

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
		
		WebDriverUtility.navigateApp(url, driver);
		WebDriverUtility.browserSetting(longTimeOut, driver);
		
		
		//Login to vtiger
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		WebElement moreLink=driver.findElement(By.linkText("More"));
		WebDriverUtility.mouseHoverOnTheElement(moreLink,driver);
		

		driver.findElement(By.name("Campaigns")).click();
		driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
		
		//To add campaign name
		driver.findElement(By.name("campaignname")).sendKeys(campaignname);
		driver.findElement(By.name("assigntype")).click();
		
		//TO click on save button
		driver.findElement(By.name("button")).click();
		
		//To validate contact name
	    WebElement ActualcampaignName = driver.findElement(By.id("dtlview_Campaign Name"));
	    jutil.assertionThroughIfCondition(ActualcampaignName.getText(), campaignname, "campaign");
				
		Thread.sleep(5000);
		
		//mousehover
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		WebDriverUtility.mouseHoverOnTheElement(ele, driver);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		WebDriverUtility.quit(driver);
	}

}
