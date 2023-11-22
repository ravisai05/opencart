package com.testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.math3.geometry.spherical.twod.Edge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestCase {
	public static WebDriver driver;
	public Logger logger;
	public ResourceBundle res;
	@Parameters("browser")
	@BeforeClass(groups = {"sanity","reg","master"})
	public void Setup(String browser) {
		logger=LogManager.getLogger(this.getClass());
		switch (browser){
		case "chrome":
			logger.info("Started Chrome");
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		case "edge":
			logger.info("Started edge");
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			break;
			
		case "firefox":
			logger.info("Started firefox");
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
			
		}
		
		res=ResourceBundle.getBundle("config");
		logger.info("mapped to config");
		System.out.println(res.getString("URL"));
		driver.get(res.getString("URL"));
		logger.info("navigated to url");
		driver.manage().window().maximize();
		logger.info("exit");
		
	}
	@AfterTest(groups = {"sanity","reg","master"})
	public void tearDown() {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	        driver.close();
	}
	public String generateUserName() {
		String s=RandomStringUtils.randomAlphabetic(7);
		return s;
	}
	public String generatePassword() {
		String p=RandomStringUtils.randomAlphanumeric(7);
		return p+"@)!";
	}
	public String captureScreenshot(String nameOfTest) {
		String timeStamp=new SimpleDateFormat("yyyyMMddmmss").format(new Date());
		  TakesScreenshot ts = (TakesScreenshot) driver;
	      File screenshot = ts.getScreenshotAs(OutputType.FILE);
	      String destination=System.getProperty("user.dir")+"\\screenshot\\"+nameOfTest+"_"+timeStamp+".png";
	      try {
	            FileUtils.copyFile(screenshot, new File(destination));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	      return destination;
	}
}
