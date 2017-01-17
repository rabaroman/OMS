package com.softserve.edu.oms.tests.createuser;

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
 * Test verifies that validation on empty mandatory fields works while
 * creating new user
 *
 * Based on LVSETOMS-49 in Jira
 *
 * @author Dmytro Voropai
 */
@Features("Create New User")
@Stories("LVSETOMS-3 As Administrator I want to create new user so he can log into the application")

public class UserWithEmptyMandatoryFieldsCreationTest extends TestRunner {

    // Provides login and password of registered user
    // with an "Administrator" role
    @DataProvider
    public Object[][] validUserAdministrator() {
        return new Object[][] {
                {UserRepository.get().adminUser()}
        };
    }

    @TestCaseId("LVSETOMS-49")
	@Severity(SeverityLevel.CRITICAL)
    @Description("This test case verifies that validation on empty mandatory fields works while creating new user.")
    @Test(dataProvider = "validUserAdministrator", alwaysRun = true)
    @Step("Checking if validation works on empty mandatory fields while creating new users")
    public void verifyErrorMessagesDuringUserCreation(IUser validUserAdministrator) {
        AdminHomePage omsAdminHomePage = loginPage.successAdminLogin(validUserAdministrator);
        CreateNewUserPage omsСreateNewUserPage = omsAdminHomePage
                .gotoAdministrationPage()
                .gotoCreateNewUserPage()
                .clickCreateButton()
                .acceptAlert();

   //  Assert that messages appear on page for all mandatory fields
        Assert.assertNotNull(omsСreateNewUserPage.getLoginErrorMessageText());
        Assert.assertNotNull(omsСreateNewUserPage.getFirstNameErrorMessageText());
        Assert.assertNotNull(omsСreateNewUserPage.getLastNameErrorMessageText());
        Assert.assertNotNull(omsСreateNewUserPage.getPasswordErrorMessageText());
        Assert.assertNotNull(omsСreateNewUserPage.getEmailErrorMessageText());

    //  Compare existing messages with expected
        Assert.assertEquals(omsСreateNewUserPage.getLoginErrorMessageText(),
                ErrorMessagesEnum.EXPECTED_ERROR_MESSAGE_FOR_lOGIN_TC49.message);
        Assert.assertEquals(omsСreateNewUserPage.getFirstNameErrorMessageText(),
                ErrorMessagesEnum.EXPECTED_ERROR_MESSAGE_FOR_FIRST_NAME_TC49.message);
        Assert.assertEquals(omsСreateNewUserPage.getLastNameErrorMessageText(),
                ErrorMessagesEnum.EXPECTED_ERROR_MESSAGE_FOR_LAST_NAME_TC49.message);
        Assert.assertEquals(omsСreateNewUserPage.getPasswordErrorMessageText(),
                ErrorMessagesEnum.EXPECTED_ERROR_MESSAGE_FOR_PASSWORD_TC49.message);
        Assert.assertEquals(omsСreateNewUserPage.getEmailErrorMessageText(),
                ErrorMessagesEnum.EXPECTED_ERROR_MESSAGE_FOR_EMAIL_ADDRESS_TC49.message);
    }
}
