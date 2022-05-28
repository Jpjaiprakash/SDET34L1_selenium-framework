package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
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

public class FetchandPassDatawrtXLTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		JavaUtility jutil=new JavaUtility();
		FileUtility.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
		String url = FileUtility.getDataFromPropertyFile("url");
		String username =FileUtility.getDataFromPropertyFile("username");
		String password = FileUtility.getDataFromPropertyFile("password");
		String timeout =FileUtility.getDataFromPropertyFile("timeout");
		String browser = FileUtility.getDataFromPropertyFile("browser");
		
		FileInputStream fis1=new FileInputStream("./src/test/resources/multiple.xlsx");
		
		Workbook wb=WorkbookFactory.create(fis1);
		//Sheet s = wb.getSheet("Sheet1");
		//Row r = s.getRow(3);
		//Cell c = r.getCell(4);
		Random random=new Random();
		int randomNum=random.nextInt(1000);
		//String LastrName=cell.getStringCellValue()+randomNum;
		String LastrName=wb.getSheet("sheet1").getRow(3).getCell(4).getStringCellValue()+randomNum;
		System.out.println(LastrName);
		
		long timelong=Long.parseLong(timeout);
		WebDriver driver=null;
		
		WebElement ActualLastname1;
		switch(browser)
		{
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
		driver.manage().timeouts().implicitlyWait(timelong, TimeUnit.SECONDS);
		
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		if(driver.getTitle().contains("Home"))
		{
			wb.getSheet("Sheet1").getRow(10).createCell(5).setCellValue("home page is displayed");
			wb.getSheet("Sheet1").getRow(10).createCell(6).setCellValue("pass");
		}
		driver.findElement(By.xpath("//a[@href='index.php?module=Contact&action=index']")).click();
		
		if(driver.getTitle().contains("Contacts"))	
		{
			wb.getSheet("Sheet1").getRow(12).createCell(5).setCellValue("contact page is displayed");
			wb.getSheet("Sheet1").getRow(12).createCell(6).setCellValue("pass");
		}
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		driver.findElement(By.name("lastname")).sendKeys(LastrName);
		driver.findElement(By.name("button")).click();
		
		WebElement AtualLastName1 = driver.findElement(By.xpath("//td[@id='mouseArea_Last Name']"));
		if( AtualLastName1.getText().equalsIgnoreCase(LastrName)) {
			System.out.println("contact createdsuccessfully");
			System.out.println("tc pass");
		}
		Actions a=new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		
		a.moveToElement(ele).perform();
		Thread.sleep(5000);
		//logout
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();

	}

}
