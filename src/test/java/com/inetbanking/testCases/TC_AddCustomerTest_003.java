package com.inetbanking.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;
import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass 
{
	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("User name provided");
		lp.setPassword(password);
		logger.info("Password provided");
		lp.clickSubmit();
		Thread.sleep(3000);

		AddCustomerPage addcust=new AddCustomerPage(driver);

		addcust.clickAddNewCustomer();
		
		logger.info("Providing customer details...");
		addcust.custName("Anil");
		addcust.custgender("Male");
		addcust.custdob("02","21","1989");
		Thread.sleep(5000);
		addcust.custaddress("Godavari Nagar");
		addcust.custcity("Nanded");
		addcust.custstate("MAHARASHTRA");
		addcust.custpinno(431605);
		addcust.custtelephoneno("9021819785");

		String email=randomstring()+"@gmail.com";
		addcust.custemailid(email); //Unique email id

		addcust.custpassword("abcdef");
		addcust.custsubmit();
		
		Thread.sleep(3000);
		
		logger.info("Validation started .....");
		captureScreen(driver,"addNewCustomer");
		
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");

		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("Test case passed .....");
		}
		else
		{
			logger.info("Test case failed .....");
			captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
		}
	}
}
