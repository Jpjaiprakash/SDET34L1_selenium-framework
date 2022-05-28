package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {
	@FindBy(id="dtlview_First Name")
	private WebElement firstname;
	
	@FindBy(id="dtlview_Last Name")
		private WebElement lastname;
	
	
	public ContactInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getFirstName() {
		return firstname.getText();
	}
	
	public String getLastName() {
		return lastname.getText();
	}
}
