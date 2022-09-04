package com.inetbanking.testCases;

import java.io.IOException;
import org.junit.Assert;
import org.testng.annotations.Test;
import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass
{
	@Test
	public void AddNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("User name is provided");
		lp.setPassword(password);
		logger.info("Password is provided");
		lp.clickSubmit();
		Thread.sleep(3000);

		AddCustomerPage addcust=new AddCustomerPage(driver);
		addcust.clickAddNewCustomer();

		logger.info("Providing customer details...");

		addcust.custName("Anil");
		addcust.custgender("Male");
		addcust.custdob("10","11","1989");
		//addcust.custdob("21","02","1989");
		Thread.sleep(5000);
		addcust.custaddress("India");
		addcust.custcity("Pune");
		addcust.custstate("MH");
		addcust.custpinno("421605");
		addcust.custtelephoneno("1234567890");
		String email=randomstring()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		addcust.custsubmit();
		Thread.sleep(3000);

		logger.info("Validation started ....");
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");

		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("Test case is passed....");
		}
		else
		{
			logger.info("Test case is failed....");
			captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
		}
	}
}
