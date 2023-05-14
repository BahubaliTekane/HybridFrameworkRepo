package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.tutorialsninja.pages.AccountPage;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.LoginPage;
import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends BaseClass {
	public WebDriver driver;
	
	LoginPage loginPage;
	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		loginPage=homePage.navigateToLoginPage();
		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	
	@Test(priority = 1)
	public void verifyLoginWithValidCredentials() {
	
		AccountPage accountPage=loginPage.enterEmailAndPasswordAndClickOnLogin(prop.getProperty("validEmail"), prop.getProperty("validPassword"));
		
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit your account information is not Displayed");

	}
/*
	// dataProvider using 2 dimensional array object
	// Here data is Hard coded
	@DataProvider(name="invalidCredentialsSupplier")
	public Object[][] supplyTestData() {
		Object[][] data = { { "admin1@xyz.com", "12345" }, { "admin2@xyz.com", "12345" },
				{ "admin3@xyz.com", "12345" } };
		return data;
	}
	

	@Test(priority = 2,dataProvider = "invalidCredentialsSupplier")
	public void verifyLoginWithInValidCredentials(String email,String password) {

		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		String actualWarningMessage = driver
				.findElement(By.xpath("//div[text()='Warning: No match for E-Mail Address and/or Password.']"))
				.getText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Edit your account information is not Displayed");

	}
*/
	
	@DataProvider(name="inValidCredentialsSupplier")
	public Object[][] supplyData() {
		Object[][] data=Utilities.getTestDataFromExcel("Login");
				return data;
	}
	
	@Test(priority = 2)
	public void verifyLoginWithInValidCredentials() {
		
		loginPage.enterEmailAndPasswordAndClickOnLogin(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
		
		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessage();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Edit your account information is not Displayed");

	}
	
	
	@Test(priority = 3)
	public void verifyLoginWithInValidEmailAndValidPassword() {
		loginPage.enterEmailAndPasswordAndClickOnLogin(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		
		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessage();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Edit your account information is not Displayed");

	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
	
		loginPage.enterEmailAndPasswordAndClickOnLogin(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		
		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessage();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Edit your account information is not Displayed");
	}

	@Test(priority = 5)
	public void verifyLoginWithwithoutProvidingAnyCredentials() {
		
		loginPage.clickOnLoginButton();

		String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessage();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Edit your account information is not Displayed");

	}
}
