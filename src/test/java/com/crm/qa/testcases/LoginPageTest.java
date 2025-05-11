package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization(); // launches browser + url from config
		loginPage = new LoginPage();
	}
	
	@Test(priority=1)
	public void loginPageTitleTest() {
		String title = loginPage.validateLoginPageTitle();
		System.out.println("Login Page Title: " + title);
		Assert.assertEquals(title, "Cogmento CRM"); 
	}
	
	@Test(priority=2)
	public void loginTest() {
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		String homePageTitle = driver.getTitle();
		System.out.println("Home Page Title After Login: " + homePageTitle);
		Assert.assertTrue(homePageTitle.contains("Cogmento"), "Home page title does not contain 'Cogmento'");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
