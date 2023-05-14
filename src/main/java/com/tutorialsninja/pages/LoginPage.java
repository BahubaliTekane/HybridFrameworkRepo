package com.tutorialsninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	@FindBy(id = "input-email")
	private WebElement emailAddressField;
	
	@FindBy(id = "input-password")
	private WebElement passwordField;
	
	@FindBy(xpath  = "//input[@type='submit']")
	private WebElement loginButton;
	
	@FindBy(xpath = "//div[text()='Warning: No match for E-Mail Address and/or Password.']")
	WebElement emailPasswordNotMatchWarning;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public AccountPage enterEmailAndPasswordAndClickOnLogin(String Email,String Password) {
		emailAddressField.sendKeys(Email);
		passwordField.sendKeys(Password);
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public String retrieveEmailPasswordNotMatchingWarningMessage() {
		String warningText = emailPasswordNotMatchWarning.getText();
		return warningText;
	}
	
	public void clickOnLoginButton() {
		loginButton.click();
	}
	
	
}
