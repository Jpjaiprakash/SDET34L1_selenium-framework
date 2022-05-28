package com.vtiger.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34L1.genericUtility.WebDriverUtility;

public class SearchProductsPage {
	
	@FindBy(xpath="//input[@name='search_text']")
	private WebElement searchTxt;
	
	@FindBy(xpath="//input[@class='crmbutton small create']")
	private WebElement searchBtn;
	
	public SearchProductsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void selectProduct(String productName, WebDriver driver) {
		WebDriverUtility.switchToWindowBasedTitle(driver,"Compaigns");
		searchTxt.sendKeys(productName);
		searchBtn.click();
		driver.findElement(By.xpath("//a[.='"+productName+"']")).click();
		
	}

}
