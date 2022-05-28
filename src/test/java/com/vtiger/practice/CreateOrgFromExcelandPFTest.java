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
import com.vtiger.objectRepository.CreateNewOrganizationPage;
import com.vtiger.objectRepository.HomePage;
import com.vtiger.objectRepository.LOginPage;
import com.vtiger.objectRepository.OrganizationInformationPage;
import com.vtiger.objectRepository.OrganizationPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrgFromExcelandPFTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		JavaUtility jutil=new JavaUtility();
		WebDriver driver=null;
		FileUtility.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
		ExcelUtility.openExcel(IconstantPath.EXCELFILEPATH1);
		String url = FileUtility.getDataFromPropertyFile("url");
		String username =FileUtility.getDataFromPropertyFile("username");
		String password = FileUtility.getDataFromPropertyFile("password");
		String timeout =FileUtility.getDataFromPropertyFile("timeout");
		String browser = FileUtility.getDataFromPropertyFile("browser");

		long longTimeOut = jutil.stringToLong(timeout);
		int randomNumber=jutil.getRandomNumber(1000);

		String orgname=ExcelUtility.getDataFromExcel("Sheet1", 1, 1)+randomNumber;



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


		//Login to vtiger
		//driver.findElement(By.name("user_name")).sendKeys(username);
		//driver.findElement(By.name("user_password")).sendKeys(password);
		//driver.findElement(By.id("submitButton")).click();
		loginpage.loginAction(username, password);

		//To click on organization
		//driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		homepage.clickorganizationsTab();

		//To click on create organization
		//driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		createorganization.clickOnOrganizationTab();
		
		
		//enter organization name
		//driver.findElement(By.name("accountname")).sendKeys(orgname);
		//driver.findElement(By.name("button")).click();
		organizationData.organizationData(orgname);
		
		Thread.sleep(5000);

		//To validate contact name
		//WebElement ActualorgName = driver.findElement(By.id("dtlview_Organization Name"));
		jutil.assertionThroughIfCondition(oip.getOrganization(), orgname , "organization");

		Thread.sleep(5000);

		//mousehover
		//WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		//WebDriverUtility.mouseHoverOnTheElement(ele, driver);
		//driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		homepage.signout(driver);
		WebDriverUtility.quit(driver);					

	}

}
