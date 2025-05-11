package com.crm.qa.testcases;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    ContactsPage contactsPage;
    String sheetName="Contacts";

    // Constructor
    public ContactsPageTest() {
        super();
    }

    // Before each test
    @BeforeMethod
    public void setUp() {
        initialization();
        loginPage = new LoginPage();
        contactsPage = new ContactsPage();
        homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
        homePage.clickOnContactsLink();
    }

    // Test 1: Verify Contacts Page Label
   @Test(priority = 1)
    public void verifyContactsPageLabelTest() {
        Assert.assertTrue(contactsPage.verifyContactsPageLabel(), "Contacts label is missing!");
    }

    // Test 2: Select a single contact
   @Test(priority = 2)
    public void selectSingleContactTest() {
        contactsPage.selectContactByName("Aarav Something");
    }

    // Test 3: Select multiple contacts
    @Test(priority = 3)
    public void selectMultipleContactsTest() {
        contactsPage.selectContactByName("Nyka Mehta");
        contactsPage.selectContactByName("Justin Rule"); 
    }
    
    
    @DataProvider 
    public Object[][] getTestData() throws InvalidFormatException {
    	Object data [][]=TestUtil.getTestData(sheetName);
    	return data;
    }
    
  
    @Test(priority = 4, dataProvider = "getTestData")
    public void createNewContactTest(
        String firstName,
        String lastName,
        String categoryName,
        String statusName,
        String position,
        String department,
        String socialChannel,
        String identifier
    ) {
        contactsPage.clickOnCreateButton();

        contactsPage.createNewContact(
            firstName,
            lastName,
            categoryName,
            statusName,
            position,
            department,
            socialChannel,
            identifier
        );

        Assert.assertTrue(
            contactsPage.verifyNewContactCreated(firstName + " " + lastName),
            "New Contact creation failed!"
        );
    }




    // After each test
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
