package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConvertLeadPage {
	
	@FindBy(xpath="//input[@id='select_potential']")
	private WebElement OpportunityCheckBox;
	
	@FindBy(name="Save")
	private WebElement convertLeadSaveBtn;
	
	@FindBy(name="closingdate")
	private WebElement closingDate;
	
	@FindBy(name="amount")
	private WebElement amountTf;
	
	@FindBy(name="lastname")
	private WebElement lastnameConvertLead;
	
	@FindBy(xpath="//input[@id='select_account']")
		private WebElement opportunitiescheckbox;
	
	@FindBy(xpath="//input[@value='Contacts']")
	private WebElement contactCheckBox;
	
	public ConvertLeadPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOpportunityCheckBox() {
		OpportunityCheckBox.click();
	}
	
	public void clickOnConvertLeadSaveBtn() {
		convertLeadSaveBtn.click();
	}

	public void amountTf(String amount) {
		amountTf.sendKeys(amount);
	}
	
	public void closingDateofLead(String date) {
		closingDate.sendKeys(date);
	}
	
	public void lastNameTfInConvertLead() {
		lastnameConvertLead.clear();
	}
	
	public void opportunitiesCheckBox() {
		opportunitiescheckbox.click();
	}
	
	public void contactsCheckboxInConvetLead() {
		contactCheckBox.click();
	}
	
	
	
}
