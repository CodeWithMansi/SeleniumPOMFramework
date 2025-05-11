package com.crm.qa.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase{
	
	
	@FindBy(xpath = "//span[contains(text(),'Mansi Sharma')]")
	WebElement userNameLabel;
	
	
	@FindBy(xpath = "//a[@href='/contacts']")
	WebElement contactsLink;

	
	
	@FindBy(xpath = "//span[text()='Deals']")
	WebElement dealsLink;
	
	
	@FindBy(xpath = "//span[text()='Tasks']")
	WebElement tasksLink;
	
	@FindBy(xpath = "//button[@class='ui linkedin button' and contains(text(),'Create')]")
	WebElement createNewContactButton;
	

	//Initializing the HomePage Objects
		public HomePage() {
			
			PageFactory.initElements(driver, this);
			
		}

	public String verifyHomePageTitle() {
		return driver.getTitle();	
		
	}
	
	public boolean verifyCorrectUsername() {
		return userNameLabel.isDisplayed();
	}
	
	
	
	public ContactsPage clickOnContactsLink() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // Wait until the contacts link is visible
	    wait.until(ExpectedConditions.visibilityOf(contactsLink));

	    // Scroll and click using JavaScript
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView(true);", contactsLink);
	    js.executeScript("arguments[0].click();", contactsLink);

	    return new ContactsPage();
	}
	
	
	public DealsPage clickOnDealsLink() {
		dealsLink.click();
		return new DealsPage();
	}
	
	public TasksPage clickOnTasksLink() {
		tasksLink.click();
		return new TasksPage();
	}
	
	public void clickOnCreateNewContactLink() {
		
		contactsLink.click();
		createNewContactButton.click();
		
		
		}
	
	
	
	
	
	
	

}
