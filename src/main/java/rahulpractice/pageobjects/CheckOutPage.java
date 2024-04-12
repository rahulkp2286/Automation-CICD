package rahulpractice.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulpractice.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver)
	{	
		super(driver);
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	/*
	 * 	
	 *  Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
		
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click(); // xpath : (//button[contains(@class,'ta-item')])[2]
		driver.findElement(By.cssSelector(".action__submit")).click();
	 */

	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By autoSuggestCountryResults = By.cssSelector(".ta-item"); //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
	
	public void selectCountry(String countryName) //8th step
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(autoSuggestCountryResults);
		selectCountry.click();
		
	}
	
	public ConfirmPage submitOrder() //9th Step
	{
		submit.click();
		ConfirmPage confirmPage = new ConfirmPage(driver); // **create here object of next page 
		return confirmPage;
	}
	

}
