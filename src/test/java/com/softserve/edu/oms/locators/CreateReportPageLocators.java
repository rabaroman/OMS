package com.softserve.edu.oms.locators;

import org.openqa.selenium.By;

/**
 * Enum contains locators for elements on {@link com.softserve.edu.oms.pages.CreateReportPage}.
 */
public enum CreateReportPageLocators {
    
    ADMINISTRATION_LINK(By.cssSelector("#nav .cur a")),
    SAVE_REPORT_LINK(By.cssSelector("#list>a"));

    public final By by;

    CreateReportPageLocators(final By by){
        this.by = by;
    }

}
