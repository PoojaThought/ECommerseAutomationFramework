package AutomationProjects.ECommerseAutomationFramework.TestComponents;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseTest implements ITestListener
{
	ExtentTest test;
	ExtentReports extent = getExtentReportObject();
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		test.log(Status.PASS, "Test Passed.");
		
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		test.fail(result.getThrowable());		
		try 
		{			
			try 
			{
				driver =  (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) 
			{			
				e.printStackTrace();
			}			
			String ssPath = getScreenshot(result.getMethod().getMethodName(), driver);
			test.addScreenCaptureFromPath(ssPath, result.getMethod().getMethodName());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) 
	{
		extent.flush();
		
	}
	
}
