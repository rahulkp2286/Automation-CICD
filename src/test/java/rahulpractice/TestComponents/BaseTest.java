package rahulpractice.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulpractice.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		// FileInputStream fis = new FileInputStream(
		// "C:\\Users\\RahPoo\\OneDrive\\Selenium
		// Practice\\SeleniumFramworkDesignRP\\src\\main\\java\\rahulpractice\\resources\\GlobalData.propertie");
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\rahulpractice\\resources\\GlobalData.properties");
		prop.load(fis);
		
//		String browserName = prop.getProperty("browser");
//		
//		if (browserName.equalsIgnoreCase("chrome")) {
//
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
		
		//through mvn cmd , before that check config from here "https://www.qamadness.com/knowledge-base/how-to-install-maven-and-configure-environment-variables/"
		//mvn test -PRegression -Dbrowser=firefox
				//mvn test -PRegression (P = stands for <Profile> -> <id> -> xml file name "Regression") 
				//         -Dbrowser=firefox (D = stands for Maven Parameter (System.getProperty("browser")
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser"); //java tunner operator 
		
		//String browserName =  from mvn if firefox and not null  -> System.getProperty("browser")!=null ? System.getProperty("browser") 
		// if null -> :prop.getProperty("browser"); - from global properties 
		
		
		
		if(browserName.contains("chrome")) {
			//System.setProperty("webdriver.chrome.driver",
				//	"C:\\Users\\RahPoo\\OneDrive\\Sel Practice\\chromedriver_win32\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions(); //using ChromeOptions - set any config with help of options object
			WebDriverManager.chromedriver().setup();
			
			if(browserName.contains("headless"))
			{
			options.addArguments("headless");
			}		
			driver = new ChromeDriver(options); 
			driver.manage().window().setSize(new Dimension(1440,900));//full screen
		}
		
//		if (browserName.equalsIgnoreCase("chrome")) {
//
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
//
//
//		} 
			else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\RahPoo\\OneDrive\\Selenium Practice\\SeleniumFramworkDesignRP\\Driver\\geckodriver.exe");
			//WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.gotoUrl();
		return landingPage;

	}

	public List<HashMap<String, String>> getJsonDataToHashMap(String filePath) throws IOException {
		// read json to string

		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// convert String to hashmap - need dependency jackson bind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

		// {{map },{map1}}
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();

	}
}
