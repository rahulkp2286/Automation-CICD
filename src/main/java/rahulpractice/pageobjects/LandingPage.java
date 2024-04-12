package rahulpractice.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulpractice.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		
		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
/*
	driver.get("https://rahulshettyacademy.com/client");
	driver.findElement(By.id("userEmail")).sendKeys("Framework@gmail.com");
	driver.findElement(By.id("userPassword")).sendKeys("Citi002$");
	driver.findElement(By.name("login")).click();
	 * 
	 */

	// PageFactory

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement PasswordEle;

	@FindBy(name = "login")
	WebElement Submit;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	public void gotoUrl() { //1st url step
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() { //1.1 step to check error
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}

	public ProductCatalogue loginApplication(String email, String password) { //2nd login step
		userEmail.sendKeys(email);
		PasswordEle.sendKeys(password);
		Submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver); // **create here object of next page 
		return productCatalogue; //return object 

	}	
	
	
}
