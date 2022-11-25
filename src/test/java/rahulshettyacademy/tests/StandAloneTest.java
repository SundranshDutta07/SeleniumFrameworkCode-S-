package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StandAloneTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void SubmitOrder(HashMap<String ,String> input) throws IOException, InterruptedException
	{
		
		
	    ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products =productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartpage=productCatalogue.goToCartPage();
		
		Boolean match = cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartpage.goToCheckoutPage();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationpage=checkoutPage.submitOrder();
		String confirmMessage =confirmationpage.getconfirmationMessage(); 
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void OrderHistoryTest()
	{  //To verify that ZaraCoar 3 is displayed in order page.
		ProductCatalogue productCatalogue=landingPage.loginApplication("nishchay.angra@gmail.com", "Nishchay@18");
		OrderPage ordersPage= productCatalogue.goToOrderPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	//Extent Reports
	@DataProvider
	public Object[][] getData() throws IOException
	{
	List<HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+
				"//src//test//java//rahulshettyacademy//Data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	@DataProvider
	public Object[][] getData1()
	{
		return new Object[][]  {{"nishchay.angra@gmail.com","Nishchay@18","ZARA COAT 3"},{"nishchayangra18@gmail.com","Nishchay@18","ADIDAS ORIGINAL"}};
	}
	
	//HashMap<String ,String> map = new HashMap<String ,String>();
	//map.put("email";"nishchay.angra@gmail.com" );
//map.put("password","Nishchay@18");
	//map.put("product","ZARA COAT 3");

	//HashMap<String ,String> map1 = new HashMap<String ,String>();
	//map1.put("email","nishchayangra18@gmail.com" );
	//map1.put("password","Nishchay@18");
	//map1.put("product","ADIDAS ORIGINAL");
 
	}


