package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.sdet34L1.genericUtility.IconstantPath;
import com.sdet34L1.genericUtility.JavaUtility;
import com.sdet34L1.genericUtility.ExcelUtility;
import com.sdet34L1.genericUtility.FileUtility;
import com.sdet34L1.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PassandFetchDataToExcel {
public static void main(String[] args) throws IOException, InterruptedException {
	
	  //to do auto-generated method stud
			JavaUtility javautil=new JavaUtility();
			WebDriver driver=null;
			
			FileUtility.openPropertyFile(IconstantPath.PROPERTYFILEPATH);
			ExcelUtility.openExcel(IconstantPath.EXCELFILEPATH5);
			String url = FileUtility.getDataFromPropertyFile("url");
			String username =FileUtility.getDataFromPropertyFile("username");
			String password = FileUtility.getDataFromPropertyFile("password");
			String timeout =FileUtility.getDataFromPropertyFile("timeout");
			String browser = FileUtility.getDataFromPropertyFile("browser");

			
			long longtime = javautil.stringToLong(timeout);
			
			int randomNumber=javautil.getRandomNumber(1000);

			String Contact_name = ExcelUtility.getDataFromExcel("testcases", 1, 1);

			//FileInputStream fisExcel=new FileInputStream("./src/test/resources/ex.xlsx");
			//Workbook wb=WorkbookFactory.create(fisExcel);
			//String Contact_name=wb.getSheet("CreateContactTest").getRow(1).getCell(1).getStringCellValue()+randomNumber;
	     	// wb.close();
	
	switch (browser) {
	case "chrome":
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();	
		break;
		
	case "firefox":
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		break;

	default:
		driver=new FirefoxDriver();
		break;
	}
	
	WebDriverUtility.browserSetting(longtime, driver);
	WebDriverUtility.navigateApp(url, driver);
	WebDriverUtility.maximizeBrowser(driver);
	WebDriverUtility.explicitlyWait(driver, longtime);
	
	
	//driver.get(url);
	//driver.manage().timeouts().implicitlyWait(longtime, TimeUnit.SECONDS);
	
	
	//testcase1:login to the app...........................
	
	driver.findElement(By.xpath("//input[@type='text']")).sendKeys(username);
	driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
	driver.findElement(By.xpath("//input[@type='submit']")).click();
	
	if(driver.getTitle().contains("Home"))
	{
		ExcelUtility.setDataIntoExcel("testcases", 14, 8, "home page is displayed");
		ExcelUtility.setDataIntoExcel("testcases", 14, 9, "test case pass");
		
		//wb.getSheet("testcases").getRow(14).createCell(8).setCellValue("home page is displayed");
		//wb.getSheet("testcases").getRow(14).createCell(9).setCellValue("test case pass");
		//FileOutputStream fos = new FileOutputStream("./src/test/resources/ex.xlsx");
		//wb.write(fos);
	}
	
	//testcase2:click on contact................
	
	driver.findElement(By.xpath("//a[.='Contacts']")).click();
	
	if(driver.getTitle().contains("Contacts"))
	{
		ExcelUtility.setDataIntoExcel("testcases", 15, 8, "contact page is displayed");
		ExcelUtility.setDataIntoExcel("testcases", 15, 9, "test case pass");
		
	//wb.getSheet("testcases").getRow(15).createCell(8).setCellValue("contact page is displayed");
	//wb.getSheet("testcases").getRow(15).createCell(9).setCellValue("test case pass");
	}
	
	
	//testcase3:click on create contact icon..................
	
	driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
	
	ExcelUtility.setDataIntoExcel("testcases", 16, 8, "contact details is displayed");
	ExcelUtility.setDataIntoExcel("testcases", 16, 9, "test case pass");
	
	//wb.getSheet("testcases").getRow(16).createCell(8).setCellValue("contact details is displayed");
	//wb.getSheet("testcases").getRow(16).createCell(9).setCellValue("test case pass");
	//fos = new FileOutputStream("./src/test/resources/ex.xlsx");
	//wb.write(fos);
	
	//testcase4:enter the mandatosy field and click on save button
	driver.findElement(By.name("lastname")).sendKeys(Contact_name);
	driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
	
	ExcelUtility.setDataIntoExcel("testcases", 17, 8, "contact is saved");
	ExcelUtility.setDataIntoExcel("testcases", 17, 9, "test case pass");
		
	//wb.getSheet("testcases").getRow(17).createCell(8).setCellValue("contact is saved");
	//wb.getSheet("testcases").getRow(17).createCell(9).setCellValue("test case pass");
	 //fos = new FileOutputStream("./src/test/resources/ex.xlsx");
	//wb.write(fos);
	
	
	//testcase5:validate the data........................
	
	Thread.sleep(2000);
	String ele = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	if(ele.contains(Contact_name))
	{
	System.out.println("TC is VALIDATED");
	}
	else {
		System.out.println("TC is NOT VALIDATED");
	}
	
	ExcelUtility.setDataIntoExcel("testcases", 18, 8, "data is validated");
	ExcelUtility.setDataIntoExcel("testcases", 18, 9, "test case pass");
	
	//wb.getSheet("testcases").getRow(18).createCell(8).setCellValue("data is validated");
	//wb.getSheet("testcases").getRow(18).createCell(9).setCellValue("test case pass");
	 //fos = new FileOutputStream("./src/test/resources/ex.xlsx");
	//wb.write(fos);
	
	
	//testcase6:click on logout.....................	
	
	WebElement ele1 = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	Actions act=new Actions(driver);
	act.moveToElement(ele1).perform();
	driver.findElement(By.xpath("//a[.='Sign Out']")).click();
	
	ExcelUtility.setDataIntoExcel("testcases", 19, 8, "login page is displayed");
	ExcelUtility.setDataIntoExcel("testcases", 19, 9, "test case pass");
	
	
	//wb.getSheet("testcases").getRow(19).createCell(8).setCellValue("login page is displayed");
	//wb.getSheet("testcases").getRow(19).createCell(9).setCellValue("test case pass");	
	//fos = new FileOutputStream("./src/test/resources/ex.xlsx");
	//wb.write(fos);
	//wb.close();
	ExcelUtility.openExcel(IconstantPath.EXCELFILEPATH5);
	ExcelUtility.closeExcel();
	ExcelUtility.quitBrowser(driver);
	
	
}
}
