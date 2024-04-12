package rahulpractice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahulpractice.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{

	WebDriver driver;
	public CartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	/*
	List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3")); 
	Boolean match = cartProducts.stream().anyMatch(cartProd-> cartProd.getText().equals(productName));
	Assert.assertTrue(match);
	driver.findElement(By.cssSelector(".totalRow button")).click();
	*
	*/
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	
	
	public Boolean verifyProductDisplay(String productName) //6th step
	{
		Boolean match = cartProducts.stream().anyMatch(cartProd-> cartProd.getText().equals(productName));
		return match;
	}

	public CheckOutPage goToCheckOutProduct() //7th step
	{
		checkOutEle.click();
		CheckOutPage checkOutPage = new CheckOutPage(driver); // **create here object of next page 
		return checkOutPage;
		
	}

}
