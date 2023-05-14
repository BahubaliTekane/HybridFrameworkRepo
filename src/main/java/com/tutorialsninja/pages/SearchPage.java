package com.tutorialsninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	WebDriver driver;
	
	@FindBy(linkText = "HP LP3065")
	private WebElement validHPProduct;
	
	@FindBy(xpath = "//input[@id='button-search']/following-sibling::p")
	private WebElement noProductMessage;
	
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void clickOnsearchButton() {
		
	}
	public boolean displayStatusOfValidHPProduct() {
		boolean displayStatus = validHPProduct.isDisplayed();
		return displayStatus;
	}
	
	public String retrieveNoProductMessageText() {
		String noProductMessageText = noProductMessage.getText();
		return noProductMessageText;
	}
	
}
