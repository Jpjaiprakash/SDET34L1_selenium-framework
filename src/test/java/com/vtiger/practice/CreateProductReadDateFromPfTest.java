package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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

public class CreateProductReadDateFromPfTest {

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
		//	if(browsername.equalsIgnoreCase("chrome")) {
		//	WebDriverManager.chromedriver().setup();
		//driver=new ChromeDriver();
		//}
		//else if(browsername.equalsIgnoreCase("firefox")) {
		//WebDriverManager.chromedriver().setup();
		//driver=new FirefoxDriver ();
		//}

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


		//Login to vtiger
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		//To click on products
		driver.findElement(By.xpath("//a[text()='Products']")).click();
		
		//To click on create product
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		
		String ProductName = "LG";
		//To enter product name
		driver.findElement(By.name("productname")).sendKeys(ProductName);
		
		//To click on save button
		driver.findElement(By.name("button")).click();
		
		//To validate product name
		WebElement ActualProductName = driver.findElement(By.id("dtlview_Product Name"));
		
		if(ActualProductName.getText().equalsIgnoreCase(ProductName))
		{
			System.out.println("product created successfully");
			System.out.println("TC Pass");
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
