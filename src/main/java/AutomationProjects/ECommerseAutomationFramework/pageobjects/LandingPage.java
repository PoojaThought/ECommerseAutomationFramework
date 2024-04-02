package AutomationProjects.ECommerseAutomationFramework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationProjects.ECommerseAutomationFramework.abstractComponenents.AbstractComponent;

public class LandingPage extends AbstractComponent
{
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement usernameEle;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	

	@FindBy(id="login")
	WebElement loginBtnEle;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement invalidloginErrMsg;
	
	public ProductCataloguePage logintoApplication(String username, String password)
	{
		usernameEle.sendKeys(username);
		passwordEle.sendKeys(password);
		loginBtnEle.click();
		ProductCataloguePage pc = new ProductCataloguePage(driver);
		return pc;
	}
	
	public String getInvalidLoginErrMsg()
	{
		waitForElementToAppear(invalidloginErrMsg);
		return invalidloginErrMsg.getText();
	}
	
}
