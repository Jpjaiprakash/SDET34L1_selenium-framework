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

import com.sdet34L1.genericUtility.FileUtility;
import com.sdet34L1.genericUtility.IconstantPath;
import com.sdet34L1.genericUtility.JavaUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TueScenario1 {

	public static void main(String[] args) throws IOException, InterruptedException {
		JavaUtility jutil=new JavaUtility();
		FileUtility.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
		String url = FileUtility.getDataFromPropertyFile("url");
		String username =FileUtility.getDataFromPropertyFile("username");
		String password = FileUtility.getDataFromPropertyFile("password");
		String timeout =FileUtility.getDataFromPropertyFile("timeout");
		String browser = FileUtility.getDataFromPropertyFile("browser");

		long longTimeOut = jutil.stringToLong(timeout);
		
		int randomNumber=jutil.getRandomNumber(1000);

		FileInputStream fisExcel=new FileInputStream("./src/test/resources/Scenario1.xlsx");
		Workbook w=WorkbookFactory.create(fisExcel);
		
	
		String documentTitle = w.getSheet("document").getRow(1).getCell(1).getStringCellValue()+randomNumber;
		String documentpath = w.getSheet("document").getRow(1).getCell(2).getStringCellValue();
		String documentdescription = w.getSheet("document").getRow(1).getCell(3).getStringCellValue();
		
		WebDriver driver=null;
		
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
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
		WebDriverWait wait=new WebDriverWait(driver, longTimeOut);
		
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.xpath("//a[text()='Documents']")).click();
		driver.findElement(By.xpath("//img[@title='Create Document...']")).click();
		driver.findElement(By.name("notes_title")).sendKeys(documentTitle);
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Rich text editor, notecontent, press ALT 0 for help.']")));
		
		driver.findElement(By.xpath("//body[@class='cke_show_borders']")).sendKeys(documentdescription,Keys.CONTROL+"a");
		driver.switchTo().defaultContent();
		
		driver.findElement(By.id("cke_5")).click();
		driver.findElement(By.id("cke_6")).click();
		driver.findElement(By.id("filename_I__")).sendKeys(documentpath);
		driver.findElement(By.name("button")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='dvHeaderText']"))));
		
		
		Actions a=new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));

		a.moveToElement(ele).perform();
		Thread.sleep(5000);

		//logout
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
	}

}
