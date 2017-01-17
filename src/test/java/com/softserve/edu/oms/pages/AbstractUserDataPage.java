package com.softserve.edu.oms.pages;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.enums.Region;
import com.softserve.edu.oms.enums.Role;
import com.softserve.edu.oms.locators.AbstractUserDataPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.softserve.edu.oms.locators.AbstractUserDataPageLocators.*;

/**
 * This abstract class represents common functionality for
 * Create New User Page and Edit User Page
 */
public abstract class AbstractUserDataPage extends AbstractBasePage {

	public static final Logger logger = LoggerFactory.getLogger(AbstractUserDataPage.class);

	AbstractUserDataPage(WebDriver driver) {
		super(driver);
	}

	// get Data
	public WebElement getLoginInput() {
		return this.driver.findElement(LOGIN_INPUT.by);
	}

	public WebElement getFirstNameInput() {
		return this.driver.findElement(FIRST_NAME_INPUT.by);
	}

	public WebElement getLastNameInput() {
		return this.driver.findElement(LAST_NAME_INPUT.by);
	}

	public WebElement getPasswordInput() {
		return this.driver.findElement(PASSWORD_INPUT.by);
	}

	public WebElement getConfirmPasswordInput() {
		return this.driver.findElement(CONFIRM_PASSWORD_INPUT.by);
	}

	public WebElement getEmailInput() {
		return this.driver.findElement(EMAIL_INPUT.by);
	}

	public WebElement getRegionDropDown() {
		return this.driver.findElement(REGION_SELECT.by);
	}

	public List<WebElement> getRoleRadioButton() {
		return this.driver.findElements(ROLE_RADIO_BUTTON.by);
	}

	public WebElement getCreateButton() {
		return this.driver.findElement(CREATE_BUTTON.by);
	}

	public WebElement getCancelButton() {
		return this.driver.findElement(CANCEL_BUTTON.by);
	}

	public WebElement getLoginErrorMessage() {
		return this.driver.findElement(ERROR_LOGIN.by);
	}

	public WebElement getFirstNameErrorMessage() { return this.driver.findElement(ERROR_FIRST_NAME.by); }

	public WebElement getLastNameErrorMessage() { return this.driver.findElement(ERROR_LAST_NAME.by); }

	public WebElement getPasswordErrorMessage() {
		return this.driver.findElement(ERROR_PASSWORD.by);
	}

	public WebElement getConfirmPasswordErrorMessage() {
		return this.driver.findElement(ERROR_CONFIRM_PASSWORD.by);
	}

	public WebElement getEmailErrorMessage() {
		return this.driver.findElement(ERROR_EMAIL.by);
	}

	// functional

	public String getLoginInputText() {
		return getLoginInput().getText();
	}

	public String getFirstNameInputText() {
		return getFirstNameInput().getText();
	}

	public String getLastNameInputText() {
		return getLastNameInput().getText();
	}

	public String getPasswordInputText() {
		return getPasswordInput().getText();
	}

	public String getConfirmPasswordInputText() {
		return getConfirmPasswordInput().getText();
	}

	public String getEmailInputText() {
		return getEmailInput().getText();
	}

	public Select getSelectRegion() {
		return new Select(getRegionDropDown());
	}

	public String getCreateButtonText() {
		return getCreateButton().getText().trim();
	}

	public String getCancelButtonText() {
		return getCancelButton().getText().trim();
	}

	public String getLoginErrorMessageText() {
		return waitForElement(ERROR_LOGIN.by).getText();
	}

	public String getFirstNameErrorMessageText() { return waitForElement(ERROR_FIRST_NAME.by).getText(); }

	public String getLastNameErrorMessageText() {
		return waitForElement(ERROR_LAST_NAME.by).getText();
	}

	public String getPasswordErrorMessageText() { return waitForElement(ERROR_PASSWORD.by).getText();}

	public String getConfirmPasswordErrorMessageText() {
		return waitForElement(ERROR_CONFIRM_PASSWORD.by).getText();
	}

	public String getEmailErrorMessageText() {
		return waitForElement(ERROR_EMAIL.by).getText();
	}

	public Boolean isLoginErrorDisplayed() {
		try {
			waitForElement(ERROR_LOGIN.by);
			return true;
		}
		catch(TimeoutException e){
			return false;
		}
	}

	public Boolean isFirstNameErrorDisplayed() {
		try {
			waitForElement(ERROR_FIRST_NAME.by);
			return true;
		}
		catch(TimeoutException e){
			return false;
		}
	}

	public Boolean isLastNameErrorDisplayed() {
		try {
			waitForElement(ERROR_LAST_NAME.by);
			return true;
		}
		catch(TimeoutException e){
			return false;
		}
	}

