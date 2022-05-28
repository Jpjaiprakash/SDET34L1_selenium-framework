package com.vtiger.practice;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;
import com.sdet34L1.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class javaScriptExecutor {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		TakesScreenshot ts=(TakesScreenshot)driver;

		//js.executeScript("window.location='http://localhost:8888'");
		/*driver.manage().window().maximize();
		js.executeScript("arguments[0].value=arguments[1]",driver.findElement(By.name("user_name")),"admin");
		js.executeScript("arguments[0].value=arguments[1]",driver.findElement(By.name("user_password")),"root");
		js.executeScript("arguments[0].click()", driver.findElement(By.id("submitButton")));
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//b[contains(.,' Upcoming Activities')]")));


		File src = ts.getScreenshotAs(OutputType.FILE);
		String fileName=new javaScriptExecutor().getClass().getName();
		Object dateTime = new SimpleDateFormat("yyyy_MM_dd_HH_mm_sss").format(new Date());

		File dst = new File("./screenshot/"+fileName+"_"+dateTime+".png");
		System.out.println(dst.getAbsolutePath());
		//FileUtils.copyFile(src, dst);
		Files.copy(src, dst);
		 */

		WebDriverUtility.intializeJs(driver);
		driver.manage().window().maximize();
		WebDriverUtility.navigateApplicationThroughJs("http://localhost:8888");
		WebDriverUtility.enterDataThroughJs(driver.findElement(By.name("user_name")),"admin");
		WebDriverUtility.enterDataThroughJs(driver.findElement(By.name("user_password")),"root");
		WebDriverUtility.clickThroughJs(driver.findElement(By.id("submitButton")));
		WebDriverUtility.scrollTillElement(driver.findElement(By.xpath("//b[text()='Subject']")));

		String fileName=new javaScriptExecutor().getClass().getName();
		WebDriverUtility.takeScreenShot(fileName, driver);
	}

}
