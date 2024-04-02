package AutomationProjects.ECommerseAutomationFramework.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationProjects.ECommerseAutomationFramework.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest
{

	@Test(groups= {"ErrorValidations"})
	public void validateInvalidLoginErrMsg()
	{
		lp.logintoApplication("prd.pro.tester@gmail.com", "asd");
		Assert.assertEquals(lp.getInvalidLoginErrMsg(), "Incorrect email  password.");
	}
}
