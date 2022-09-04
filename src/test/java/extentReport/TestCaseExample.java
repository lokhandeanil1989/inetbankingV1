package extentReport;

import org.junit.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;

public class TestCaseExample 
{
	@Test
	public void testMethod1()
	{
		System.out.println("This is test method 1....");
		Assert.assertTrue(true); //pass
	}

	@Test
	public void testMethod2()
	{
		System.out.println("This is test method 2....");
		Assert.assertTrue(false); //fail
	}

	@Test
	public void testMethod3()
	{
		System.out.println("This is test method 3....");
		Assert.assertTrue(false); //fail
	}

	@Test
	public void testMethod4()
	{
		System.out.println("This is test method 4....");
		Assert.assertTrue(true); //pass
	}

	@Test
	public void testMethod5()
	{
		System.out.println("This is test method 5....");
		throw new SkipException("This test method is skipped.....");
	}








}
