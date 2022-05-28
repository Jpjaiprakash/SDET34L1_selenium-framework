package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sdet34L1.genericUtility.ExcelUtility;
import com.sdet34L1.genericUtility.FileUtility;
import com.sdet34L1.genericUtility.IconstantPath;
import com.sdet34L1.genericUtility.JavaUtility;
import com.sdet34L1.genericUtility.WebDriverUtility;
import com.vtiger.objectRepository.CreateNewDocumentPAge;
import com.vtiger.objectRepository.DocumentPage;
import com.vtiger.objectRepository.EnterDataToCreateDocument;
import com.vtiger.objectRepository.HomePage;
import com.vtiger.objectRepository.LOginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GenericUtilityScenario1 {

	public static void main(String[] args) throws IOException, InterruptedException {
		JavaUtility jutil=new JavaUtility();
		WebDriver driver=null;
		
		FileUtility.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
		ExcelUtility.openExcel(IconstantPath.EXCELFILEPATH5);
		
		
		String url = FileUtility.getDataFromPropertyFile("url");
		String username =FileUtility.getDataFromPropertyFile("username");
		String password = FileUtility.getDataFromPropertyFile("password");
		String timeout =FileUtility.getDataFromPropertyFile("timeout");
		String browser = FileUtility.getDataFromPropertyFile("browser");

		long longTimeOut = jutil.stringToLong(timeout);
		
		int randomNumber=jutil.getRandomNumber(1000);
		String documentTitle =ExcelUtility.getDataFromExcel("document", 1, 1)+randomNumber;
		String documentpath =ExcelUtility.getDataFromExcel("document", 1, 2)+randomNumber;
		String documentdescription =ExcelUtility.getDataFromExcel("document", 1, 3)+randomNumber;
		
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
		DocumentPage documentTab=new DocumentPage(driver);
		CreateNewDocumentPAge newDocument=new CreateNewDocumentPAge(driver);
		EnterDataToCreateDocument titlename=new EnterDataToCreateDocument(driver);
		
		
		WebDriverUtility.navigateApp(url, driver);
		WebDriverUtility.browserSetting(longTimeOut, driver);
		//WebDriverUtility.explicitlyWait(driver, longTimeOut);
		WebDriverWait wait=new WebDriverWait(driver, longTimeOut);
		
		//driver.findElement(By.name("user_name")).sendKeys(username);
		//driver.findElement(By.name("user_password")).sendKeys(password);
		//driver.findElement(By.id("submitButton")).click();
		loginpage.loginAction(username, password);
		
		//driver.findElement(By.xpath("//a[text()='Documents']")).click();
		documentTab.clickOnDocumentsTab();
		
		
		//driver.findElement(By.xpath("//img[@title='Create Document...']")).click();
		newDocument.clickOnCreateDocument();
		
		//driver.findElement(By.name("notes_title")).sendKeys(documentTitle);
		titlename.EnterDocumentTitle(documentTitle);
		
		
		//WebDriverUtility.switchToFrameByIndex(driver, 0);
		//driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Rich text editor, notecontent, press ALT 0 for help.']")));
		//driver.findElement(By.xpath("//body[@class='cke_show_borders']")).sendKeys(documentdescription,Keys.CONTROL+"a");
		//WebDriverUtility.switchBackToHomeWebPage(driver);
		//driver.switchTo().defaultContent();
		titlename.SwitchToTF(driver, 0, documentdescription);
		
		//driver.findElement(By.id("cke_5")).click();
		titlename.clickOnBoldBtn();
		//driver.findElement(By.id("cke_6")).click();
		titlename.clickOnItalicBtn();
		
		//driver.findElement(By.name("filename")).sendKeys(documentpath);
		titlename.clickOnFilenameBtn(documentpath);
		
		//driver.findElement(By.name("button")).click();
		titlename.clickOnDocumentSaveBtn();
		
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='dvHeaderText']"))));
		
		
		
		//WebElement ele1 = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		//WebDriverUtility.mouseHoverOnTheElement(ele1, driver);
		//driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		homepage.signout(driver);
		WebDriverUtility.quit(driver);
	}

}
