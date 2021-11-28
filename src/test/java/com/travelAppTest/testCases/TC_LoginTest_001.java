package com.travelAppTest.testCases;

import java.util.concurrent.TimeUnit;
import com.travelAppTest.pageObjects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_LoginTest_001 extends TCBaseClass {
	private String EXPECTED_TITLE = "Dashboard - PHPTRAVELS";
	
	@Test
    public void loginTest() {
		driver.get(baseURL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("URL is opened");

        LoginPage page = new LoginPage(driver);
        page.setUserName(username);
        logger.info("Username is entered");
        page.setPassword(password);
        logger.info("Password is entered");
        page.clickSubmit();
        logger.info("Submit button pressed");
        
        WebDriverWait waitLogin = new WebDriverWait(driver, 30);
		waitLogin.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title")));
        logger.info("Dashboard is loaded");
        Assert.assertEquals(driver.getTitle(), EXPECTED_TITLE);      
    }
}
