package com.vtiger.practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sdet34L1.genericUtility.ExcelUtility;
import com.sdet34L1.genericUtility.FileUtility;
import com.sdet34L1.genericUtility.IconstantPath;
import com.sdet34L1.genericUtility.JavaUtility;
import com.sdet34L1.genericUtility.WebDriverUtility;
import com.vtiger.objectRepository.ContactsPage;
import com.vtiger.objectRepository.CreateNewContactPage;
import com.vtiger.objectRepository.CreateNewOrganizationPage;
import com.vtiger.objectRepository.HomePage;
import com.vtiger.objectRepository.LOginPage;
import com.vtiger.objectRepository.OrganizationInformationPage;
import com.vtiger.objectRepository.OrganizationPage;
import com.vtiger.objectRepository.SearchOrgPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrganization {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
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
		OrganizationPage createorganization=new OrganizationPage(driver);
		CreateNewOrganizationPage organizationData=new CreateNewOrganizationPage(driver);
		OrganizationInformationPage oip=new OrganizationInformationPage(driver);
		ContactsPage newcontactbtn=new ContactsPage(driver);
		CreateNewContactPage data=new CreateNewContactPage(driver);
		SearchOrgPage orglookup=new SearchOrgPage(driver);
		
		
		WebDriverUtility.navigateApp(url, driver);
		WebDriverUtility.browserSetting(longTimeOut, driver);

		loginpage.loginAction(username, password);
		homepage.clickorganizationsTab();
		createorganization.clickOnOrganizationTab();
		organizationData.organizationData(orgname);
		
		homepage.clickcontactsTab();
		newcontactbtn.clickcreatecontact();
		data.CreateNewContact1(firstname, lastname);
		orglookup.selectorganization(orgname, driver);
		data.ContactSaveBtn();

	}

}
