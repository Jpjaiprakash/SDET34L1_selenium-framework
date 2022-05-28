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
import com.vtiger.objectRepository.CampaignPage;
import com.vtiger.objectRepository.CreateNewProductPage;
import com.vtiger.objectRepository.CreateProductPage;
import com.vtiger.objectRepository.HomePage;
import com.vtiger.objectRepository.LOginPage;
import com.vtiger.objectRepository.ProductInformationPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProductFromExcelandPfTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		JavaUtility jutil=new JavaUtility();
		WebDriver driver=null;
		FileUtility.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
		ExcelUtility.openExcel(IconstantPath.EXCELFILEPATH4);
		String url = FileUtility.getDataFromPropertyFile("url");
		String username =FileUtility.getDataFromPropertyFile("username");
		String password = FileUtility.getDataFromPropertyFile("password");
		String timeout =FileUtility.getDataFromPropertyFile("timeout");
		String browser = FileUtility.getDataFromPropertyFile("browser");

		long longTimeOut = jutil.stringToLong(timeout);

		int randomNumber=jutil.getRandomNumber(1000);
		
		String fis1=ExcelUtility.getDataFromExcel("Sheet1", 1, 1)+randomNumber;
		


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
		CreateProductPage newproduct=new CreateProductPage(driver);
		CreateNewProductPage nameofproduct=new CreateNewProductPage(driver);
		ProductInformationPage pip=new ProductInformationPage(driver);

		
		WebDriverUtility.navigateApp(url, driver);
		WebDriverUtility.browserSetting(longTimeOut, driver);
		
		//Login to vtiger
		//driver.findElement(By.name("user_name")).sendKeys(username);
		//driver.findElement(By.name("user_password")).sendKeys(password);
		//driver.findElement(By.id("submitButton")).click();
		loginpage.loginAction(username, password);
		
		//To click on products
		//driver.findElement(By.xpath("//a[text()='Products']")).click();
		homepage.clickproducts();
		
		
		//To click on create product
		//driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		newproduct.clickOnCreateProduct();

		//To enter product name and  //To click on save button
		//driver.findElement(By.name("productname")).sendKeys(fis1);
		//driver.findElement(By.name("button")).click();
		nameofproduct.enterProductName(fis1);
		
		
		//To validate product name
		//WebElement ActualProductName = driver.findElement(By.id("dtlview_Product Name"));
		
		jutil.assertionThroughIfCondition(pip.getProductName(), fis1, "product");
		
		

		Thread.sleep(5000);

		//mousehover
		
		//WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		//WebDriverUtility.mouseHoverOnTheElement(ele, driver);
		//driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		homepage.signout(driver);
		WebDriverUtility.quit(driver);
	

				
	}

}
