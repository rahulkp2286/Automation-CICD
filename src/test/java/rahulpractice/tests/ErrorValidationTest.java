package rahulpractice.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulpractice.TestComponents.BaseTest;
import rahulpractice.TestComponents.Retry;
import rahulpractice.pageobjects.CartPage;
import rahulpractice.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {

		@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
		public void loginErrorValidation() throws IOException, InterruptedException {
			
			
	
		//LandingPage landingPage = new LandingPage(driver);//** refer BaseTest 
		//LandingPage landingPage = launchApplication(); // use @beforeMethod on luanchapp method 
		landingPage.loginApplication("Framework@gmail.com", "Citi3202$");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
		
		
		
		
	}
		@Test
		public void productErrorValidation() throws IOException, InterruptedException{
			String productName = "ADIDAS ORIGINAL";
			ProductCatalogue productCatalogue = landingPage.loginApplication("abc02@gmail.com", "Citi002$");		
			List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductToCart(productName);
			CartPage cartPage = productCatalogue.goToCartPage();
			Boolean match = cartPage.verifyProductDisplay("ADIDAS ORIGINALS");
			Assert.assertFalse(match);
		}

}
