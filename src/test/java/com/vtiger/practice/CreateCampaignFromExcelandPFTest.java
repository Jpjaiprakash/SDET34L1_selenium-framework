package com.vtiger.practice;

import java.io.FileInputStream;
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

import com.sdet34L1.genericUtility.FileUtility;
import com.sdet34L1.genericUtility.IconstantPath;
import com.sdet34L1.genericUtility.JavaUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignFromExcelandPFTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		JavaUtility jutil=new JavaUtility();
		FileUtility.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
		
		String url = FileUtility.getDataFromPropertyFile("url");
		String username =FileUtility.getDataFromPropertyFile("username");
		String password = FileUtility.getDataFromPropertyFile("password");
		String timeout =FileUtility.getDataFromPropertyFile("timeout");
		String browser = FileUtility.getDataFromPropertyFile("browser");
		
		long longTimeOut = jutil.stringToLong(timeout);
		
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
		
		//step1: Convert physical file to java readable file
		FileInputStream fis1=new FileInputStream("./src/test/resources/WorksheetTest.xlsx");
		
		//step2: get excel
		Workbook w=WorkbookFactory.create(fis1);
		
		//step3: get sheet
		Sheet s = w.getSheet("CreateCampaign");
		
		//step4: get row
		Row r = s.getRow(3);
		
		//step5: get cell
		Cell c = r.getCell(1);
		
		//step6: fetch data from cell
		String data = c.getStringCellValue();
		
		System.out.println(data);
		
		//step7:
		w.close();
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
		
		//Login to vtiger
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		//mousehover
		Actions a=new Actions(driver);
		
		WebElement m = driver.findElement(By.xpath("//a[@href='javascript:;']"));
		a.moveToElement(m).perform();
		
		//TO click on campaign
		driver.findElement(By.name("Campaigns")).click();
		
		//TO create campaign
		driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
		
		//To add campaign name
		driver.findElement(By.name("campaignname")).sendKeys(data);
		driver.findElement(By.name("assigntype")).click();
		
		//TO click on save button
		driver.findElement(By.name("button")).click();
		
		//To validate contact name
	    WebElement ActualcampaignName = driver.findElement(By.id("dtlview_Campaign Name"));
				
		if(ActualcampaignName.getText().equalsIgnoreCase(data))
				{
					System.out.println("contact created successfully");
					System.out.println("TC Pass");
				}

		Thread.sleep(5000);
		
		//mousehover
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));

		a.moveToElement(ele).perform();
		Thread.sleep(5000);

		//logout
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();	

	}

}
