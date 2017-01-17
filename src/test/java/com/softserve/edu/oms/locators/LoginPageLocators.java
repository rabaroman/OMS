package com.softserve.edu.oms.locators;

import org.openqa.selenium.By;

/**
 * Enum contains locators for elements on {@link com.softserve.edu.oms.pages.LoginPage}.
 */
public enum LoginPageLocators {

    ATTRIBUTE(By.name("name")),
    LOGIN_INPUT_FIELD(By.name("j_username") ),
    PASSWORD_INPUT_FIELD(By.name("j_password")),
    LOGIN_BUTTON(By.name("submit")),
    RESET_BUTTON(By.name("reset")),
    REMEMBER_ME_CHECKBOX(By.name("_spring_security_remember_me")),
    BAD_CREDENTIALS_ERROR_MESSAGE(By.cssSelector("#edit>fieldset>font"));


    public final By by;

    LoginPageLocators(final By by){
        this.by = by;
    }

}
