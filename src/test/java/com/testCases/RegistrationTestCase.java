package com.testCases;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.logging.Logger;

import org.testng.annotations.Test;
import com.pageObjects.HomePage;
import com.pageObjects.RegisterPag;
import com.testBase.BaseTestCase;


public class RegistrationTestCase extends BaseTestCase {
	@Test(groups = {"reg","master"})
	void regstrationTestCase() throws InterruptedException  {
	try {
		logger.info("***************************Starting TC_01***************************");
		HomePage hp=new HomePage(driver);
		hp.clickMyAcct();
		hp.clickRegister();
		logger.info("IN to Register Page");
		RegisterPag rp=new RegisterPag(driver);
		logger.info("Entering Credentials");
		String s=generateUserName();
		rp.setFirstName(s);
		rp.setLastName(s+" last");
		rp.setEmail(s+"@gmail.com");
		String p=generatePassword();
		rp.setPassword(p);
		rp.agreePrivacy();
		rp.clickRegister();
		Thread.sleep(Duration.ofSeconds(20));
		String cf=rp.confirmationMsg();
		logger.info("capturing the  Title");
		assertEquals(cf, "Your Account Has Been Created!");
		
	}catch (Exception e) {
		// TODO: handle exception
		logger.info("Failed");
		e.getMessage();
	}
		
		
	}

}
