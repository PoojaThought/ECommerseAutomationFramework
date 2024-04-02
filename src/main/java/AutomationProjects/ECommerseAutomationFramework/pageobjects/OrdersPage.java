package AutomationProjects.ECommerseAutomationFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationProjects.ECommerseAutomationFramework.abstractComponenents.AbstractComponent;

public class OrdersPage extends AbstractComponent
{
	WebDriver driver;
	public OrdersPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='ng-star-inserted']/td[2]")
	List<WebElement> orderNameList;
	
	public boolean chkIfItemPresentOnOrdersPage(String itemName)
	{
		return orderNameList.stream().anyMatch(e->e.getText().equalsIgnoreCase(itemName));
	}
}
