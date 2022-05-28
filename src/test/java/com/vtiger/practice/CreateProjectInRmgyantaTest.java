package com.vtiger.practice;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProjectInRmgyantaTest {

	public static void main(String[] args) throws InterruptedException, SQLException {
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
		String projectname="project_102";
		driver.findElement(By.name("projectName")).sendKeys(projectname);
		driver.findElement(By.name("createdBy")).sendKeys("deepak");
		
		WebElement projectStatusDropdown = driver.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select"));
		Select s=new Select(projectStatusDropdown);
		s.selectByVisibleText("Created");
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		
		Connection connection = null;
		
		try {
		//step1: create object for driver class
		Driver driver1=new Driver();
		
		//step2: Register the Driver with JDBC
		DriverManager.registerDriver(driver1);
		
		//step3: establish the database connection
	    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "");
		
		
		//step4: create statement
		Statement statement = connection.createStatement();
		
		//step5: execute query
		ResultSet result = statement.executeQuery("select project_name from project;");
		
		//step6: validate(based on testcase)
		while(result.next())
		{
			if(result.getString("project_name").equals(projectname))
			{
				System.out.println("project name is present in the database");
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
