package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	 WebDriver driver;

	public CartPage(WebDriver driver)
	{  
		super(driver);
		//Initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
    @FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
    
    @FindBy(css=" .totalRow button")
    WebElement CheckoutEle;
    
    public Boolean VerifyProductDisplay(String productName)
    {
    	Boolean match = cartProducts.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(productName));
		return match;
    }
    
    public CheckoutPage goToCheckoutPage()
    {
    	CheckoutEle.click();
    	return new CheckoutPage(driver);
    }

}
