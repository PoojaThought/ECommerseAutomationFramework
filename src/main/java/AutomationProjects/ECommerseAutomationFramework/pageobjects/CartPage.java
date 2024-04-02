package AutomationProjects.ECommerseAutomationFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationProjects.ECommerseAutomationFramework.abstractComponenents.AbstractComponent;

public class CartPage extends AbstractComponent
{
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartItemsEle;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutBtn;
	
	public boolean checkDesiredIteminCart(String productName)
	{
		return cartItemsEle.stream().anyMatch(e->e.getText().equalsIgnoreCase(productName));
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutBtn.click();
		CheckoutPage ck = new CheckoutPage(driver);
		return ck;
		
	}
}
