package AutomationProjects.ECommerseAutomationFramework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AutomationProjects.ECommerseAutomationFramework.abstractComponenents.AbstractComponent;

public class ProductCataloguePage extends AbstractComponent
{
	WebDriver driver;
	public ProductCataloguePage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	By productsBy = By.cssSelector(".mb-3");
	
	By toastContainerBy = By.cssSelector("#toast-container");
			
	@FindBy(css=".card-body h5 b")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement animation;
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	private WebElement getDesiredProduct(String productName)
	{
		return products.stream().filter(e->e.getText().contains(productName)).findFirst().orElse(null);
	}
	
	public void addItemintoCart(String productName)
	{
		WebElement drsiredProd = getDesiredProduct(productName);
		drsiredProd.findElement(By.xpath("parent::h5/following-sibling::button[2]")).click();
		waitForElementToAppear(toastContainerBy);
		waitForElementToDisappear(animation);
	}
	

	
}
