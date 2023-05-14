package com.tutorialsninja.qa.base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class BaseClass {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	public BaseClass() {
		prop=new Properties();
		try {
		FileInputStream FS=new FileInputStream(System.getProperty("user.dir")+"\\configuration\\config.properties");
		prop.load(FS);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		dataProp = new Properties();
		try {
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\testdata\\testdata.properties");
		dataProp.load(fis);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver initializeBrowserAndOpenApplicationUrl(String browserName) {

		if(browserName.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if (browserName.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_WAIT_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
}
