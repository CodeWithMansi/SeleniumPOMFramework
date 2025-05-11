package com.crm.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {
	
	// PageFactory - Object Repository
	@FindBy(name="email")  
	WebElement emailField;
	
	@FindBy(name="password")
	WebElement passwordField;
	
	@FindBy(xpath="//div[text()='Login']") 
	WebElement loginButton;
	
	// Initializing the Page Objects
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	// Actions
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public HomePage login(String email, String pass) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    wait.until(ExpectedConditions.visibilityOf(emailField));
	    emailField.sendKeys(email);
	    passwordField.sendKeys(pass);

	    wait.until(ExpectedConditions.elementToBeClickable(loginButton));
	    loginButton.click();

	    return new HomePage();
	}
}
