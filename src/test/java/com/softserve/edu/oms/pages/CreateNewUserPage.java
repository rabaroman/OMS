package com.softserve.edu.oms.pages;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.enums.Region;
import com.softserve.edu.oms.enums.Role;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * This page represents PageObject for Create New User Page
 */
public class CreateNewUserPage extends AbstractUserDataPage {

    public CreateNewUserPage(WebDriver driver) {
        super(driver);
    }

    //set data
    @Step("setLoginInput")
    public CreateNewUserPage setLoginInput(String login) {
        getLoginInput().clear();
        getLoginInput().sendKeys(login);
        getFirstNameInput().sendKeys();
        return this;
    }

    @Step("setFirstNameInput")
    public CreateNewUserPage setFirstNameInput(String firstName) {
        super.setFirstNameInput(firstName);
        return this;
    }

    @Step("setLastNameInput")
    public CreateNewUserPage setLastNameInput(String lastName) {
        super.setLastNameInput(lastName);
        return this;
    }

    @Step("setPasswordInput")
    public CreateNewUserPage setPasswordInput(String password) {
        super.setPasswordInput(password);
        return this;
    }

    @Step("setConfirmPasswordInput")
    public CreateNewUserPage setConfirmPasswordInput(String confirmPassword) {
        super.setConfirmPasswordInput(confirmPassword);
        return this;
    }

    @Step("setEmailInput")
    public CreateNewUserPage setEmailInput(String email) {
        super.setEmailInput(email);
        return this;
    }

    @Step("Set Select Region")
    public CreateNewUserPage setSelectRegion(Region region) {
        super.setSelectRegion(region);
        return this;
    }

    @Step("Set Select Role")
    public CreateNewUserPage setSelectRole(Role roleId) {
        super.setSelectRole(roleId);
        return this;
    }

    public CreateNewUserPage clearLoginInput() {
        getLoginInput().clear();
        return this;
    }

    public CreateNewUserPage clearFirstNameInput() {
        super.clearFirstNameInput();
        return this;
    }

    public CreateNewUserPage clearLastNameInput() {
        super.clearLastNameInput();
        return this;
    }

    public CreateNewUserPage clearPasswordInput() {
        super.clearPasswordInput();
        return this;
    }

    public CreateNewUserPage clearConfirmPasswordInput() {
        super.clearConfirmPasswordInput();
        return this;
    }

    public CreateNewUserPage clearEmailInput() {
        super.clearEmailInput();
        return this;
    }

    @Step("clickCreateButton")
    public CreateNewUserPage clickCreateButton() {
        super.clickCreateButton();
        return this;
    }

    public CreateNewUserPage clickCancelButton() {
        super.clickCancelButton();
        return this;
    }


    // business logic
    public CreateNewUserPage setLoginData(IUser user) {
        setLoginInput(user.getLoginname());
        super.setLoginData(user);
        return this;
    }

    public AdministrationPage successCreateNewUser(IUser validUser) {
        setLoginData(validUser);
        waitForInputErrorsToDisappear();
        clickCreateButton();
        return new AdministrationPage(driver);
    }

    public CreateNewUserPage unsuccessCreateNewUser(IUser invalidUser) {
        setLoginData(invalidUser);
        clickCreateButton();
        return this;
    }


    public AdministrationPage cancelCreateNewUser(IUser someUser) {
        setLoginData(someUser);
        clickCancelButton();
        return new AdministrationPage(driver);
    }

    public CreateNewUserPage acceptAlert() {
        super.acceptAlert();
        return this;
    }
}
