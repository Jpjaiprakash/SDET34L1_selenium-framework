package com.sdet34L1.genericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.vtiger.objectRepository.HomePage;
import com.vtiger.objectRepository.LOginPage;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * This class contains all configure annotations
 */
public class BaseClass {

	public WebDriver driver;
	public String password;
	public String  username;
	public int randomNumber;
	public long longTimeOut;
	public JavaUtility jutil;
	public LOginPage loginpage;
	public HomePage homepage;
	public static WebDriver staticdriver;
	public String url;
	public String browser; 
	
	

	/**
	 * This annotation contains opening of excel file and opening of property file	
	 * @throws IOException
	 */
	@BeforeSuite(groups="baseclass")
	public void beforesuite1test() throws IOException {
		//open database if it is required
		FileUtility.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
		ExcelUtility.openExcel(IconstantPath.EXCELFILEPATH);
		
	}

	/**
	 * This annotation contains normal execution configuration
	 */
	//@Parameters("browser")
	@BeforeClass(groups="baseclass")
	public void beforeClass1Test(/*String browser*/) {
		jutil=new JavaUtility();
		
		 url = FileUtility.getDataFromPropertyFile("url");
		//username =FileUtility.getDataFromPropertyFile("username");
		//password = FileUtility.getDataFromPropertyFile("password");
		String timeout =FileUtility.getDataFromPropertyFile("timeout");
		String browser = FileUtility.getDataFromPropertyFile("browser");
		browser=System.getProperty("BROWSER");
		username=System.getProperty("USERNAME");
		password=System.getProperty("PASSWORD");
		

		longTimeOut = jutil.stringToLong(timeout);
		randomNumber=jutil.getRandomNumber(1000);

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
		staticdriver=driver;
		WebDriverUtility.browserSetting(longTimeOut, driver);
		WebDriverUtility.explicitlyWait(driver, longTimeOut);
		
		loginpage=new LOginPage(driver);
		homepage=new HomePage(driver);

		WebDriverUtility.navigateApp(url, driver);

	}

	/**
	 * This annotation consists of Login actions
	 */
	@BeforeMethod(groups="baseclass")
	public void beforeMethod1Test(){
	loginpage.loginAction(username, password);
	}

	/**
	 * This annotation contains Logout actions
	 */
	@AfterMethod(groups="baseclass")
	public void afterMethod1Test() {
		
		homepage.signout(driver);
	}
	
	/**
	 * This annotation is used to close the browser
	 */
	@AfterClass(groups="baseclass")
	public void afterClass1Test() {
	
		WebDriverUtility.quit(driver);
	}

	/**
	 * This annotation is to close excel
	 */
	@AfterSuite(groups="baseclass")
	public void afterSuite1Test() {
	
		ExcelUtility.closeExcel();
	}

}
