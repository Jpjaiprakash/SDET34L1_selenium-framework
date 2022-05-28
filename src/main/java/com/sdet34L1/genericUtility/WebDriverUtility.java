package com.sdet34L1.genericUtility;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
	 * This is class is used to maintain all web Driver common actions
	 */
	
	public class WebDriverUtility {	
		
		static WebDriverWait wait;
		static Select s;
		static  Actions act;
		static JavascriptExecutor js;
		static JavaUtility jutil=new JavaUtility();
		
		
		
		
		
		
		public WebDriverUtility(WebDriver staticdriver) {
			// TODO Auto-generated constructor stub
		}


		/**
		 * This method is used to navigate the application
		 */
		public static void navigateApp(String url,WebDriver driver) 
		{
			driver.get(url);
		}
		
		
		/**
		 * This method is used to maximize the browser and implicit wait
		 */
		public static  void browserSetting(long longTimeOut,WebDriver driver)
		{
			maximizeBrowser(driver);
			
		}
		
		
		
		/**
		 * This method is used to maximize the browser
		 */
		public static  void maximizeBrowser(WebDriver driver)
		{
			driver.manage().window().maximize();
		}
		
		
		/**
		 * This method is used to implicit wait till page load
		 */
		public static void waitTillPageLoad(long longTimeOut,WebDriver driver)
		{
			driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
		}
		
		/**
		 * initialize driver
		 * @param driver
		 */
		public  static void initilaizeActions(WebDriver driver) {
			 act=new Actions(driver);
		}
		
		/**
		 * This method is used to mousehover element
		 */
		public static  void mouseHoverOnTheElement(WebElement moreLink,WebDriver driver)
		{
			
		    act=new Actions(driver);
			act.moveToElement(moreLink).perform();
		}
		
		public static   void doubleClick(WebElement moreLink, WebDriver driver) {
			
			act.doubleClick(moreLink).perform();	
		}
		
		public static  void doubleClick(WebDriver driver) {
			
			act.doubleClick().perform();
			
		}
		
		
		
		/**
		 * This method is used to quit the browser
		 */
		public static  void quit(WebDriver driver)
		{
			driver.quit();
		}
		
		/**
		 * This method is used to wait the control till the particular element is clickable
		 */
		public static void waitUntillElementClickable(WebElement element) {
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		
		/**
		 * This method is used to wait the control till the particular element is visible
		 */
		public static void waitUntillElementVisible(WebElement element) {
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		
		/**
		 * This method is used to initialize wait instance
		 */
		public static void explicitlyWait(WebDriver driver, long timeOut) {
			wait=new WebDriverWait(driver, timeOut);
		}
		
		/**
		 * This method is used to switch the window based on title
		 */
		public static void switchToWindowBasedTitle(WebDriver driver, String partialText) {
			Set<String> sessionIDs = driver.getWindowHandles();
			for(String id:sessionIDs) {
				driver.switchTo().window(id);
				if(driver.getTitle().contains(partialText))
				{
					break;
				}
			}
		}
		public static  void initializeDropDown(WebElement element) {
			s=new Select(element);
		}
		
		/**
		 * This method is used to select value from dropdown
		 * @param element
		 * @param value
		 */
		public  void selectByValue(String value, WebElement element) {
			
			s.selectByValue(value);	
		}
		
		/**
		 * This method is used to select index from dropdown
		 * @param element
		 * @param index
		 */
		public static  void selectByIndex(WebElement element,int index) {
		
			s.selectByIndex(index);
		}
		
		/**
		 * This method is used to select visibletext from dropdown
		 * @param visibleText
		 * @param element
		 */
		public static  void selectByVisibleText(WebElement element, String visibleText ) {
			
			s.selectByVisibleText(visibleText);
		}
		
		/**
		 * This method is used to switch the frame by frame element
		 * @param driver
		 * @param element
		 */
		public static  void switchToFrame(WebDriver driver,WebElement element) {
			driver.switchTo().frame(element);
		}
		
		public static  void switchToFrameByIndex(WebDriver driver, int index) {
			driver.switchTo().frame(index);
		}
		
		
		
		public static  void switchToFrameId(WebDriver driver, String nameorID) {
			driver.switchTo().frame(nameorID);
		}
		
		/**
		 * This method is used to switch back to the main web page
		 * @param driver
		 */
		public static void switchBackToHomeWebPage(WebDriver driver) {
			driver.switchTo().defaultContent();
		}
			
	
		public static void intializeJs(WebDriver driver) {
			js=(JavascriptExecutor)driver;
		}
		
		public static void enterDataThroughJs(WebElement element,String data) {
			js.executeScript("arguments[0].value=arguments[1]", element,data);
		}
		
		public static  void clickThroughJs(WebElement element) {
			js.executeScript("arguments[0].click()", element);
		}
		
		public static void navigateApplicationThroughJs(String url) {
			js.executeScript("window.location=arguments[0]", url);
		}
		
		public static void scrollToSpecifiedHeight(String height) {
			js.executeScript("window.scrollBy(0,"+height+")");
		}
		
		public static  void scrollToBottom() {
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}
		
		public static void scrollTillElement(WebElement element) {
			js.executeScript("arguments[0].scrollIntoView()",element);
		}
		
		public static void takeScreenShot(String fileName,WebDriver driver) throws IOException {
			TakesScreenshot ts=(TakesScreenshot)driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dst = new File("./screenshot/"+fileName+"_"+jutil.dateTimeInFormat()+".png");
			System.out.print(dst.getAbsolutePath());
			FileUtils.copyFile(src, dst);
			
		}
		public static String takeScreenShot1(String fileName,WebDriver driver) throws IOException {
			
			TakesScreenshot ts=(TakesScreenshot)driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dst = new File("./screenshot/"+fileName+"_"+jutil.dateTimeInFormat()+".png");
			FileUtils.copyFile(src, dst);
			return dst.getAbsolutePath();
				
		}
		
		}
			
		
		
	

