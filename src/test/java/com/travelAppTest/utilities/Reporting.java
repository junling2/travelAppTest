package com.travelAppTest.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter{
	public ExtentReports extentReports;
	public ExtentSparkReporter sparkReporter;
	public ExtentTest eTest;
	
	
	public void onStart(ITestContext iContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String reportName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + reportName);
		try {
			sparkReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		extentReports = new ExtentReports();
		
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("Hostname", "localhost");
		extentReports.setSystemInfo("Environment", "QA");
		extentReports.setSystemInfo("User", "admin");
		
		sparkReporter.config().setDocumentTitle("TravelApp Test Selenium");
		sparkReporter.config().setReportName("Functional Test Report");
		sparkReporter.config().setTheme(Theme.DARK);
	}
	
	public void onTestSuccess(ITestResult result) {
		eTest = extentReports.createTest(result.getName());
		eTest.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
	}
	
	public void onTestFailure(ITestResult result) {
		eTest = extentReports.createTest(result.getName());
		eTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
		
		String screenshotPathString = System.getProperty("user.dir") + "\\Screenshots\\" + result.getName() + ".png";		
		File file = new File(screenshotPathString);		
		if (file.exists()) {
			eTest.addScreenCaptureFromPath(screenshotPathString);
		}				
	}
	
	public void onTestSkipped(ITestResult result) {
		eTest = extentReports.createTest(result.getName());
		eTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext) {
		extentReports.flush();
	}
}
