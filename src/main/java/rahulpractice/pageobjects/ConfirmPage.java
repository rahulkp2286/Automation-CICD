package rahulpractice.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulpractice.AbstractComponents.AbstractComponents;

public class ConfirmPage extends AbstractComponents{

WebDriver driver;
	
	public ConfirmPage(WebDriver driver)
	{	
		super(driver);
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//	String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	
	@FindBy(css=".hero-primary")
	WebElement confirmOrderMessage;
	
	public String getConfirmationMessage() //10th step
	{
		return confirmOrderMessage.getText();
	}
	
	


	
	
}
