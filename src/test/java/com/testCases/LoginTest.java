package com.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.MyAcctPage;
import com.testBase.BaseTestCase;

public class LoginTest extends BaseTestCase {
	
	@Test(groups = {"sanity","master"})
	public void LoginTestCase() {
		try {
			logger.info("***************************Starting TC_02***************************");
			HomePage hp=new HomePage(driver);
			logger.info("Login Page");
			hp.clickMyAcct();
			hp.clickLogin();
			LoginPage lp=new LoginPage(driver);
			logger.info("providing login details");
			lp.setEmail(res.getString("email"));
			lp.setPassword(res.getString("password"));
			lp.clickLogin();
			logger.info("Clicked on login");
			MyAcctPage ap=new MyAcctPage(driver);
			boolean actualpageexist=ap.isMyAccountExist();
			Assert.assertEquals(actualpageexist, true);
			logger.info("Done case 2!!!!!!!!!!");
		}catch (Exception e) {
			// TODO: handle exception
			Assert.fail();
		}
		
		
	}
}
