package rahulpractice.tests;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
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
import rahulpractice.TestComponents.BaseTest;
import rahulpractice.pageobjects.CartPage;
import rahulpractice.pageobjects.CheckOutPage;
import rahulpractice.pageobjects.ConfirmPage;
import rahulpractice.pageobjects.LandingPage;
import rahulpractice.pageobjects.OrderPage;
import rahulpractice.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider="getData",groups= {"Purchase"})
	//public void submitOrder(String email, String password, String productNmae) throws IOException, InterruptedException 
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		// LandingPage landingPage = new LandingPage(driver);//** refer BaseTest
		// LandingPage landingPage = launchApplication(); // use @beforeMethod on
		// Launch app method
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));

		// ProductCatalogue productCatalogue = new ProductCatalogue(driver); //** refer
		// landing page
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		// Thread.sleep(2000);

		// CartPage cartPage = new CartPage(driver); //** refer Abstract Component page
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);

		// CheckOutPage checkOutPage = new CheckOutPage(driver); //** refer Cart page
		CheckOutPage checkOutPage = cartPage.goToCheckOutProduct();
		checkOutPage.selectCountry("India");

		// ConfirmPage confirmPage = new ConfirmPage(driver); //** refer Checkout page
		ConfirmPage confirmPage = checkOutPage.submitOrder();

		String confirmMessage = confirmPage.getConfirmationMessage(); // ** refer confirm page
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		// driver.close(); //use @AfterMethod in BaseTest
		tearDown();
	}

	// to verify ADIDAS ORIGINAL is displaying on Orderspage
	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		// "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue = landingPage.loginApplication("Framework@gmail.com", "Citi002$");
		OrderPage ordersPage = productCatalogue.goToOrderPage(); // Abstract component to get the method
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName)); // order page

	}
	
	@DataProvider
	public Object[][] getData() throws IOException{
	List<HashMap<String, String>> data = getJsonDataToHashMap(System.getProperty("user.dir") + 
			"\\src\\test\\java\\rahulpractice\\data\\PurchaseOrder.json");
	return new Object[][] {{data.get(0) },{data.get(1)}};
	}
	
//	@DataProvider
//	public Object[][] getData() throws IOException{
//		HashMap<String,String> map = new HashMap<String,String> ();
//		map.put("email", "Framework@gmail.com");
//		map.put("password", "Citi002$");
//		map.put("product", "ADIDAS ORIGINAL");
//		
//		HashMap<String,String> map1 = new HashMap<String,String> ();
//		map1.put("email", "abc02@gmail.com");
//		map1.put("password", "Citi002$");
//		map1.put("product", "ZARA COAT 3");
//		return new Object[][] {map},{map1}};
//}
		
		

//	@DataProvider
//	public Object[][] getData(){
//		
//		
//		return new Object[][] {{"Framework@gmail.com","Citi002$" "ADIDAS ORIGINAL"},{"abc02@gmail.com",Citi002$", "ZARA COAT 3"}};
//		
//	}
}
