package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewDocumentPAge {
	@FindBy(xpath="//img[@title='Create Document...']")
	private WebElement CreateDocumentBtn;
	
	public CreateNewDocumentPAge(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnCreateDocument() {
		CreateDocumentBtn.click();
	}

}
