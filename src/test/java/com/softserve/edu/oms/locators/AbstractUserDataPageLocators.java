package com.softserve.edu.oms.locators;

import com.softserve.edu.oms.pages.AbstractUserDataPage;
import org.openqa.selenium.By;

/**
 * Enum contains locators for elements on {@link AbstractUserDataPage}.
 */
public enum AbstractUserDataPageLocators {

    LOGIN_INPUT(By.id("login")),
    FIRST_NAME_INPUT(By.id("firstName")),
    LAST_NAME_INPUT(By.id("lastName")),
    PASSWORD_INPUT(By.id("password")),
    CONFIRM_PASSWORD_INPUT(By.id("confirmPassword")),
    EMAIL_INPUT(By.id("email")),
    REGION_SELECT(By.id("regionID")),
    ROLE_RADIO_BUTTON(By.name("roleID")),
    CREATE_BUTTON(By.cssSelector("input[value=\"Create\"]")),
    CANCEL_BUTTON(By.cssSelector("input[value=\"Cancel\"]")),
    ERROR_LOGIN(By.id("nameError")),
    ERROR_FIRST_NAME(By.id("firstNameError")),
    ERROR_LAST_NAME(By.id("lastNameError")),
    ERROR_PASSWORD(By.id("passwordError")),
    ERROR_CONFIRM_PASSWORD(By.id("confirmError")),
    ERROR_EMAIL(By.id("emailError"));

    public final By by;
    AbstractUserDataPageLocators(final By by){
        this.by = by;
    }
}
