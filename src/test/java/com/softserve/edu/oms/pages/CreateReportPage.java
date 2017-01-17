package com.softserve.edu.oms.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static com.softserve.edu.oms.locators.CreateReportPageLocators.*;

/**
 * This page represents PageObject for Create Report Page
 */
public class CreateReportPage extends AbstractAdminReportPage {

    public CreateReportPage(WebDriver driver) {
        super(driver);
    }

    // get Data
    public WebElement getAdministrationLink() {
        return driver.findElement(ADMINISTRATION_LINK.by);
    }

    public WebElement getSaveReportLink() {
        return driver.findElement(SAVE_REPORT_LINK.by);
    }

    // Functional
    public String getAdministrationLinkText() {
        return getAdministrationLink().getText();
    }

    public String getSaveReportLinkText() {
        return getSaveReportLink().getText();
    }

    // set Data
    public void clickAdministrationLink() {
        getAdministrationLink().click();
    }

    public void clickSaveReportLink() {
        getSaveReportLink().click();
    }

    // Business Logic
    AdministrationPage goToAdministrationPage() {
        clickAdministrationLink();
        return new AdministrationPage(driver);
    }
}
