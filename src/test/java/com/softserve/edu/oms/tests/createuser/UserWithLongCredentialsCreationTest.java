package com.softserve.edu.oms.tests.createuser;

import com.softserve.edu.oms.data.DBUtils;
import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.enums.ErrorMessagesEnum;
import com.softserve.edu.oms.pages.AdminHomePage;
import com.softserve.edu.oms.pages.CreateNewUserPage;
import com.softserve.edu.oms.tests.TestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.model.SeverityLevel;

/**
 * This test case verifies that error messages appear when trying to create
 * a new user with too long login, first /last name (longer than 13 characters)
 * and password (longer than 10 character
 *
 * Based on LVSETOMS-51 in Jira
 *
 * @author Dmytro Voropai
 */
@Features("Create New User")
@Stories("LVSETOMS-3 As Administrator I want to create new user so he can log into the application")

public class UserWithLongCredentialsCreationTest extends TestRunner{

    final private DBUtils dbUtils = new DBUtils();
    final private IUser userWithLongCredentials= UserRepository.get().UserWithLongCredentials();

    // Provides login and password of registered user
    // with an "Administrator" role
    @DataProvider
    public Object[][] validUserAdministrator() {
        return new Object[][] {
                {UserRepository.get().adminUser()}
        };
    }

    @TestCaseId("LVSETOMS-51")
	@Severity(SeverityLevel.CRITICAL)
    @Description("This test case verifies that error messages appear when trying"
    		+ " to create a new user with too long login, first /last name "
    		+ "(longer than 13 characters) and password (longer than 10 characters).")
    @Test(dataProvider = "validUserAdministrator",alwaysRun = true)
    @Step("Verification of correctness validation messages while trying to create new user with long credentials")
    public void verifyCreateNewUserWithLongCredentials(IUser validUserAdministrator){
        AdminHomePage omsAdminHomePage = loginPage.successAdminLogin(validUserAdministrator);
        CreateNewUserPage omsСreateNewUserPage = omsAdminHomePage
                .gotoAdministrationPage()
                .gotoCreateNewUserPage()
                .setLoginData(userWithLongCredentials)
                .clickCreateButton()
                .acceptAlert();

//        Check if correct messages appeared for required fields
        Assert.assertEquals(omsСreateNewUserPage.getLoginErrorMessageText(),
                ErrorMessagesEnum.EXPECTED_ERROR_MESSAGE_FOR_lOGIN_TC51.message);
        Assert.assertEquals(omsСreateNewUserPage.getFirstNameErrorMessageText(),
                ErrorMessagesEnum.EXPECTED_ERROR_MESSAGE_FOR_FIRST_NAME_TC51.message);
        Assert.assertEquals(omsСreateNewUserPage.getLastNameErrorMessageText(),
                ErrorMessagesEnum.EXPECTED_ERROR_MESSAGE_FOR_LAST_NAME_TC51.message);
        Assert.assertEquals(omsСreateNewUserPage.getPasswordErrorMessageText(),
                ErrorMessagesEnum.EXPECTED_ERROR_MESSAGE_FOR_PASSWORD_TC51.message);

//        Check if user with too long credentials was not created in database
        Assert.assertNull(dbUtils.getUserByLogin(userWithLongCredentials.getLoginname()));
    }
}
