package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentPage {
	
	@FindBy(xpath="//a[text()='Documents']")
	private WebElement documentsTab;
	
	public DocumentPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnDocumentsTab() {
		documentsTab.click();
	}

}
