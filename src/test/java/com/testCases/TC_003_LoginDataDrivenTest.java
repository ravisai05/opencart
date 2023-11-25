package com.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.beust.ah.A;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;
import com.pageObjects.MyAcctPage;
import com.testBase.BaseTestCase;
import com.utlites.DataProviders;

public class TC_003_LoginDataDrivenTest extends BaseTestCase{
	
	@Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class)
	public void dataDrivenTest(String email,String password,String expected) {
		try
		{
		HomePage hp=new HomePage(driver);
		logger.info("Login Page");
		hp.clickMyAcct();
		hp.clickLogin();
		LoginPage lp=new LoginPage(driver);
		logger.info("providing login details");
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLogin();
		MyAcctPage ap=new MyAcctPage(driver);
		boolean actualpageexist=ap.logoutExist();
		if(expected=="valid") {
			if(actualpageexist==true) {
				Assert.assertTrue(true);
				ap.clickLogout();
				
			}
			else {
				Assert.assertTrue(false);
			}
		}
		else {
			if(actualpageexist==true) {
				Assert.assertTrue(false);
				ap.clickLogout();
				
			}
			else {
				Assert.assertTrue(true);
			}
		}
	}catch (Exception e) {
		// TODO: handle exception
		Assert.fail();
	}
	}
}
