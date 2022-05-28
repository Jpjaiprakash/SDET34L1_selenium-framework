package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	@FindBy(id="dtlview_Organization Name")
	private WebElement organizationNameText;
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement newOrganizationInfoText;

	public OrganizationInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String getOrganization() {
		return organizationNameText.getText();
	}
	
	public String getNewOrganizationName() {
		return newOrganizationInfoText.getText();
	}
	}
