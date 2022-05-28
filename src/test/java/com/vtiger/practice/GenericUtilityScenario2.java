package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
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
import org.openqa.selenium.support.ui.Select;

import com.sdet34L1.genericUtility.ExcelUtility;
import com.sdet34L1.genericUtility.FileUtility;
import com.sdet34L1.genericUtility.IconstantPath;
import com.sdet34L1.genericUtility.JavaUtility;
import com.sdet34L1.genericUtility.WebDriverUtility;
import com.vtiger.objectRepository.CreateNewOrganizationPage;
import com.vtiger.objectRepository.HomePage;
import com.vtiger.objectRepository.LOginPage;
import com.vtiger.objectRepository.OrganizationInformationPage;
import com.vtiger.objectRepository.OrganizationPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GenericUtilityScenario2  {

	public static void main(String[] args) throws IOException, InterruptedException {
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
		
		String data=ExcelUtility.getDataFromExcel("Sheet1", 1, 1)+randomNumber;

		
		switch(browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.chromedriver().setup();
			driver=new FirefoxDriver();
			break;

		default:System.out.println("please specify proper browser key");
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		break;
		}
		
		LOginPage loginpage=new LOginPage(driver);
		HomePage homepage=new HomePage(driver);
		OrganizationPage createorganization=new OrganizationPage(driver);
		CreateNewOrganizationPage organizationData=new CreateNewOrganizationPage(driver);
		OrganizationInformationPage oip=new OrganizationInformationPage(driver);
		
		WebDriverUtility.navigateApp(url, driver);
		WebDriverUtility.browserSetting(longTimeOut, driver);
		
	

		//driver.findElement(By.name("user_name")).sendKeys(username);
		//driver.findElement(By.name("user_password")).sendKeys(password);
		//driver.findElement(By.id("submitButton")).click();
		loginpage.loginAction(username, password);
		
		//driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		homepage.clickorganizationsTab();
		
		
		//driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		createorganization.clickOnOrganizationTab();
		
		//driver.findElement(By.name("accountname")).sendKeys(data);
		organizationData.organizationData(data);
		
		//WebElement ele2 = driver.findElement(By.name("industry"));
		//WebDriverUtility.initializeDropDown(ele2);
		//WebDriverUtility.selectByVisibleText(ele2,"Education");
		organizationData.industryDrop("Education");
		
		
		//WebElement ele3 = driver.findElement(By.name("accounttype"));
		//WebDriverUtility.initializeDropDown(ele3);
		//WebDriverUtility.selectByVisibleText(ele3,"Press");
		organizationData.typeDrop("Press");
		
		/*Select s1=new Select(ele3);
		s1.selectByValue("Press");
		*/
		//driver.findElement(By.name("button")).click();
		organizationData.organizationSave();
		Thread.sleep(5000);
		
		
		 //WebElement actname = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		jutil.assertionThroughIfCondition(oip.getNewOrganizationName(), data, "organization is created");
		/*if(actname.contains(data)) {
			System.out.println("pass");
		}
		else {
			System.out.println("false");
		}
		Thread.sleep(5000);
		*///mousehover
	
		//WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		//WebDriverUtility.mouseHoverOnTheElement(ele, driver);
		//driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		homepage.signout(driver);
		WebDriverUtility.quit(driver);
	}

}

