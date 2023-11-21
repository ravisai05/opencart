package com.utlites;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class MYListner implements ITestListener{
	public void onTestStart(ITestResult result) {
	    System.out.println("Test Started.............");
	  }
	public void onTestSuccess(ITestResult result) {
	    // not implemented
		System.out.println("Test Success.............");
	  }
	 public void onTestFailure(ITestResult result) {
		    // not implemented
		 System.out.println("Test Failed.............");
		  }
	 public void onTestSkipped(ITestResult result) {
		    // not implemented
		 System.out.println("Test Skipped.............");
		  }
}
