package com.utlites;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.testBase.BaseTestCase;

public class ExtentReportManager implements ITestListener{
	//UI of Report
	public ExtentSparkReporter sparkReporter;
	//Populating common info
	public ExtentReports extentReports;
	//creating test case entries
	public ExtentTest extentTest;
	
	public   void onTestStart(ITestResult result) {
	    // not implemented
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/report/myReports.html");
		sparkReporter.config().setDocumentTitle("Automation Testing");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		extentReports=new ExtentReports();
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("Website", "PrestaShop");
		extentReports.setSystemInfo("Envirorment", "QA");
		extentReports.setSystemInfo("Tester Name", "Ravi");
		extentReports.setSystemInfo("Browser", "Chrome,edge");
	  } 
	public void onTestSuccess(ITestResult result) {
	    // not implemented
		extentTest=extentReports.createTest(result.getName());
		extentTest.log(Status.PASS, "Test Case Passed IS:" + result.getName());
		
	  }
	public void onTestFailure(ITestResult result) {
	    // not implemented
		extentTest=extentReports.createTest(result.getName());
		extentTest.log(Status.FAIL, "Test Case Failed IS:" + result.getName());
		extentTest.log(Status.FAIL, result.getThrowable());
		try {
			String imp=new BaseTestCase().captureScreenshot(result.getName());
			extentTest.addScreenCaptureFromPath(imp);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	  }
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extentTest.log(Status.INFO, "Test Finished");

        // Flush the Extent Reports instance
        extentReports.flush();
	}
	 
	

}
