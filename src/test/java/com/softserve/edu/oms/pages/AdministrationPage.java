package com.softserve.edu.oms.pages;

import com.softserve.edu.oms.enums.ConditionFilterDropdownList;
import com.softserve.edu.oms.enums.FieldFilterDropdownList;
import com.softserve.edu.oms.locators.AdministrationPageLocators;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.NoSuchElementException;

/**
 * This page represents PageObject for Administration page
 */
public class AdministrationPage extends AbstractAdminReportPage {

    public static final Logger logger = LoggerFactory.getLogger(AbstractAdminReportPage.class);
//    Constructor with driver initialization
	public AdministrationPage(WebDriver driver) {
		super(driver);
	}

    /**
     *
     * Delete user by login. Administrator set Fields filter on User Name,
     * set condition filter on equals input login of desired user who should be deleted into
     * search text field and click search button
     */
	public AdministrationPage deleteUserByLogin(final String login, boolean deleteUserOrNot) {
        selectField(FieldFilterDropdownList.LOGIN);
        selectCondition(ConditionFilterDropdownList.EQUALS);
        search(login);

		try {
			driver.findElement(AdministrationPageLocators.DELETE_LINK.by).click();
			if (deleteUserOrNot) {
				driver.switchTo().alert().accept();
			} else {
				driver.switchTo().alert().dismiss();
			}
		} catch (NoSuchElementException e) {
            logger.error("No user with login {} found", login);
		}
		waitForLoad();
		return this;
	}

    /**
     * User as role an "Administrator" goes from tab "Administration" to
     * "Create New User" page by using this method
     */
    @Step("User as role an Administrator goes from tab Administration to Create New User page")
    public CreateNewUserPage gotoCreateNewUserPage() {
        driver.findElement(AdministrationPageLocators.GO_TO_CREATE_NEW_USER_PAGE.by).click();
        logger.info("Click action performed on Create New User link");
        return new CreateNewUserPage(this.driver);
    }

    /**
     * Ascending sorting grid on Administration page by First Name column
     */
    @Override
    public AdministrationPage sortByFirstNameASC(){
        super.sortByFirstNameASC();
        return this;
    }


    /**
     * Descending sorting grid on Administration page by First Name column
     */
    @Override
    public AdministrationPage sortByFirstNameDESC(){
        super.sortByFirstNameDESC();
        return this;
    }

    /**
     * Ascending sorting grid on Administration page by Last Name column
     */
    @Override
    public AdministrationPage sortByLastNameASC(){
        super.sortByLastNameASC();
        return this;
    }

    /**
     * Descending sorting grid on Administration page by Last Name column
     */
    @Override
    public AdministrationPage sortByLastNameDESC(){
        super.sortByLastNameDESC();
        return this;
    }

    /**
     * Ascending sorting grid on Administration page by Login column
     */
    @Override
    public AdministrationPage sortByLoginASC(){
        super.sortByLoginASC();
        return this;
    }

    /**
     * Descending sorting grid on Administration page by Login column
     */
    @Override
    public AdministrationPage sortByLoginDESC(){
        super.sortByLoginDESC();
        return this;
    }

    /**
     *  Ascending sorting grid on Administration page by Role column
     */
    @Step("Ascending sorting grid on Administration page by Role column")
    @Override
    public AdministrationPage sortByRoleASC(){
        super.sortByRoleASC();
        return this;
    }

    /**
     * Descending sorting grid on Administration page by Role column
     */
    @Step("Descending sorting grid on Administration page by Role column")
    @Override
    public AdministrationPage sortByRoleDESC(){
        super.sortByRoleDESC();
        return this;
    }

    /**
     * Ascending sorting grid on Administration page by Region column
     */
    @Step(" Ascending sorting grid on Administration page by Region column")
    @Override
    public AdministrationPage sortByRegionASC(){
        super.sortByRegionASC();
        return this;
    }

    /**
     * Descending sorting grid on Administration page by Region column
     */
    @Step("Descending sorting grid on Administration page by Region column")
    @Override
    public AbstractAdminReportPage sortByRegionDESC(){
        super.sortByRegionDESC();
        return this;
    }

    /**
     * Change user quantity per page by click "Show 10 items" or "Show 5 items"
     */
	public AdministrationPage changeQuantityOfUsersPerPage(){
		getShowItemsLink().click();
		return this;
	}

    /**
     * This  method verifies if "Items Link"
     * is displayed on page
     */
	public boolean showItemsLinkIsDisplayed () {
		return this.getShowItemsLink().isDisplayed();
	}


    /**
     * Find user and return EditUserPage for user editing
     */
	public EditUserPage editUserByLogin(String login){
        selectField(FieldFilterDropdownList.LOGIN);
        selectCondition(ConditionFilterDropdownList.EQUALS);
        search(login);

		driver.findElement(AdministrationPageLocators.EDIT_USER_LINK.by).click();
		return new EditUserPage(driver);
	}

    /**
     * This method get users form current page and
     * @return quantity of user per page
     */
    @Step("getQuantityOfUsersPerPage")
	public int getQuantityOfUsersPerPage (){
		return getUsersFromCurrentPage().size();
	}


    /**
     *  Set  up default search conditions
     */
	public AdministrationPage setDefaultFilterConditions(){
        selectField(FieldFilterDropdownList.LOGIN);
        selectCondition(ConditionFilterDropdownList.EQUALS);
        clickSearchButton();
        waitForLoad();
		return this;
	}

    /**
     * Click on Create report link and
     * @return CreateReportPage
     */
	public CreateReportPage goToCreateReportPage() {
		driver.findElement(AdministrationPageLocators.CREATE_REPORT.by).click();
		return new CreateReportPage(this.driver);
	}

}


