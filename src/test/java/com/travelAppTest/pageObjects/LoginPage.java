package com.travelAppTest.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name="email")
    @CacheLookup
    WebElement txtUserName;

    @FindBy(name="password")
    @CacheLookup
    WebElement txtPassword;

    @FindBy(xpath = "//button[@class='btn btn-default btn-lg btn-block effect ladda-button waves-effect']")
    @CacheLookup
    WebElement btnLogin;

    public void setUserName(String username) {
        txtUserName.sendKeys(username);
    }

    public void setPassword(String pwd) {
        txtPassword.sendKeys(pwd);
    }

    public void clickSubmit() {
        btnLogin.click();
    }
}
