package com.softserve.edu.oms.tests.createuser;

import com.softserve.edu.oms.data.DBUtils;
import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.enums.Region;
import com.softserve.edu.oms.enums.Role;
import com.softserve.edu.oms.pages.AdminHomePage;
import com.softserve.edu.oms.pages.AdministrationPage;
import com.softserve.edu.oms.pages.CreateNewUserPage;
import com.softserve.edu.oms.tests.TestRunner;
import org.hamcrest.CoreMatchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.softserve.edu.oms.enums.ErrorMessagesEnum.CONFIRM_PASSWORD_ERROR_MESSAGE;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * This test verifies that error message is shown
 * when creating new user with not confirmed password
 *
 * Based on LVSETOMS-58 in Jira
 *
 * @author Iryna Kyselchuk
 * @since 16.12.16
 */
@Features("Create New User")
@Stories("LVSETOMS-3 As Administrator I want to create new user so he can log into the application")


public class ErrorMsgConfirmPasswordTest extends TestRunner{

    public static final Logger logger = LoggerFactory.getLogger(ErrorMsgConfirmPasswordTest.class);
    private CreateNewUserPage createNewUserPage;
    /**
     * Provides data for user login
     * @return badMemoryUser from UserRepository
     */
    @DataProvider
    public Object[][] badMemoryUser() {
        return new Object[][] {
                { UserRepository
                        .get()
                        .badMemoryUser() }
        };
    }

    /**
     * Set preconditions for test:
     * login with Administrator role credentials
     * and navigate to Create New User page
     */
    @BeforeMethod
    @Step("Login as Admin and go to Create New User page")
    public void loginAndGotoCreateUserPage() {
        IUser admin = UserRepository
                .get()
                .adminUser();
        // login as Administrator
        AdminHomePage adminHomePage = loginPage.successAdminLogin(admin);
        AdministrationPage administrationPage = adminHomePage.gotoAdministrationPage();
        createNewUserPage = administrationPage.gotoCreateNewUserPage();
    }

    /**
     * This method verifies that error message appears
     * when trying to create a new user with
     * correct values in 'Login Name', 'First Name', 'Last Name', 'Email Address' fields
     * and different values in 'Password' and 'Confirm Password' fields
     *
     * @param newUser {@link com.softserve.edu.oms.data.UserRepository}
     */

  	@TestCaseId("LVSETOMS-58")
  	@Severity(SeverityLevel.NORMAL)
    @Description("This test case verifies that error message appears when trying to create "
    		+ "a new user with different values in 'Password' and 'Confirm Password' fields")
    
    @Test(dataProvider = "badMemoryUser")
    @Step("Verify error message is shown while creating user with not confirmed password")
    public void verifyErrorMsgUserWithNotConfirmedPassword(IUser newUser) {

        DBUtils dbUtils = new DBUtils();

        logger.info("Test verifyErrorMsgUserWithNotConfirmedPassword start");

        innerStep("Verify that user with chosen login does not exist");
        assertThat(dbUtils.getUserByLogin(newUser.getLoginname()), CoreMatchers.equalTo(null));

        // set correct data for new user account
        // valid value in 'Confirm Password' but not the same as in 'Password' field
        createNewUserPage
                .setLoginInput(newUser.getLoginname())
                .setFirstNameInput(newUser.getFirstname())
                .setLastNameInput(newUser.getLastname())
                .setPasswordInput(newUser.getPassword())
                .setConfirmPasswordInput(newUser.getPassword().toUpperCase())
                .setEmailInput(newUser.getEmail())
                .setSelectRegion(Region.getRegion(newUser.getRegion()))
                .setSelectRole(Role.valueOf(newUser.getRole().toUpperCase()));

        innerStep("Verify that correct error message appears");
        Assert.assertTrue(createNewUserPage.isConfirmPasswordErrorDisplayed()
                && createNewUserPage.getConfirmPasswordErrorMessageText().equals(CONFIRM_PASSWORD_ERROR_MESSAGE.message));

        innerStep("Verify that user with invalid confirm password is not created");
        assertThat(dbUtils.getUserByLogin(newUser.getLoginname()), CoreMatchers.equalTo(null));
        logger.info("Test verifyErrorMsgUserWithNotConfirmedPassword done");
    }

    /**
     * Logout from current page
     */
    @AfterMethod
    @Step("Logout from Create New User page")
    public void returnToPreviousState() {
        createNewUserPage.logout();
    }
}
