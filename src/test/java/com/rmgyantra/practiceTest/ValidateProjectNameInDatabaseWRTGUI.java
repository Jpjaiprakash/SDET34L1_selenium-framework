package com.rmgyantra.practiceTest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.jdbc.Driver;
import com.sdet34L1.genericUtility.DatabaseUtility;
import com.sdet34L1.genericUtility.FileUtility;
import com.sdet34L1.genericUtility.IconstantPath;
import com.sdet34L1.genericUtility.JavaUtility;
import com.sdet34L1.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidateProjectNameInDatabaseWRTGUI {

	public static void main(String[] args) throws InterruptedException, IOException, SQLException {
		JavaUtility jutil=new JavaUtility();
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://localhost:8084");
		Thread.sleep(5000);
		
		// login to rmgTestyantra
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		
		//To create project
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		String projectName="SDET34L2-"+jutil.getRandomNumber(1000);
		driver.findElement(By.name("projectName")).sendKeys(projectName);
		driver.findElement(By.name("createdBy")).sendKeys("deepak");
		
		WebElement projectStatusDropdown = driver.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select"));
		Select s=new Select(projectStatusDropdown);
		s.selectByVisibleText("Created");
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		
		FileUtility.openPropertyFile(IconstantPath.RMGYANTRA_PROPERTYFILE_PATH);
		DatabaseUtility.openDBConnection(IconstantPath.DATABASEURL+FileUtility.getDataFromPropertyFile("dbName"),FileUtility.getDataFromPropertyFile("dbUserName"),FileUtility.getDataFromPropertyFile("dbPassword"));
		boolean status = DatabaseUtility.validateDataInDatabase("select project_name from project;", "project_name", projectName);
		if(status==true) {
			System.out.println("TC pass");
		}
		else {
			System.out.println("TC fail");
	}
		
		DatabaseUtility.closeDBConnection();
		WebDriverUtility.quit(driver);
	
	}
}


