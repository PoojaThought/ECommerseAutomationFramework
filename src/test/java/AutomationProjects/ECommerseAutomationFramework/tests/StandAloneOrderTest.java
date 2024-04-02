package AutomationProjects.ECommerseAutomationFramework.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneOrderTest {

	public static void main(String[] args) 
	{
		String productName = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		driver.findElement(By.id("userEmail")).sendKeys("prd.pro.tester@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@1234");
		driver.findElement(By.id("login")).click();
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		List<WebElement> itemEleList = driver.findElements(By.cssSelector(".card-body h5 b"));
		WebElement desiredItemEle = itemEleList.stream().filter(e->e.getText().contains(productName)).findFirst().orElse(null);
		desiredItemEle.findElement(By.xpath("parent::h5/following-sibling::button[2]")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink *= 'cart']")).click();
		List<WebElement> cartItemsEle = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean presentCart = cartItemsEle.stream().anyMatch(e->e.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(presentCart);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions a = new Actions(driver);
		//a.moveToElement(driver.findElement(By.cssSelector("[placeholder='Select Country']"))).click().sendKeys("ind").build().perform();
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"ind").build().perform();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:nth-child(3")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMessage =  driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
		driver.close();
	}
	
	
}