	public Boolean isPasswordErrorDisplayed() {
		try {
			waitForElement(ERROR_PASSWORD.by);
			return true;
		}
		catch(TimeoutException e){
			return false;
		}
	}

	public Boolean isConfirmPasswordErrorDisplayed() {
		try {
			waitForElement(ERROR_CONFIRM_PASSWORD.by);
			return true;
		}
		catch(TimeoutException e){
			return false;
		}
	}

	public Boolean isEmailErrorDisplayed() {
		try {
			waitForElement(ERROR_EMAIL.by);
			return true;
		}
		catch(TimeoutException e){
			return false;
		}
	}


	// set data
	public AbstractUserDataPage setFirstNameInput(String firstName) {
		getFirstNameInput().clear();
		getFirstNameInput().sendKeys(firstName);
		logger.info("{} entered in the First Name input field", firstName);
		return this;
	}

	public AbstractUserDataPage setLastNameInput(String lastName) {
		getLastNameInput().clear();
		getLastNameInput().sendKeys(lastName);
		logger.info("{} entered in the Last Name input field", lastName);
		return this;
	}

	public AbstractUserDataPage setPasswordInput(String password) {
		getPasswordInput().clear();
		getPasswordInput().sendKeys(password);
		logger.info("{} entered in the Password input field", password);
		return this;
	}

	public AbstractUserDataPage setConfirmPasswordInput(String confirmPassword) {
		getConfirmPasswordInput().clear();
		getConfirmPasswordInput().sendKeys(confirmPassword);
		logger.info("{} entered in the Confirm Password input field", confirmPassword);
		return this;
	}

	public AbstractUserDataPage setEmailInput(String email) {
		getEmailInput().clear();
		getEmailInput().sendKeys(email);
		logger.info("{} entered in the Email input field", email);
		return this;
	}

	public AbstractUserDataPage setSelectRegion(Region region) {
		getSelectRegion().selectByVisibleText(region.getRegionType());
		logger.info("{} Region is selected", region.toString().toLowerCase());
		return this;
	}

	public AbstractUserDataPage setSelectRole(Role roleId) {
		driver.findElement(By.id(roleId.getRoleId())).click();
		waitForLoad();
		logger.info("{} Role is selected", roleId.toString().toLowerCase());
		return this;
	}

	public AbstractUserDataPage clearFirstNameInput() {
		getFirstNameInput().clear();
		return this;
	}

	public AbstractUserDataPage clearLastNameInput() {
		getLastNameInput().clear();
		return this;
	}

	public AbstractUserDataPage clearPasswordInput() {
		getPasswordInput().clear();
		return this;
	}

	public AbstractUserDataPage clearConfirmPasswordInput() {
		getConfirmPasswordInput().clear();
		return this;
	}

	public AbstractUserDataPage clearEmailInput() {
		getEmailInput().clear();
		return this;
	}

	public AbstractUserDataPage clickCreateButton() {
		getCreateButton().click();
		logger.info("Click action performed on Create button");
		return this;
	}

	public AbstractUserDataPage clickCancelButton() {
		getCancelButton().click();
		waitForLoad();
		logger.info("Click action performed on Cancel button");
		return this;
	}

	public AbstractUserDataPage setLoginData(IUser user) {
		setFirstNameInput(user.getFirstname());
		setLastNameInput(user.getLastname());
		setPasswordInput(user.getPassword());
		setConfirmPasswordInput(user.getPassword());
		setEmailInput(user.getEmail());
		setSelectRegion(Region.getRegion(user.getRegion()));
		setSelectRole(Role.valueOf(user.getRole().toUpperCase()));
		return this;
	}

	public AdministrationPage successCreateNewUser() {
		waitForInputErrorsToDisappear();
		clickCreateButton();
		return new AdministrationPage(driver);
	}

	/**
	 * This method is used to wait for every possible input error which is displayed
	 * due to a lag to disappear.
	 * Precondition of inputting valid data should be met when using this method.
	 */
	protected void waitForInputErrorsToDisappear() {
		if (waitForElementToDisappear(AbstractUserDataPageLocators.ERROR_PASSWORD.by)
				&& waitForElementToDisappear(AbstractUserDataPageLocators.ERROR_CONFIRM_PASSWORD.by)
				&& waitForElementToDisappear(AbstractUserDataPageLocators.ERROR_EMAIL.by)) {
			logger.info("***Input Errors Disappeared***");
		} else {
			throw new RuntimeException("Waiting for input errors to disappear failed!");
		}
	}
}
