package com.rmgyantra.practiceTest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.jdbc.Driver;
import com.sdet34L1.genericUtility.DatabaseUtility;
import com.sdet34L1.genericUtility.ExcelUtility;
import com.sdet34L1.genericUtility.FileUtility;
import com.sdet34L1.genericUtility.IconstantPath;
import com.sdet34L1.genericUtility.JavaUtility;
import com.sdet34L1.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidateProjectNameInGUIWrtDatabase {

	public static void main(String[] args) throws SQLException, InterruptedException, IOException {
		JavaUtility jutil=new JavaUtility();
		FileUtility.openPropertyFile(IconstantPath.RMGYANTRA_PROPERTYFILE_PATH);
		ExcelUtility.openExcel(IconstantPath.RMGYANTRAEXELFILEPATH);
		int randomNumber = jutil.getRandomNumber(1000);
		String projectName=ExcelUtility.getDataFromExcel("projects", 1, 1)+"_"+randomNumber;
		System.out.println(projectName);
		DatabaseUtility.openDBConnection(IconstantPath.DATABASEURL+FileUtility.getDataFromPropertyFile("dbName"),FileUtility.getDataFromPropertyFile("dbUserName"),FileUtility.getDataFromPropertyFile("dbPassword"));
		DatabaseUtility.setDataInDataBase("insert into project values('TY_PROJ_"+randomNumber+"','jp','28/04/2022','"+projectName+"','on Going',12);");
		DatabaseUtility.closeDBConnection();
		
			WebDriverManager.chromedriver().setup();
			WebDriver driver=new ChromeDriver();
			WebDriverUtility.browserSetting(10, driver);
			WebDriverUtility.navigateApp("http://localhost:8084", driver);
			driver.manage().window().maximize();
			Thread.sleep(5000);
			
			// login to rmgTestyantra
			driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
			driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
			driver.findElement(By.xpath("//button[text()='Sign in']")).click();
			Thread.sleep(1000);
			//To create project
			driver.findElement(By.xpath("//a[text()='Projects']")).click();
			
			List<WebElement> listOfProjects = driver.findElements(By.xpath("//table//tbody/tr/td[2]"));
			
			for(WebElement project:listOfProjects) {
				if(project.getText().equalsIgnoreCase(projectName)) {
					System.out.println("project name is visible in GUI");
					System.out.println("TC pass");
					break;
				}
			}
			WebDriverUtility.quit(driver);
			}
	

		
		}
	
			 

	


