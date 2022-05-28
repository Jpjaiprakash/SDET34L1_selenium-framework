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
import com.sdet34L1.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactReadDataFromPropertyfileTest {

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

		WebDriverUtility.navigateApp(url, driver);
		WebDriverUtility.browserSetting(longTimeOut, driver);
		

		//Login to vtiger
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		//To click on contact
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();

		//To click on create contact
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		String firstName = "sdet18"+randomNumber;
		String lastName = "L2"+randomNumber;

		//To enter firstname
		driver.findElement(By.name("firstname")).sendKeys(firstName);

		//To enter last name
		driver.findElement(By.name("lastname")).sendKeys(lastName);



		//To click on save button
		driver.findElement(By.name("button")).click();

		//To validate contact name
		WebElement ActualFirstName = driver.findElement(By.id("dtlview_First Name"));
		WebElement ActualLastName = driver.findElement(By.id("dtlview_Last Name"));
		
		jutil.assertionThroughIfCondition(ActualFirstName.getText(), firstName, "contact");
		jutil.assertionThroughIfCondition(ActualLastName.getText(), lastName, "");

		

		Thread.sleep(5000);

		//mousehover
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));

		
		WebDriverUtility.mouseHoverOnTheElement(ele, driver);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		WebDriverUtility.quit(driver);		

	}

}
