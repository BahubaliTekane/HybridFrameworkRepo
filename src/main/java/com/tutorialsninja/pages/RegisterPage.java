package com.tutorialsninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	
	@FindBy(id = "input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id = "input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id = "input-email")
	private WebElement emailAddressField;
	
	@FindBy(id = "input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id = "input-password")
	private WebElement passwordField;
	
	@FindBy(id = "input-confirm")
	private WebElement passwordConfirmField;
	
	@FindBy(xpath = "//input[@type='checkbox']")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement continueButton;
	
	@FindBy(name = "newsletter")
	private WebElement selectYesNewsletterOption;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement PrivacyPolicyWarning;
	
	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement FirstNameWarning;
	
	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement LastNameWarning;
	
	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement EmailWarning;
	
	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement TelephoneWarning;
	
	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement PasswordWarning;
	
	
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnContinueButton() {
		continueButton.click();
	}
	
	public AccountSuccessPage enterRegisterDetailsWithMandatoryFields(String firstName,String lastName,String emailAddress,String telephone,String password,String passwordConfirm) {
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		emailAddressField.sendKeys(emailAddress);
		telephoneField.sendKeys(telephone);
		passwordField.sendKeys(password);
		passwordConfirmField.sendKeys(password);
		privacyPolicyField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
		
	}
	
	public AccountSuccessPage enterRegisteringAccountBYProvidingAllFields(String firstName,String lastName,String emailAddress,String telephone,String password,String passwordConfirm) {
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		emailAddressField.sendKeys(emailAddress);
		telephoneField.sendKeys(telephone);
		passwordField.sendKeys(password);
		passwordConfirmField.sendKeys(password);
		selectYesNewsletterOption.click();
		privacyPolicyField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
		
	}
	
	public void enterRegisteringAccountWithExistingEmailAddress(String firstName,String lastName,String emailAddress,String telephone,String password,String passwordConfirm) {
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		emailAddressField.sendKeys(emailAddress);
		telephoneField.sendKeys(telephone);
		passwordField.sendKeys(password);
		passwordConfirmField.sendKeys(password);
		privacyPolicyField.click();
		continueButton.click();
		
	}
	
	public String retrieveDuplicateEmailAddressWarningMeassage() {
		String duplicateEmailAddressWarningMessage = duplicateEmailAddressWarning.getText();
		return duplicateEmailAddressWarningMessage;
	}
	
	public String retrievePrivacyPolicyWarning() {
		String privacyPolicyWarning = PrivacyPolicyWarning.getText();
		return privacyPolicyWarning;
	}
	
	public String retrieveFirstNameWarning() {
		String firstNameWarning = FirstNameWarning.getText();
		return firstNameWarning;
	}
	

	public String retrieveLastNameWarning() {
		String lastNameWarning = LastNameWarning.getText();
		return lastNameWarning;
	}
	

	public String retrieveEmailWarning() {
		String emailWarning = EmailWarning.getText();
		return emailWarning;
	}
	

	public String retrieveTelephoneWarning() {
		String telephoneWarning = TelephoneWarning.getText();
		return telephoneWarning;
	}
	

	public String retrievePasswordWarning() {
		String passwordWarning = PasswordWarning.getText();
		return passwordWarning;
	}
	
	public boolean displayStatusOfWarningMessages(String expectedPrivacyPolicyWarning,String expectedFirstNameWarning,String expectedLastNameWarning,String expectedEmailWarning,String expectedTelephoneWarning,String expectedPasswordWarning) {
		String actualPrivacyPolicyWarning = PrivacyPolicyWarning.getText();
		boolean privacyPolicyWarningStatus = actualPrivacyPolicyWarning.contains(actualPrivacyPolicyWarning);
		
		String actualFirstNameWarning = FirstNameWarning.getText();
		boolean firstNameWarningStatus=actualFirstNameWarning.contains(expectedFirstNameWarning);
		
		String actualLastNameWarning = LastNameWarning.getText();
		boolean lastNameWarningStatus=actualLastNameWarning.contains(expectedLastNameWarning);
		
		String actualEmailWarning = EmailWarning.getText();
		boolean emailWarningStatus=actualEmailWarning.contains(expectedEmailWarning);
		
		String actualTelephoneWarning = TelephoneWarning.getText();
		boolean telephoneWarningStatus=actualTelephoneWarning.contains(expectedTelephoneWarning);
		
		String actualPasswordWarning = PasswordWarning.getText();
		boolean passwordWarningStatus=actualPasswordWarning.contains(expectedPasswordWarning);
		
		return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailWarningStatus && telephoneWarningStatus && passwordWarningStatus; 
		
	}
	
	
	
}
