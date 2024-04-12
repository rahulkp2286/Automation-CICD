package rahulpractice.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulpractice.AbstractComponents.AbstractComponents;


public class OrderPage extends AbstractComponents {
	WebDriver driver;


	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;

	public Boolean verifyOrderDisplay(String productName) { //11th step
		Boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

	}


}