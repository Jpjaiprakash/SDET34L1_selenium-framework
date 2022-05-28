package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewProductPage {
	@FindBy(name="productname")
	private WebElement productName;
	
	@FindBy(name="button")
	private WebElement productSaveBtn;
	
	public CreateNewProductPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void enterProductName(String productname) {
		productName.sendKeys(productname);
		productSaveBtn.click();
	}

}
