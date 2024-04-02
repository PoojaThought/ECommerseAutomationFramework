package AutomationProjects.ECommerseAutomationFramework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationProjects.ECommerseAutomationFramework.abstractComponenents.AbstractComponent;

public class CheckoutPage extends AbstractComponent
{
	WebDriver driver;
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By countriesBy = By.cssSelector(".ta-results");
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement countryDD;
	
	@FindBy(css=".ta-item:nth-child(3")
	WebElement countryIND;
	
	@FindBy(css=".action__submit")
	WebElement submitBtn;
	
	public void selectCountry(String country)
	{
		Actions a = new Actions(driver);
		a.sendKeys(countryDD,country).build().perform();
		waitForElementToAppear(countriesBy);
		countryIND.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		submitBtn.click();
		ConfirmationPage cm = new ConfirmationPage(driver);
		return cm;
	}
}
