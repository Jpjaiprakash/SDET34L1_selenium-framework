package com.vtiger.practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sdet34L1.genericUtility.ExcelUtility;
import com.sdet34L1.genericUtility.FileUtility;
import com.sdet34L1.genericUtility.IconstantPath;
import com.sdet34L1.genericUtility.JavaUtility;
import com.sdet34L1.genericUtility.WebDriverUtility;
import com.vtiger.objectRepository.ConvertLeadPage;
import com.vtiger.objectRepository.CreateNewLeadPage;
import com.vtiger.objectRepository.HomePage;
import com.vtiger.objectRepository.LOginPage;
import com.vtiger.objectRepository.LeadInformationPage;
import com.vtiger.objectRepository.LeadsPage;
import com.vtiger.objectRepository.ValidationTestcasesPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC32 {

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
		
		String lastname=ExcelUtility.getDataFromExcel("TC31", 1, 1);
		String companyname=ExcelUtility.getDataFromExcel("TC31", 2, 1);
		String closingdate=ExcelUtility.getDataFromExcel("TC32", 0, 1);
		String amount=ExcelUtility.getDataFromExcel("TC32", 1, 1);
		
		
		
		long longTimeOut = jutil.stringToLong(timeout);
		
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
		LeadsPage lead=new LeadsPage(driver);
		LeadInformationPage information=new LeadInformationPage(driver);
		ConvertLeadPage convert=new ConvertLeadPage(driver);
		CreateNewLeadPage newlead=new CreateNewLeadPage(driver);
		ValidationTestcasesPage validate=new ValidationTestcasesPage(driver);
		
		WebDriverUtility.navigateApp(url, driver);
		WebDriverUtility.browserSetting(longTimeOut, driver);
		WebDriverWait wait=new WebDriverWait(driver, longTimeOut);
		
		//driver.findElement(By.name("user_name")).sendKeys(username);
		//driver.findElement(By.name("user_password")).sendKeys(password);
		//driver.findElement(By.id("submitButton")).click();
		loginpage.loginAction(username, password);
		
		//driver.findElement(By.xpath("//a[text()='Leads']")).click();
		homepage.clickLeadsTab();
		
		//driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		lead.clickOnLead();
		
		//driver.findElement(By.name("lastname")).sendKeys("raj");
		//driver.findElement(By.name("company")).sendKeys("yyy");
		//driver.findElement(By.xpath("//input[@accesskey='S']")).click();
		newlead.enterlastnameTf(lastname);
		newlead.entercompanynameTf(companyname);
		newlead.leadsaveBtn();
		
		Thread.sleep(5000);
		
		//driver.findElement(By.xpath("//a[text()='kant']")).click();
		
		
		//driver.findElement(By.xpath("//a[text()='Convert Lead']")).click();
		information.clickOnConvertLeadBtn();
		
		Thread.sleep(10000);
		
		//driver.findElement(By.xpath("//input[@id='select_potential']")).click();
		convert.clickOpportunityCheckBox();
		
		 
		//driver.findElement(By.name("closingdate")).sendKeys(closedate);
		convert.closingDateofLead(closingdate);
		
		//driver.findElement(By.name("amount")).sendKeys("5000");
		convert.amountTf(amount);
		
		//driver.findElement(By.name("Save")).click();
		//driver.findElement(By.xpath("(//input[@value='Save'])[1]")).click();
		 convert.clickOnConvertLeadSaveBtn();
		 
		//WebElement ActualcampaignName = driver.findElement(By.id("dtlview_Last Name"));
		
		jutil.assertionThroughIfCondition(validate.getleadlastname(), lastname, "organization");
		 
		 
		//WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		//WebDriverUtility.mouseHoverOnTheElement(ele, driver);
		//driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		 homepage.signout(driver);
		
		
		WebDriverUtility.quit(driver);

	}

}
