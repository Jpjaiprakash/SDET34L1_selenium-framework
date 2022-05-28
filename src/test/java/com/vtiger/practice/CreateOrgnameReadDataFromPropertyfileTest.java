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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrgnameReadDataFromPropertyfileTest {

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
		
		//To click on organization
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		
		//To click on create organization
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//enter organization name
		String organizationname = "appu";
		driver.findElement(By.name("accountname")).sendKeys(organizationname);
		
		
		//To click on save button
	    driver.findElement(By.name("button")).click();
		Thread.sleep(5000);
		

		//To validate contact name
		WebElement ActualorgName = driver.findElement(By.id("dtlview_Organization Name"));
		

		if(ActualorgName.getText().equalsIgnoreCase(organizationname))
		{
			System.out.println("organization is created successfully");
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
