package com.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sdet34L1.genericUtility.WebDriverUtility;

public class HomePage {

	//create the class as web page name and make it as public
	
		//declare all the elements and specify the access specific as private
		@FindBy(linkText = "More" )
		private WebElement moreDropDown;
		
		@FindBy(name="Campaigns")
		private WebElement campaignTab;
		
		@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
		private WebElement administratorIcon;
		
		@FindBy(xpath="//a[text()='Sign Out']")
		private  WebElement signOutLink;
		
		@FindBy(xpath="//a[text()='Products']")
		private WebElement productsTab;
		
		@FindBy(xpath="//a[text()='Contacts']")
		private WebElement contactsTab;
		
		@FindBy(xpath="//a[text()='Organizations']")
		private WebElement organizationsTab;
		
		@FindBy(xpath="//a[text()='Leads']")
		private WebElement leadsTab;
		
		//initialize the driver to all the elements through constructors and make it as public
		
		public HomePage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		
		//buisness lib
		public void clickCampaign(WebDriver driver) {
			
			WebDriverUtility.mouseHoverOnTheElement(moreDropDown, driver);
			campaignTab.click();
		}
		
		public void clickproducts() {
			productsTab.click();
		}
		
		public void clickcontactsTab() {
			
			contactsTab.click();
		}
		
		public void clickLeadsTab() {
			leadsTab.click();
		}
		
		public void signout(WebDriver driver) {
			
			WebDriverUtility.mouseHoverOnTheElement(administratorIcon, driver);
			signOutLink.click();
		}
		
		public void clickorganizationsTab() {
			organizationsTab.click();
		}

		

}
