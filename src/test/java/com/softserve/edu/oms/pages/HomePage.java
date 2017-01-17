	package com.softserve.edu.oms.pages;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;

	import static com.softserve.edu.oms.locators.UserHomePageLocators.*;

	/**
	 * This class represents PageObject for all HomePages
	 */
	public class HomePage extends AbstractBasePage {

		public HomePage(WebDriver driver) {
			super(driver);
		}

		// get Data
		public WebElement getFirstname() {
			return driver.findElement(FIRST_NAME_LABEL.by);
		}

		public WebElement getLastname() {
			return driver.findElement(LAST_NAME_LABEL.by);
		}

		public WebElement getRole() {
			return driver.findElement(ROLE_LABEL.by);
		}

		public WebElement getEngLangLink() {
			return driver.findElement(ENG_LANG_LINK.by);
		}

		public WebElement getUkrLangLink() {
			return driver.findElement(UKR_LANG_LINK.by);
		}

		// Functional
		public String getFirstnameText() {
			return getFirstname().getText().trim();
		}

		public String getLastnameText() {
			return getLastname().getText().trim();
		}

		public String getRoleText() {
			return getRole().getText().trim();
		}

		public String getEngLangLinkText() {
			return getEngLangLink().getText().trim();
		}

		public String getUkrLangLinkText() {
			return getUkrLangLink().getText().trim();
		}


		// set Data
		public void clickEngLangLink() {
			getEngLangLink().click();
		}

		public void clickUkrLangLink() {
			getUkrLangLink().click();
		}


		// Business Logic

		public HomePage gotoUserInfoTab(){
			String role = getRoleText();
			clickUserInfoTab();
			switch(role){
				case "Administrator":
					return new AdminHomePage(driver);

				case "Customer":
					return new CustomerHomePage(driver);

				case "Merchandiser":
					return new MerchandiserHomePage(driver);

				case "Supervisor":
					return new SupervisorHomePage(driver);

				default:
					return new HomePage(driver);
			}
		}

	}
