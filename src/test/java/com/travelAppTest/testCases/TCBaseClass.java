package com.travelAppTest.testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.travelAppTest.utilities.ReadConfig;

public class TCBaseClass {
	public ReadConfig rc = new ReadConfig();
    public String baseURL = rc.getApplicationURL();
    public String username = rc.getUserName();
    public String password = rc.getPassword();
    public static WebDriver driver;
    public static Logger logger;
    
    @Parameters("browser")
    @BeforeClass
    public void setup(String browser) {
        logger = Logger.getLogger(TCBaseClass.class);
        PropertyConfigurator.configure("log4j.properties");
        
        if (browser.equals("chrome")) {
        	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
        	driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
        	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe");
        	driver = new FirefoxDriver();
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
    
    public void screenShot(WebDriver driver, String name) throws IOException {
    	TakesScreenshot ts = (TakesScreenshot) driver;
    	File source = ts.getScreenshotAs(OutputType.FILE);
    	File target = new File(System.getProperty("user.dir") + "/Screenshots/" + name + ".png");
    	FileUtils.copyFile(source, target);
    	logger.info("Screenshot Taken");
    }
}
