    package com.softserve.edu.oms.tests.createuser;

    import com.softserve.edu.oms.data.DBUtils;
import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.enums.SQLQueries;
import com.softserve.edu.oms.pages.AdministrationPage;
import com.softserve.edu.oms.pages.CreateNewUserPage;
import com.softserve.edu.oms.tests.TestRunner;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

    @Features("Create New User")
    @Stories("LVSETOMS-3 As Administrator I want to create new user so he can log into the application")

    public class LoginFieldIsCaseInsensitiveTest extends TestRunner{

        public static final Logger logger = LoggerFactory.getLogger(LoginFieldIsCaseInsensitiveTest.class);

    @DataProvider
    public Object[][] admAndNonExistingUser() {
        return new Object[][] {
                { UserRepository.get().adminUser(), UserRepository.get().nonExistingUser() }
        };
    }

    /**
     * <h1>Verify that Login field is case insensitive</h1>
     * This test goes to Create New User Page,
     * 1) executes SQL query to verify that nonExistingUser from UserRepository
     * really is not in DB
     * 2) If he, for some reason, exists - then generates new random Login
     * 3) Then the data for nonExistingUser are set in the form "Create new user"
     * 4) the fields "Role" and "Region" are not used, and leave as default
     * 5) the same Login, but in reverse case is set in Login field
     * 6) error message should appear - if so test passed
     *
     * <p>Note: there are two data providers and thus two tests, because
     * first we login as Admin and then trying to create New User</p>
     *
     * @author Viktoriia Bybel
     * @version 1.0
     * @since 15.12.16
     * @param nonExistingUser
     * @see UserRepository
     */
    @TestCaseId("LVSETOMS-56")
	@Severity(SeverityLevel.CRITICAL)
    @Description("This test case verifies that 'Login Name' is case insensitive while creating new user.")


    @Test(dataProvider = "admAndNonExistingUser")
    @Step("LoginFieldIsCaseInsensitiveTest")
    public void loginFieldIsCaseInsensitiveTest(IUser admUser, IUser nonExistingUser) {

        logger.info("Test loginFieldIsCaseInsensitive started");

        innerStep("Login and go to Create New User Page");
        CreateNewUserPage adminHomePage = loginPage.successAdminLogin(admUser)
                .gotoAdministrationPage()
                .gotoCreateNewUserPage();

        innerStep("Verifying that user do not exist and generating a new one");
        DBUtils dbUtils = new DBUtils();
        String nonExistingLogin = nonExistingUser.getLoginname();
        String nonExistingFirstName = nonExistingUser.getFirstname();
        String nonExistingLastName = nonExistingUser.getLastname();

        while (dbUtils.verifyThatUserIsInDB(nonExistingLogin)) {
            nonExistingLogin = RandomStringUtils.random(5, true, false).toLowerCase();
            nonExistingFirstName = RandomStringUtils.random(5, true, false);
            nonExistingLastName = RandomStringUtils.random(5, true, false);
        }

        innerStep("Fill in a form to create new user");
        CreateNewUserPage newUserPage = new CreateNewUserPage(driver);
        newUserPage
                .setLoginInput(nonExistingLogin)
                .setFirstNameInput(nonExistingFirstName)
                .setLastNameInput(nonExistingLastName)
                .setPasswordInput(nonExistingUser.getPassword())
                .setConfirmPasswordInput(nonExistingUser.getPassword())
                .setEmailInput(nonExistingUser.getEmail())
                .successCreateNewUser();

        innerStep("Entering the reverse case login to verify error message will appear");
        CreateNewUserPage newUserPageAgain = new AdministrationPage(driver)
                .gotoCreateNewUserPage()
                .setLoginInput(nonExistingLogin.toUpperCase())
                .setFirstNameInput(nonExistingFirstName)
                .setLastNameInput(nonExistingLastName)
                .setPasswordInput(nonExistingUser.getPassword())
                .setConfirmPasswordInput(nonExistingUser.getPassword());

        Assert.assertTrue(newUserPageAgain.isLoginErrorDisplayed());

        logger.info("Test loginFieldIsCaseInsensitive finished");
        }

        @Test(dataProvider = "admAndNonExistingUser")
        @Step("delete user from DB")
        public void deleteUserFromDB(IUser admUser, IUser nonExistingUser){

        logger.info("Test deleteUserFromDB started");

        innerStep("Delete previously created test user from DB");

        DBUtils dbUtils = new DBUtils();
        String nonExistingLogin = nonExistingUser.getLoginname();
        if (dbUtils.verifyThatUserIsInDB(nonExistingLogin)) {
            dbUtils.deleteUsersFromDB(SQLQueries.DELETE_USER_BY_LOGIN.getQuery(),
                    nonExistingUser.getLoginname());
        }

        logger.info("Test deleteUserFromDB finished");
    }
    }
