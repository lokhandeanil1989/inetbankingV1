package extentReport;

import org.junit.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BasicExtentReport 
{
	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;

	@BeforeTest
	public void startReport()
	{
		htmlReporter=new ExtentSparkReporter("ExtentReportDemo.html");
		reports =new ExtentReports();
		reports.attachReporter(htmlReporter);

		//add environment details 
		reports.setSystemInfo("Machine", "Anil's PC");
		reports.setSystemInfo("OS","Windows 10");
		reports.setSystemInfo("User", "Anil");
		reports.setSystemInfo("Browser", "Chrome");

		//configuration to  change the look and feel
		htmlReporter.config().setDocumentTitle("Extent Report Demo");
		htmlReporter.config().setReportName("Test Report");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}

	@Test
	public void LaunchBrowserAndOpenBrowser()
	{
		//create test
		test=reports.createTest("Launch Browser and open URL");
		Assert.assertTrue(true); //test passed
	}

	@Test
	public void VerifyTitle()
	{
		//create test
		test=reports.createTest("Verify Title");
		Assert.assertTrue(false); //test failed
	}

	@Test
	public void VerifyLogo()
	{
		//create test
		test=reports.createTest("Verify Logo");
		Assert.assertTrue(true); //test passed
	}

	@Test
	public void VerifyEmail()
	{
		//create test
		test=reports.createTest("Verify Email");
		throw new SkipException("Skipping this test case woth exception..");
	}

	@Test
	public void VerifyUserName()
	{
		//create test
		test=reports.createTest("Verify Username");
		Assert.assertTrue(true); //test passed
	}

	@AfterMethod
	public void getTestResult(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+ " FAIL ", ExtentColor.RED));
			test.fail(result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+ " PASS ", ExtentColor.GREEN));
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+ " SKIPPED ", ExtentColor.YELLOW));
		}
	}

	@AfterTest
	public void tearDown()
	{
		reports.flush();
	}
}
