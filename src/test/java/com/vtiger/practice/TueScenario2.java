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

import io.github.bonigarcia.wdm.WebDriverManager;

public class TueScenario2 {

	public static void main(String[] args) throws IOException, InterruptedException {
		FileInputStream fis=new FileInputStream("./src/test/resources/CommonData.properties");
		Properties property = new Properties();
		property.load(fis);
		String url = property.getProperty("url");
		String username = property.getProperty("username");
		String password = property.getProperty("password");
		String timeout = property.getProperty("timeout");
		String browser = property.getProperty("browser");

		long longTimeOut = Long.parseLong(timeout);
		
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
		
		FileInputStream fisExcel=new FileInputStream("./src/test/resources/Book1.xlsx");
		Workbook w=WorkbookFactory.create(fisExcel);
		
		Sheet sh = w.getSheet("Sheet1");
		Row r = sh.getRow(1);
		Cell c = r.getCell(1);
		
		Random ran = new Random();
		String data = c.getStringCellValue()+ran;
		System.out.println(data);
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);

		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(data);
		
		WebElement ele2 = driver.findElement(By.name("industry"));
		Select s=new Select(ele2);
		s.selectByVisibleText("Education");
		
		WebElement ele3 = driver.findElement(By.name("accounttype"));
		Select s1=new Select(ele3);
		s1.selectByValue("Press");
		
		driver.findElement(By.name("button")).click();
		Thread.sleep(5000);
		
		String actname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(actname.contains(data)) {
			System.out.println("pass");
		}
		else {
			System.out.println("false");
		}
		Thread.sleep(5000);
		//mousehover
		Actions a=new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		
		a.moveToElement(ele).perform();
		Thread.sleep(5000);
		//logout
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
		
		
		
		
		
		

	}

}
