package com.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidateOrgnameInDatebaseWRTGUI {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8888");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Login to vtiger
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("root");
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
		String actorg = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
       Connection connection = null;
		
		try {
		//step1: create object for driver class
		Driver driver1=new Driver();
		
		//step2: Register the Driver with JDBC
		DriverManager.registerDriver(driver1);
		
		//step3: establish the database connection
	    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/organization", "root", "");
		
		
		//step4: create statement
		Statement statement = connection.createStatement();
		
		//step5: execute query
		ResultSet result = statement.executeQuery("select name from orgn;");
		
		//step6: validate(based on testcase)
		while(result.next())
		{
			if(result.getString("name").equals(organizationname))
			{
				System.out.println("name is present in the database");
				System.out.println("TC pass");
			}
		}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally {
			try {
				connection.close();
				driver.quit();
			} catch(SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
				
		

	}

}
