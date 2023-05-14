package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.SearchPage;
import com.tutorialsninja.qa.base.BaseClass;

public class SearchTest extends BaseClass {
	public WebDriver driver;
	HomePage homePage;
	SearchPage searchPage ;
	
	public SearchTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		driver=initializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));
		homePage=new HomePage(driver);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifySearchingWithExistingProduct() {
		searchPage =homePage.enterAndSearchingWithExistingProduct(dataProp.getProperty("validProduct"));
		
		Assert.assertTrue(searchPage.displayStatusOfValidHPProduct(),"valid product HP is Not Displayed ");
		
	}
	
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		searchPage=homePage.enterAndSearchWithInvalidProduct(dataProp.getProperty("invalidProduct"));
		
		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage, "abcd","No product message in search results is not displayed");
	}
	
	@Test(priority = 3,dependsOnMethods = {"verifySearchingWithExistingProduct","verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() {
		searchPage=homePage.SearchWithoutEnteringAnyProduct();
		
		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage, dataProp.getProperty("noProductMatchesWarningMessage"),"No product message in search results is not displayed");
	
	}
}
