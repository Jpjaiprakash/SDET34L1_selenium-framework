package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ValidationTestcasesPage {
	
	@FindBy(id="dtlview_Last Name")
	private WebElement leadlastnameText;
	
	public ValidationTestcasesPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getleadlastname() {
		return leadlastnameText.getText();
	}

}
