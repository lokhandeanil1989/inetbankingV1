package extentReport;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListenerClass implements ITestListener
{
	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;

	public void configureReport()
	{
		htmlReporter=new ExtentSparkReporter("ExtentListenerReportdemo.html");
		reports=new ExtentReports();
		reports.attachReporter(htmlReporter);

		//add environment details 
		reports.setSystemInfo("Machine", "Lenovo PC");
		reports.setSystemInfo("OS","Windows 10");
		reports.setSystemInfo("User", "Anil");
		reports.setSystemInfo("Browser", "Chrome");

		//configuration to  change the look and feel
		htmlReporter.config().setDocumentTitle("Extent Listener Report Demo");
		htmlReporter.config().setReportName("Test Report");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}
	public void onStart(ITestContext Result)
	{
		configureReport();
		System.out.println("On statrt method invoked...");
	}

	public void onFinish(ITestContext Result)
	{
		System.out.println("On finished method invoked...");
		reports.flush();
	}

	//When test case get failed, this method is called
	public void onTestFailure(ITestResult Result)
	{
		System.out.println("Name of the test method failed : "+Result.getName());
		test=reports.createTest(Result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed test case is : "+Result.getName(), ExtentColor.RED));
	}

	public void onTestSkipped(ITestResult Result)
	{
		System.out.println("Name of the test method skipped : "+Result.getName());
		test=reports.createTest(Result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of the skip test case is : "+Result.getName(), ExtentColor.YELLOW));
	}

	//When test case get started, this method is called
	public void onTestStart(ITestResult Result)
	{
		System.out.println("Name of the test method started : "+Result.getName());	
	}

	//When test case get passed, this method is called
	public void onTestSuccess(ITestResult Result)
	{
		System.out.println("Name of the test sucessfully executed : "+Result.getName());
		test=reports.createTest(Result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel("Name of the pass test case is : "+Result.getName(), ExtentColor.GREEN));
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult Result)
	{

	}

}
