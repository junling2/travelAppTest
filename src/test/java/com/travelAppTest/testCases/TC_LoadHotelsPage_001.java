package com.travelAppTest.testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.travelAppTest.pageObjects.Dashboard;
import com.travelAppTest.pageObjects.LoginPage;

public class TC_LoadHotelsPage_001 extends TCBaseClass {
	private String EXPECTED_HOTELS_TITLE = "Search Hotels - PHPTRAVELS";
	
	@Test
	public void loadHotelsPageTest() throws IOException {
		// Login 
		driver.get(baseURL + "/login");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        LoginPage page = new LoginPage(driver);
        page.setUserName(username);
        page.setPassword(password);
        page.clickSubmit();
        
        // Goto Hotels Page
        Dashboard dashboard = new Dashboard(driver);
        dashboard.clickHotels();
        
        // Check Hotels Page loaded properly
        WebDriverWait waitLogin = new WebDriverWait(driver, 10);
		waitLogin.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title")));
		if (!driver.getTitle().equals(EXPECTED_HOTELS_TITLE)) {
			logger.info("Load Hotels Page Failed");
        	screenShot(driver, "bookHotelTest");
        	Assert.assertTrue(false);      
		} else {
			logger.info("Hotel Search Page loaded successfully");
			Assert.assertTrue(true);
		}
	}
}
