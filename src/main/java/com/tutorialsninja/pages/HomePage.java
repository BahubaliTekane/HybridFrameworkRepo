package com.tutorialsninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	
	//Objects
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText  = "Login")
	private WebElement loginOption;
	
	@FindBy(linkText  = "Register")
	WebElement registerOption;
	
	@FindBy(name = "search")
	WebElement searchBox;
	
	@FindBy(xpath = "//span//button[contains(@class,'btn-lg')]")
	WebElement searchBoxButton;
	
	

	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//Actions
	
	
	public LoginPage navigateToLoginPage() {
		myAccountDropMenu.click();
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage() {
		myAccountDropMenu.click();
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	
	public SearchPage enterAndSearchingWithExistingProduct(String validProduct) {
		searchBox.sendKeys(validProduct);
		searchBoxButton.click();
		return new SearchPage(driver);
	}
	
	public SearchPage enterAndSearchWithInvalidProduct(String invalidProduct) {
		searchBox.sendKeys(invalidProduct);
		searchBoxButton.click();
		return new SearchPage(driver);
	}
	
	public SearchPage SearchWithoutEnteringAnyProduct() {
		searchBoxButton.click();
		return new SearchPage(driver);
	}
	
	
}
