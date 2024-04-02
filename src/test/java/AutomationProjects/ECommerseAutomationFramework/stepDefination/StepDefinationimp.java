package AutomationProjects.ECommerseAutomationFramework.stepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import AutomationProjects.ECommerseAutomationFramework.TestComponents.BaseTest;
import AutomationProjects.ECommerseAutomationFramework.pageobjects.CartPage;
import AutomationProjects.ECommerseAutomationFramework.pageobjects.CheckoutPage;
import AutomationProjects.ECommerseAutomationFramework.pageobjects.ConfirmationPage;
import AutomationProjects.ECommerseAutomationFramework.pageobjects.LandingPage;
import AutomationProjects.ECommerseAutomationFramework.pageobjects.ProductCataloguePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationimp extends BaseTest
{
	public LandingPage lp;
	public ProductCataloguePage pc;
	public ConfirmationPage cm;
	@Given("I landed on ECommerce Page")
	public void I_landed_on_ECommerce_Page() throws IOException
	{
		lp = LaunchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password)
	{
		pc = lp.logintoApplication(username, password);
	}

	@When("^I add product (.+) into Cart$")
	public void When_I_add_product_into_Cart(String prodName)
	{
		List<WebElement> itemEleList = pc.getProductList();
		pc.addItemintoCart(prodName);		
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void  Checkout_and_submit_the_order (String prodName)
	{
		CartPage cp = pc.goToCart();
		Assert.assertTrue(cp.checkDesiredIteminCart(prodName));
		CheckoutPage ck = cp.goToCheckout();
		ck.selectCountry("ind");
		cm = ck.submitOrder();		
				
	}
	
	@Then("{string} message is displayed on confirmation page") 
	public void message_is_displayed_on_confirmation_page(String string)
	{
		String confirmMessage = cm.getConfirmationMessage();
		Assert.assertEquals(confirmMessage, string);	
		driver.close();
	}
	
	@Then("{string} error message is displayed")
	public void Incorrect_email_password(String string)
	{
		Assert.assertEquals(lp.getInvalidLoginErrMsg(), string);
		driver.close();
	}
}