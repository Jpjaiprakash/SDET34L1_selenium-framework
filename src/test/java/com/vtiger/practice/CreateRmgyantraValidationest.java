package com.vtiger.practice;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateRmgyantraValidationest {

	public static void main(String[] args) throws SQLException, InterruptedException {
		WebDriver driver=null;
		Connection connection = null;
		String projectname="project_103";
		try {
	//step1: create object for driver class
	Driver d=new Driver();
	
	//step2: Register the Driver with JDBC
	DriverManager.registerDriver(d);
	
	//step3: establish the database connection
	 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "");
			

	//step4: create statement
	Statement statement = connection.createStatement();
	
	//step5: execute query
     statement.executeUpdate("insert into project values('TY_PROJ_002','jp','28/04/2022','"+projectname+"','on Going',12);");
    
		}
		finally {
			connection.close();
		}
		try {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("http://localhost:8084");
			driver.manage().window().maximize();
			Thread.sleep(5000);
			
			// login to rmgTestyantra
			driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
			driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
			driver.findElement(By.xpath("//button[text()='Sign in']")).click();
			
			//To create project
			driver.findElement(By.xpath("//a[text()='Projects']")).click();
			
			List<WebElement> listOfProjects = driver.findElements(By.xpath("//table//tbody/tr/td[2]"));
			
			for(WebElement project:listOfProjects) {
				if(project.getText().equalsIgnoreCase(projectname)) {
					System.out.println("project name is visible in GUI");
					System.out.println("TC pass");
					break;
				}
			}
			}
		finally {
			driver.quit();
		}
		}
	
			 
	}


