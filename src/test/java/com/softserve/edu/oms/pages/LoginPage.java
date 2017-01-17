package com.softserve.edu.oms.pages;

import com.softserve.edu.oms.data.IUser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.allure.annotations.Step;

import static com.softserve.edu.oms.locators.LoginPageLocators.*;

/**
 * PageObject class that represents Login page.
 *
 * @version 1.0
 * @since 16.12.16
 * @author Anastasiia Maidanska
 */
public class LoginPage extends AbstractBasePage {

	public static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

	//Class constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	//Getters
	private WebElement getLoginnameInput() {
		return this.driver.findElement(LOGIN_INPUT_FIELD.by);
	}

	private WebElement getPasswordInput() {
		return this.driver.findElement(PASSWORD_INPUT_FIELD.by);
	}

	private WebElement getSubmitButton() {
		return this.driver.findElement(LOGIN_BUTTON.by);
	}

	private WebElement getResetButton() {
		return this.driver.findElement(RESET_BUTTON.by);
	}

	private WebElement getRememberMeCheckbox() {
		return this.driver.findElement(REMEMBER_ME_CHECKBOX.by);
	}

	private WebElement getBadCredentialsErrorMessage() {
		return waitForElement(BAD_CREDENTIALS_ERROR_MESSAGE.by);
	}

	//Functional
	@Step("getLoginnameInputText")
	public String getLoginnameInputText() {
		return getLoginnameInput().getText();
	}

	@Step("getPasswordInputText")
	public String getPasswordInputText() {
		return getPasswordInput().getText();
	}

	public String getSubmitButtonText() {
		return getSubmitButton().getText().trim();
	}

	public String getResetButtonText() {
		return getResetButton().getText().trim();
	}

	public String getRememberMeCheckboxNameAttribute() {
		return getRememberMeCheckbox()
				.getAttribute(ATTRIBUTE.name()).toLowerCase().trim();
	}

	public String getBadCredentialsErrorMessageText() {
		return this.getBadCredentialsErrorMessage().getText();
	}

	private void clearLoginnameInput() {
        getLoginnameInput().clear();
    }

    private void clearPasswordInput() {
        getPasswordInput().clear();
    }

	public void setLoginnameInput(String login) {
		getLoginnameInput().sendKeys(login);
        logger.info("Login {} entered in the User input field", login);
	}

	private void setLoginnameInputClear(String login) {
		clearLoginnameInput();
		setLoginnameInput(login);
	}

	public void setPasswordInput(String password) {
		getPasswordInput().sendKeys(password);
        logger.info("Password {} entered in the User input field", password);
	}

	private void setPasswordInputClear(String password) {
		clearPasswordInput();
		setPasswordInput(password);
	}

	public void clickSubmitButton() {
		getSubmitButton().click();
        logger.info("Click action performed on Submit button");
    }

	private void clickResetButton() {
		getResetButton().click();
        logger.info("Click action performed on Reset button");
	}

	public void clickGetRememberMeCheckbox() {
		getRememberMeCheckbox().click();
        logger.info("Remember Me checkbox is checked");
	}

	/**
	 * Business logic
	 */
	@Step("Set Login Data")
    private void setLoginData(IUser user) {
		setLoginnameInputClear(user.getLoginname());
		setPasswordInputClear(user.getPassword());
		clickSubmitButton();
	}

	@Step("setLoginDataAndReset")
	public void setLoginDataAndReset(IUser user) {
		setLoginnameInputClear(user.getLoginname());
		setPasswordInputClear(user.getPassword());
		clickResetButton();
	}

    @Step("Login as Admin")
    public AdminHomePage successAdminLogin(IUser admin) {
		setLoginData(admin);
        logger.info("Logging as Admin");
		return new AdminHomePage(driver);
	}

	@Step("Login as Customer")
	public CustomerHomePage successCustomerLogin(IUser customer){
		setLoginData(customer);
        logger.info("Logging as Customer");
		return new CustomerHomePage(driver);
	}

	@Step("Login as Merchandiser")
	public MerchandiserHomePage successMerchandiserLogin(IUser merchandiser){
		setLoginData(merchandiser);
        logger.info("Logging as Merchandiser");
		return new MerchandiserHomePage(driver);
	}

	@Step("Login as Supervisor")
	public SupervisorHomePage successSupervisorLogin(IUser supervisor){
		setLoginData(supervisor);
        logger.info("Logging as Supervisor");
		return new SupervisorHomePage(driver);
	}

	@Step("Login with invalid credentials")
    public LoginPage unsuccessfulLogin(IUser invalidUser) {
    	setLoginData(invalidUser);
		return this;
	}

	@Step("Login with empty credentials")
	public LoginPage loginWithEmptyCredentials (){
		this.clearLoginnameInput();
		this.clearPasswordInput();
		this.clickSubmitButton();
		return this;
	}

}
