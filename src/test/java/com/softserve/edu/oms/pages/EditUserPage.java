    package com.softserve.edu.oms.pages;

    import com.softserve.edu.oms.data.IUser;
    import com.softserve.edu.oms.enums.Region;
    import com.softserve.edu.oms.enums.Role;
    import org.openqa.selenium.WebDriver;

    /**
     * This class represetns PageObject for Edit
     * User Page
     */
    public class EditUserPage  extends AbstractUserDataPage {
        public EditUserPage (WebDriver driver) {
            super(driver);
        }

        //set Data
        public EditUserPage setLoginInput(String login) {
            getLoginInput().clear();
            getLoginInput().sendKeys(login);
            return this;
        }

        public EditUserPage setFirstNameInput(String firstName) {
            super.setFirstNameInput(firstName);
            return this;
        }

        public EditUserPage setLastNameInput(String lastName) {
            super.setLastNameInput(lastName);
            return this;
        }

        public EditUserPage setPasswordInput(String password) {
            super.setPasswordInput(password);
            return this;
        }

        public EditUserPage setConfirmPasswordInput(String confirmPassword) {
            super.setConfirmPasswordInput(confirmPassword);
            return this;
        }

        public EditUserPage setEmailInput(String email) {
            super.setEmailInput(email);
            return this;
        }

        public EditUserPage setSelectRegion(Region region) {
            super.setSelectRegion(region);
            return this;
        }

        public EditUserPage setSelectRole(Role roleId) {
            super.setSelectRole(roleId);
            return this;
        }

        public EditUserPage clearLoginInput() {
            getLoginInput().clear();
            return this;
        }

        public  EditUserPage clearFirstNameInput() {
            super.clearFirstNameInput();
            return this;
        }

        public EditUserPage clearLastNameInput() {
            super.clearLastNameInput();
            return this;
        }

        public EditUserPage clearPasswordInput() {
            super.clearPasswordInput();
            return this;
        }

        public EditUserPage clearConfirmPasswordInput() {
            super.clearConfirmPasswordInput();
            return this;
        }

        public EditUserPage clearEmailInput() {
            super.clearEmailInput();
            return this;
        }

        public EditUserPage clickCreateButton() {
            super.clickCreateButton();
            return this;
        }

        public EditUserPage  clickCancelButton() {
            super.clickCancelButton();
            return this;
        }
        // business logic
        public EditUserPage setLoginData(IUser user) {
            super.setLoginData(user);
            return this;
        }

        public AdministrationPage successEditUser(IUser validUser){
            setLoginData(validUser);
            clickCreateButton();
            return new AdministrationPage(driver);
        }

        public AdministrationPage successEditUser(){
            clickCreateButton();
            return new AdministrationPage(driver);
        }

        public AdministrationPage cancelEditUser(IUser someUser) {
            setLoginData(someUser);
            clickCancelButton();
            return new AdministrationPage(driver);
        }

    }

