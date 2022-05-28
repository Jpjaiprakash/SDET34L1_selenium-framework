package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewLeadPage {
	@FindBy(name="lastname")
	private WebElement lastnameTf;
	
	@FindBy(name="company")
	private WebElement companyTf;
	
	@FindBy(xpath="//input[@accesskey='S']")
	private WebElement saveleadBtn;
	
	public CreateNewLeadPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void enterlastnameTf(String lastname) {
		lastnameTf.sendKeys(lastname);
	}
	
	public void entercompanynameTf(String companyname) {
		companyTf.sendKeys(companyname);
	}
	
	public void leadsaveBtn() {
		saveleadBtn.click();
	}
	

}
