package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadInformationPage {
	
	@FindBy(xpath="//a[text()='Convert Lead']")
	private WebElement convertLeadBtn;

	public LeadInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnConvertLeadBtn() {
		convertLeadBtn.click();
	}
	
	
	
	

}
