package com.vtiger.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34L1.genericUtility.WebDriverUtility;

public class SearchOrgPage {
	@FindBy(name="search_text")
	private WebElement searchTxt;
	
	@FindBy(xpath="//input[@class='crmbutton small create']")
	private WebElement searchBtn;
	
	public SearchOrgPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void selectorganization(String orgname, WebDriver driver) {
		WebDriverUtility.switchToWindowBasedTitle(driver, "Account&action");
		searchTxt.sendKeys(orgname);
		searchBtn.click();
		driver.findElement(By.id("1")).click();
		WebDriverUtility.switchToWindowBasedTitle(driver, "Contacts");
	}
}
