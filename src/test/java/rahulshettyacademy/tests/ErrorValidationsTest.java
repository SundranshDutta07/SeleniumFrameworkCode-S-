package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
		
	    landingPage.loginApplication("nishchay.angra@gmail.com", "Nishhay@18");
	    Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	    
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
		String productName = "ZARA COAT 3";
		
	    ProductCatalogue productCatalogue=landingPage.loginApplication("nishchay.angra@gmail.com", "Nishchay@18");
		List<WebElement> products =productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartpage=productCatalogue.goToCartPage();
		
		Boolean match = cartpage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}
 
 
	}


