package AutomationProjects.ECommerseAutomationFramework.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomationProjects.ECommerseAutomationFramework.TestComponents.BaseTest;
import AutomationProjects.ECommerseAutomationFramework.pageobjects.CartPage;
import AutomationProjects.ECommerseAutomationFramework.pageobjects.CheckoutPage;
import AutomationProjects.ECommerseAutomationFramework.pageobjects.ConfirmationPage;
import AutomationProjects.ECommerseAutomationFramework.pageobjects.LandingPage;
import AutomationProjects.ECommerseAutomationFramework.pageobjects.OrdersPage;
import AutomationProjects.ECommerseAutomationFramework.pageobjects.ProductCataloguePage;

public class SubmitOrderTest extends BaseTest
{
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"purchase"})
	public void SubmitOrdet(HashMap<String,String> input)
	{		
		ProductCataloguePage pc = lp.logintoApplication(input.get("email"), input.get("password"));
		List<WebElement> itemEleList = pc.getProductList();
		pc.addItemintoCart(input.get("product"));
		CartPage cp = pc.goToCart();
		Assert.assertTrue(cp.checkDesiredIteminCart(input.get("product")));
		CheckoutPage ck = cp.goToCheckout();
		ck.selectCountry("ind");
		ConfirmationPage cm = ck.submitOrder();
		String confirmMessage = cm.getConfirmationMessage();
		Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");

	}
	
	@Test(dependsOnMethods= {"SubmitOrdet"})
	public void chkItemOrderedIsPresentInOrderHistory()
	{
		ProductCataloguePage pc = lp.logintoApplication("prd.pro.tester@gmail.com", "Test@1234");
		OrdersPage op = pc.goToOrdersPage();
		Assert.assertTrue(op.chkIfItemPresentOnOrdersPage(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = getJSONDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\AutomationProjects\\ECommerseAutomationFramework\\Data\\PurchaseData.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	/*
	 * HashMap<String,String> map = new HashMap<String,String>(); map.put("Email",
	 * "prd.pro.tester@gmail.com"); map.put("password", "Test@1234");
	 * map.put("product", "ZARA COAT 3");
	 * 
	 * HashMap<String,String> map1 = new HashMap<String,String>(); map1.put("Email",
	 * "anshika@gmail.com"); map1.put("password", "Iamking@000");
	 * map1.put("product", "ADIDAS ORIGINAL");
	 * 
	 * return new Object[][] {{map},{map1}};
	 */	

	
	/*@DataProvider
	//public Object[][] getData()
	//{
	//	return new Object[][] {{"prd.pro.tester@gmail.com","Test@1234","ZARA COAT 3"},{"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
	}*/
	
}
