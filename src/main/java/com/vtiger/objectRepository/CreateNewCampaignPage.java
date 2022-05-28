package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewCampaignPage {
	@FindBy(name="campaignname")
	private WebElement CampaignTxt;
	
	@FindBy(name="button")
	private WebElement Savebtn;
	
	@FindBy(xpath="//td[contains(.,'Product') and @class='dvtCellLabel']/following-sibling::td/img")
	private WebElement searchProductsLookupImg;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement campaignsaveBtn;
	
	
	public CreateNewCampaignPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void CreateCampaignAction(String campaignname ) {
	CampaignTxt.sendKeys(campaignname);
	Savebtn.click();
	}
	
	public void enterCampaignNameAndSwitchToSearchProduct(String campaignName, WebDriver driver) {
		CampaignTxt.sendKeys(campaignName);
		searchProductsLookupImg.click();
	}
	
	public void saveCampaign() {
		campaignsaveBtn.click();
		
	}
}
	


