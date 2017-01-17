package com.softserve.edu.oms.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static com.softserve.edu.oms.locators.AbstractBasePageLocators.*;


/**
 * This abstract class represents common elements and functionality for
 * every page of the project
 */
public abstract class AbstractBasePage {

    protected WebDriver driver;
    public static final Logger logger = LoggerFactory.getLogger(AbstractBasePage.class);

    AbstractBasePage(WebDriver driver) {
        this.driver = driver;

        //waiting for the last elem on the page to load
        //thus ensuring the whole page is ready to work with
       waitForLoad();
    }

    // get Data

    public WebElement getLogoutButton() {
        return driver.findElement(LOGOUT_BUTTON.by);
    }

    public WebElement getOmsLabel() {
        return driver.findElement(OMS_LABEL.by);
    }

    public WebElement getSimpleSlimGeniusLabel() {
        return driver.findElement(SIMPLE_SLIM_GENIUS_LABEL.by);
    }

    public WebElement getInspiredByGoogleLink() {
        return driver.findElement(INSPIRED_BY_GOOGLE_LINK.by);
    }

    public WebElement getUserInfoTab() {
        return driver.findElement(USER_INFO_TAB.by);
    }

    // Functional

    public String getOmsLabelText() {
        return getOmsLabel().getText().trim();
    }

    public String getSimpleSlimGeniusLabelText() {
        return getSimpleSlimGeniusLabel().getText().trim();
    }

    public String getInspiredByGoogleLinkText() {
        return getInspiredByGoogleLink().getText().trim();
    }

    public String getUserInfoTabText() {
        return getUserInfoTab().getText().trim();
    }


    // set Data

    public void clickLogoutButton() {
        getLogoutButton().click();
        logger.info("Click action performed on Logout button");
    }

    public void clickInspiredByGoogleLink() {
        getInspiredByGoogleLink().click();
    }

    @Step("Just a click on User Info Tab")
    public void clickUserInfoTab() {
        getUserInfoTab().click();
    }

    /**
     * Method that waits for alert for 5 seconds before accepting it.
     * Prevents scenario caused by alert popping up with a lag
     * thus triggering default accept to throw NoAlertPresentException
     */
    public AbstractBasePage acceptAlert() {
        try {
            //Wait 5 seconds till alert is present
            WebDriverWait wait = new WebDriverWait(driver, 5);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            //Accepting alert.
            alert.accept();

            logger.info("Accepted the alert successfully");
        } catch (Throwable e) {
            logger.error("Error {} came while waiting for the alert popup", e.getMessage());
        }
        return this;
    }

    // Business Logic

    public HomePage gotoUserInfoTab() {
        clickUserInfoTab();
        logger.info("Click action performed on User Info tab");
        return new HomePage(driver);
    }

    public LoginPage logout() {
        clickLogoutButton();
        return new LoginPage(driver);
    }

    //Explicit wait methods

    /**
     * Main explicit wait method that ensures that last element which is common for
     * every page of the project is present on the page and reference to it is valid.
     * Used mainly in constructors and methods that return self-reference to guarantee
     * that page loads before next method uses its elements
     */
    protected void waitForLoad() {
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, 5)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        wait.until((ExpectedCondition<Boolean>) webDriver -> {
            WebElement element = webDriver.findElement(INSPIRED_BY_GOOGLE_LINK.by);
            return element != null && element.isDisplayed();
        });

    }

    /**
     * This method is used to wait for elements to become visible.
     * Used in get data methods in corresponding page object class
     */
    protected WebElement waitForElement (final By by){
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, 5)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * This method is used to wait for elements, usually input errors,
     * to disappear when there is a precondition of valid data input.
     */
    protected boolean waitForElementToDisappear(final By by) {
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, 5)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

}