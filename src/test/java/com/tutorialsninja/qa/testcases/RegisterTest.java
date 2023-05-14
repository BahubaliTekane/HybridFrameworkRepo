package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.pages.AccountSuccessPage;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.RegisterPage;
import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends BaseClass{
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	@BeforeMethod
	public void setUp() {
		driver=initializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		registerPage=homePage.navigateToRegisterPage();
		
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		
		accountSuccessPage=registerPage.enterRegisterDetailsWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));	
		
		accountSuccessPage.retrieveAccountSuccessPageHeading();
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Has Been not Created");
		
	}
	
	@Test(priority = 2)
	public void verifyRegisteringAccountBYProvidingAllFields() {
	
		accountSuccessPage=registerPage.enterRegisteringAccountBYProvidingAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		
		accountSuccessPage.retrieveAccountSuccessPageHeading();
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Has Been not Created");
		
	}
	
	@Test(priority = 3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {
		
		registerPage.enterRegisteringAccountBYProvidingAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
	
		String actualWarning = registerPage.retrieveDuplicateEmailAddressWarningMeassage();
		Assert.assertEquals(actualWarning, dataProp.getProperty("duplicateEmailWarning"),"Warning Message Regarding duplicate Duplicate email address is not displayed");
	
	}
	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {
	
		registerPage.clickOnContinueButton();
		
		Assert.assertTrue(registerPage.displayStatusOfWarningMessages(dataProp.getProperty("privacyPolicyWarning"), dataProp.getProperty("firstNameWarning"), dataProp.getProperty("lastNameWarning"), dataProp.getProperty("emailWarning"), dataProp.getProperty("telephoneWarning"), dataProp.getProperty("passwordWarning")),"warning message(s) are not displayed");
		/*
		Assert.assertEquals(registerPage.retrievePrivacyPolicyWarning(), dataProp.getProperty("privacyPolicyWarning"),"warning message(s) are not displayed");
		
		Assert.assertEquals(registerPage.retrieveFirstNameWarning(), dataProp.getProperty("firstNameWarning"),"FirstName warning message is not displayed");
		
		Assert.assertEquals(registerPage.retrieveLastNameWarning(), dataProp.getProperty("lastNameWarning"),"LastName warning message is not displayed");
		
		Assert.assertEquals(registerPage.retrieveEmailWarning(), dataProp.getProperty("emailWarning"),"Email warning message is not displayed");
		
		Assert.assertEquals(registerPage.retrieveTelephoneWarning(), dataProp.getProperty("telephoneWarning"),"Telephone warning message is not displayed");
		 
		Assert.assertEquals( registerPage.retrievePasswordWarning(), dataProp.getProperty("passwordWarning"),"Password warning message is not displayed");
		*/
		
	}
}
