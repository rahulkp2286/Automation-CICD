package rahulpractice.resources;

import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
//ExtentReports extent;
	
	//@BeforeTest
	public static ExtentReports getReportObject() {
		//ExtentReport - create and consolidate all your test execution 
		// ExtentSparkReporter - responsible for creating  configuration of  report
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Autgomation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Rahul");
		return extent;
		
	}

}
