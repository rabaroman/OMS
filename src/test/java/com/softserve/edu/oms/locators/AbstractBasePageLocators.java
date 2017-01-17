package com.softserve.edu.oms.locators;


import com.softserve.edu.oms.pages.AbstractBasePage;
import org.openqa.selenium.By;

/**
 * Enum contains locators for elements on {@link AbstractBasePage}.
 */
public enum AbstractBasePageLocators {

    LOGOUT_BUTTON(By.cssSelector(".spec a")),
    OMS_LABEL(By.cssSelector("#logo h1")),
    SIMPLE_SLIM_GENIUS_LABEL(By.cssSelector("#logo h2")),
    INSPIRED_BY_GOOGLE_LINK(By.cssSelector("#footer a")),
    USER_INFO_TAB(By.cssSelector("*[href=\"/OMS/userInfo.htm\"]"));

    public final By by;
    AbstractBasePageLocators(final By by){
        this.by = by;
    }
}
