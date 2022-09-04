package com.inetbanking.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//listener class used to generate Extent reports
public class Reporting implements ITestListener
{
	ExtentSparkReporter htmlReporter;
	ExtentReports extent;
	ExtentTest logger;

	public void configureReport()
	{
		//String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp
		//String repName="Test-Report-"+timestamp+".html";
		//htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/test-output/"+repName); //specify location
		htmlReporter=new ExtentSparkReporter("NewExtentListenerReport.html");
		//htmlReporter=new ExtentSparkReporter(repName);
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		//add environment details 
		extent.setSystemInfo("Machine", "Lenovo PC");
		extent.setSystemInfo("OS","Windows 10");
		extent.setSystemInfo("User", "Anil");
		extent.setSystemInfo("Browser", "Chrome");

		//configuration to  change the look and feel
		htmlReporter.config().setDocumentTitle("InetBanking Test Project"); //title of the report
		htmlReporter.config().setReportName("Test Report");  //name of the report
	//	htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //Location of the chart
		htmlReporter.config().setTheme(Theme.DARK); //theme
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}
	public void onStart(ITestContext textContext)
	{
		configureReport();
		System.out.println("On start method invoked...");
	}
	public void onTestSuccess(ITestResult tr)
	{
		logger=extent.createTest(tr.getName());
		logger.log(Status.PASS,MarkupHelper.createLabel (tr.getName(),ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult tr)
	{
		logger=extent.createTest(tr.getName());
		logger.log(Status.FAIL,MarkupHelper.createLabel (tr.getName(),ExtentColor.RED));

		String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		File f=new File(screenshotPath);

		if(f.exists())
		{
			try
			{
				logger.fail("Screenshot is below : "+logger.addScreenCaptureFromPath(screenshotPath));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName());
		logger.log(Status.SKIP,MarkupHelper.createLabel (tr.getName(),ExtentColor.YELLOW));
	}
	public void onFinish(ITestResult tr)
	{
		extent.flush();
	}
	public void onTestFailedButWithinSuccessPercentage(ITestResult Result)
	{

	}
}
