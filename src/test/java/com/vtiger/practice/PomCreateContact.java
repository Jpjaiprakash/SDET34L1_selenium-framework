package com.vtiger.practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
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
import com.vtiger.objectRepository.ContactInformationPage;
import com.vtiger.objectRepository.ContactsPage;
import com.vtiger.objectRepository.CreateNewContactPage;
import com.vtiger.objectRepository.HomePage;
import com.vtiger.objectRepository.LOginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PomCreateContact {

	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
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


		String firstname=ExcelUtility.getDataFromExcel("Sheet1", 0, 1)+randomNumber;
		String lastname=ExcelUtility.getDataFromExcel("Sheet1", 1, 1)+randomNumber;


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
		ContactsPage newcontactbtn=new ContactsPage(driver);
		CreateNewContactPage data=new CreateNewContactPage(driver);
		ContactInformationPage valid=new ContactInformationPage(driver);
		
		WebDriverUtility.navigateApp(url, driver);
		WebDriverUtility.browserSetting(longTimeOut, driver);

		//Login to vtiger
		//driver.findElement(By.name("user_name")).sendKeys(username);
		//driver.findElement(By.name("user_password")).sendKeys(password);
		//driver.findElement(By.id("submitButton")).click();
		loginpage.loginAction(username, password);

		//To click on contact
		//driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		homepage.clickcontactsTab();

		//To click on create contact
		//driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		newcontactbtn.clickcreatecontact();

		//To enter firstname
		//driver.findElement(By.name("firstname")).sendKeys(firstname);
		//To enter last name
		//driver.findElement(By.name("lastname")).sendKeys(lastname);
		//To click on save button
		//driver.findElement(By.name("button")).click();
		data.CreateNewContactData(firstname, lastname);

		//To validate contact name
		//WebElement ActualFirstName = driver.findElement(By.id("dtlview_First Name"));
		//WebElement ActualLastName = driver.findElement(By.id("dtlview_Last Name"));

		jutil.assertionThroughIfCondition(valid.getFirstName(), firstname, "contact");
		jutil.assertionThroughIfCondition(valid.getLastName(), lastname, "");
		
		
		Thread.sleep(5000);

		homepage.signout(driver);
		WebDriverUtility.quit(driver);

		

	}

	}


