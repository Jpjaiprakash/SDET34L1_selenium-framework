package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34L1.genericUtility.WebDriverUtility;

	public class CreateNewOrganizationPage {
	@FindBy(name="accountname")
	private WebElement organizationNameTxt;
	
	@FindBy(name="industry")
	private WebElement industryDropdown;
	
	@FindBy(name="accounttype")
	private WebElement typeDropdown;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement organizationSaveBtn;
	
	public CreateNewOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void organizationData(String organizationName) {
		organizationNameTxt.sendKeys(organizationName);
		organizationSaveBtn.click();
	}

	public void industryDrop(String visibleText) {
		WebDriverUtility.initializeDropDown(industryDropdown);
		WebDriverUtility.selectByVisibleText(industryDropdown, visibleText);
	}
	
	public void typeDrop(String visibleText) {
		WebDriverUtility.initializeDropDown(typeDropdown);
		WebDriverUtility.selectByVisibleText(typeDropdown, visibleText);
	}
	
	public void organizationSave() {
		organizationSaveBtn.click();
	}
}
