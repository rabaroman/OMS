package com.softserve.edu.oms.locators;

import org.openqa.selenium.By;

/**
 * Enum contains locators for elements on {@link com.softserve.edu.oms.pages.AdministrationPage}.
 */
public enum AdministrationPageLocators {

    DELETE_LINK(By.linkText("Delete")),
    GO_TO_CREATE_NEW_USER_PAGE(By.cssSelector("a[href=\"addUser.htm\"]")),
    EDIT_USER_LINK(By.linkText("Edit")),
    CREATE_REPORT(By.cssSelector("#list h5 a"));


    public final By by;
    AdministrationPageLocators(final By by){
        this.by = by;
    }
}
