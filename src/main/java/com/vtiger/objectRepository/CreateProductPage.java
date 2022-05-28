package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateProductPage {
	@FindBy(xpath="//img[@title='Create Product...']")
	private WebElement createproductBtn;
	
	

	public CreateProductPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}



	public void clickOnCreateProduct() {
		createproductBtn.click();
	}

}
