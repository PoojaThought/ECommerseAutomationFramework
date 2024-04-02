package AutomationProjects.ECommerseAutomationFramework.abstractComponenents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationProjects.ECommerseAutomationFramework.pageobjects.CartPage;
import AutomationProjects.ECommerseAutomationFramework.pageobjects.OrdersPage;

public class AbstractComponent 
{
	WebDriver driver;	
	public AbstractComponent(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink *= 'cart']")
	WebElement cartBtn;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersBtn;
	
	public void waitForElementToAppear(By byEle)
	{
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(byEle));
	}
	
	public void waitForElementToAppear(WebElement e)
	{
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(e));
	}	
	
	public void waitForElementToDisappear(WebElement ele)
	{
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.invisibilityOf(ele));
	}	
	
	public CartPage goToCart()
	{
		cartBtn.click();
		CartPage cp = new CartPage(driver);
		return cp;
	}
	
	public OrdersPage goToOrdersPage()
	{
		ordersBtn.click();
		OrdersPage op = new OrdersPage(driver);
		return op;
	}

}
