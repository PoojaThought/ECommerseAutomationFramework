package AutomationProjects.ECommerseAutomationFramework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import AutomationProjects.ECommerseAutomationFramework.pageobjects.LandingPage;

public class BaseTest 
{	
	public WebDriver driver;
	public LandingPage lp;
	public ExtentReports extent;
	public WebDriver InitializeDriver() throws IOException
	{		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\AutomationProjects\\ECommerseAutomationFramework\\resources\\global.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage LaunchApplication() throws IOException
	{
		driver = InitializeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		lp = new LandingPage(driver);
		return lp;
	}
	
	@AfterMethod(alwaysRun=true)
	public void CloseApplication()
	{
		driver.close();
	}
	
	public List<HashMap<String,String>> getJSONDataToMap(String filePath) throws IOException
	{
		String jsonInString = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper op = new ObjectMapper();
		List<HashMap<String,String>> data = op.readValue(jsonInString , new TypeReference<List<HashMap<String,String>>>() {
			});
		return data;
	}
	
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
		FileUtils.copyFile(src, des);
		return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
	}
	
	public ExtentReports getExtentReportObject()
	{
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Basic Login/Purchase Valisations");
		reporter.config().setDocumentTitle("ECommerse Application Automation");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Pooja Desai");
		
		return extent;
		
	}
}
