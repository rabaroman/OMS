package com.softserve.edu.oms.locators;


import org.openqa.selenium.By;

/**
 * Enum contains locators for elements on
 * {@link com.softserve.edu.oms.pages.HomePage}
 * {@link com.softserve.edu.oms.pages.AdminHomePage}
 * {@link com.softserve.edu.oms.pages.CustomerHomePage}
 * {@link com.softserve.edu.oms.pages.MerchandiserHomePage}
 * {@link com.softserve.edu.oms.pages.SupervisorHomePage}
 */
public enum UserHomePageLocators {

    FIRST_NAME_LABEL(By.cssSelector("fieldset tr:nth-child(1) td:last-child")),
    LAST_NAME_LABEL(By.cssSelector("fieldset tr:nth-child(2) td:last-child")),
    ROLE_LABEL(By.cssSelector("fieldset tr:nth-child(4) td:last-child")),
    ENG_LANG_LINK(By.xpath("//a[contains(@href, 'en_US')]")),
    UKR_LANG_LINK(By.xpath("//a[contains(@href, 'uk_UA')]")),
    ADMINISTRATION_TAB(By.cssSelector("*[href=\"/OMS/users.htm\"]")),
    ORDERING_TAB(By.xpath(".//a[contains(@href, 'order.htm')]")),
    ITEM_MANAGEMENT_TAB(By.xpath("//a[contains(@href, 'itemManagement.htm')]"));

    public final By by;
    UserHomePageLocators(final By by){
        this.by = by;
    }
}
