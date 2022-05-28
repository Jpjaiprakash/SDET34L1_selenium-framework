package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPage {
	@FindBy(name="firstname")
	private WebElement firstnameTxt;
	
	@FindBy(name="lastname")
	private WebElement lastnameTxt;
	
	@FindBy(name="button")
	private WebElement contactsSaveBtn;
	
	@FindBy(xpath="//img[@title='Select']")
	private WebElement organizationNameLookUpIcon;
	
	public CreateNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void CreateNewContactData(String firstname, String lastname) {
		firstnameTxt.sendKeys(firstname);
		lastnameTxt.sendKeys(lastname);
		contactsSaveBtn.click();
		
	}
	
	public void CreateNewContact1(String firstname, String lastname) {
		firstnameTxt.sendKeys(firstname);
		lastnameTxt.sendKeys(lastname);
		organizationNameLookUpIcon.click();
	}
	
	
	public void ContactSaveBtn() {
		contactsSaveBtn.click();
	}
	
	
	

}
