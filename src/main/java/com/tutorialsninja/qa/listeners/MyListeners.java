package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener {
	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;
	public void onStart(ITestContext context) {
		 extentReport = ExtentReporter.generateExtentReport();
	}
	
	public void onTestStart(ITestResult result) {
		testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO,testName+" Started executing ");
			
	}

	public void onTestSuccess(ITestResult result) {
		
		extentTest.log(Status.PASS, testName+" got successfilly executing ");
		
		//extentTest.log(Status.PASS, result.getName()+" got successfilly executing ");
	
	}

	public void onTestFailure(ITestResult result) {
		
		System.out.println("ScreenShot taken");
		
		WebDriver driver=null;;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		//getClass(result.getInstance());
		
		String destinationScreenshotPath = Utilities.captureScreenshot(driver, testName);
		
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName+" got Failed");
		
	}

	public void onTestSkipped(ITestResult result) {
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+" got Skipped");
		
	}

	public void onFinish(ITestContext context) {
		extentReport.flush();
		
		String pathOfExtentReport=System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentreport=new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentreport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
}
