package rahulshettyacademy.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImplementation extends BaseTest {
	
	public LandingPage landingpage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationpage;
	@Given("I landed on Ecommerce Page.")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingpage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username,String password)
	{
		 productCatalogue = landingPage.loginApplication(username,password);
	}
	
	@When("I add product {string} to cart.")
	public void I_add_product_to_cart(String ProductName) throws InterruptedException
	{
		List<WebElement> products =productCatalogue.getProductList();
		productCatalogue.addProductToCart(ProductName);
	}
	

@When("Checkout {string} and Submit the order.")
public void checkout_and_submit_the_order(String ProductName) {
CartPage cartpage=productCatalogue.goToCartPage();
		
		Boolean match = cartpage.VerifyProductDisplay(ProductName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartpage.goToCheckoutPage();
		checkoutPage.selectCountry("India");
		 confirmationpage = checkoutPage.submitOrder();
	}
	
@Then("{string} message is displayed on ConfirmationPage.")
public void message_is_displayed_on_confirmation_page(String string) {
	
		String confirmMessage =confirmationpage.getconfirmationMessage(); 
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	  @Then("{string} message is displayed.")
	    public void something_message_is_displayed(String string) throws Throwable {
		  Assert.assertEquals("Login Successfully", landingPage.getErrorMessage());
		  driver.close();
	    }

}
