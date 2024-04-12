package rahulpractice.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import rahulpractice.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver; 
	
	public ProductCatalogue(WebDriver driver)
	{
		
		super(driver);
		//initialization 
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}	

	
	/*
	 * 	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));  //first step  take all products in list 
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst().orElse(null); // from list of products filter out single product and return in to variable prod as webelement, which we want  
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); // now from returns  single prod click add to cart 

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	 */
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() // 3rd Step
	{
		waitForElementToAppear(productsBy); //from abstract class to reuse wait 
		return products; //return list of products
		
	}
	
	public WebElement getProductByName(String productName) //4th Step
	{
		WebElement prod = getProductList().stream() //3rd step method .stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst().orElse(null);
		return prod; // return filtered product 
	}
	
	
	public void addProductToCart(String productName) throws InterruptedException  //5th Step 
	{
		WebElement prod = getProductByName(productName); //4th step method save it in one variable
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage); //from abstract class to reuse wait 
		waitForElementToDisappear(spinner); //from abstract class to reuse wait 
		
	
		
	}
	
	
}
