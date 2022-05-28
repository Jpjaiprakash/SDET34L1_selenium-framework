package com.vtiger.objectRepository;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34L1.genericUtility.WebDriverUtility;

public class EnterDataToCreateDocument {
	
	@FindBy(name="notes_title")
	private WebElement documentTitleTf;
	
	@FindBy(xpath="//body[@class='cke_show_borders']")
	private WebElement enterDataToTAF;
	
	@FindBy(id="cke_5")
	private WebElement boldBtn;
	
	@FindBy(id="cke_6")
	private WebElement italicBtn; 
	
	@FindBy(name="filename")
	private WebElement filenameBtn;
	
	@FindBy(name="button")
	private WebElement documentSaveBtn;
	
	@FindBy(xpath="//iframe[@title='Rich text editor, notecontent, press ALT 0 for help.']")
	private WebElement switching;
	
	public EnterDataToCreateDocument(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void EnterDocumentTitle(String title) {
		documentTitleTf.sendKeys(title);
		
	}
	
	public void SwitchToTF(WebDriver driver, int index, String documentdescription) {
		WebDriverUtility.switchToFrameByIndex(driver, index);
		WebDriverUtility.switchToFrame(driver, switching);
		enterDataToTAF.sendKeys(documentdescription,Keys.CONTROL+"a");
		WebDriverUtility.switchBackToHomeWebPage(driver);
		driver.switchTo().defaultContent();
	}
	
	public void clickOnBoldBtn() {
		boldBtn.click();
	}
	
	public void clickOnItalicBtn() {
		italicBtn.click();
	}
	
	public void clickOnFilenameBtn(String documentpath) {
		filenameBtn.sendKeys(documentpath);
	}
	
	public void clickOnDocumentSaveBtn() {
		documentSaveBtn.click();
	}

}
