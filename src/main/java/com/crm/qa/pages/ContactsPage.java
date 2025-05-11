package com.crm.qa.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {

    @FindBy(xpath = "//span[@class='selectable ' and text()='Contacts']")
    WebElement contactsLabel;

    @FindBy(xpath = "//button[contains(text(),'Create')]")
    WebElement createButton;

    @FindBy(name = "first_name")
    WebElement firstName;

    @FindBy(name = "last_name")
    WebElement lastName;

    @FindBy(name = "category")
    WebElement categoryDropdown;

    @FindBy(name = "status")
    WebElement statusDropdown;

    @FindBy(name = "position")
    WebElement positionField;

    @FindBy(name = "department")
    WebElement departmentField;

    @FindBy(name = "channel_type")
    WebElement socialChannelDropdown;

    @FindBy(name = "identifier")
    WebElement identifierField;

    @FindBy(xpath = "//button[text()='Save']")
    WebElement saveButton;

    public ContactsPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean verifyContactsPageLabel() {
        return contactsLabel.isDisplayed();
    }

    public void clickOnCreateButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("first_name")));
    }

    public void selectContactByName(String contactName) {
        String xpathforCheckbox = "//a[text()='" + contactName + "']/ancestor::tr//td//label";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathforCheckbox)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
    }

    public void createNewContact(String fName, String lName, String categoryName, String statusName,
                                 String position, String department, String socialChannel,
                                 String identifier) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        firstName.sendKeys(fName);
        lastName.sendKeys(lName);

        categoryDropdown.click();
        ThreadSleep(500);
        WebElement categoryOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='" + categoryName + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", categoryOption);

        statusDropdown.click();
        ThreadSleep(500);
        WebElement statusOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option' and contains(.,'" + statusName + "')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", statusOption);

        positionField.sendKeys(position);
        departmentField.sendKeys(department);

        socialChannelDropdown.click();
        ThreadSleep(500);
        WebElement socialChannelOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option' and contains(.,'" + socialChannel + "')]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", socialChannelOption);

        identifierField.sendKeys(identifier);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
    }

    public boolean verifyNewContactCreated(String fullName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(
                    By.xpath("//span[@class='selectable ']"), "Create New Contact")));

            WebElement contactNameSpan = driver.findElement(By.xpath("//span[@class='selectable ']"));
            String displayedName = contactNameSpan.getText().trim();

            if (displayedName.equals(fullName)) {
                System.out.println("Contact [" + fullName + "] created successfully!");
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private void ThreadSleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
