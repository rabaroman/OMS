    package com.softserve.edu.oms.tests.administration;

    import com.softserve.edu.oms.data.DBUtils;
    import com.softserve.edu.oms.data.IUser;
    import com.softserve.edu.oms.data.UserRepository;
    import com.softserve.edu.oms.pages.AdministrationPage;
    import com.softserve.edu.oms.tests.TestRunner;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.testng.Assert;
    import org.testng.annotations.DataProvider;
    import org.testng.annotations.Test;
    import ru.yandex.qatools.allure.annotations.*;
    import ru.yandex.qatools.allure.model.SeverityLevel;

    import java.util.List;

    @Features("Administration")
    @Stories("LVSETOMS-4 As Admin I want to see all existing users "
            + "and perform user searching on the 'Administration' tab so I can manage them")
    public class CorrectUserInfoIsDisplayedTest extends TestRunner{

        public static final Logger logger = LoggerFactory.getLogger(CorrectUserInfoIsDisplayedTest.class);

        @DataProvider
        public Object[][] admUser() {
            return new Object[][] {
                    { UserRepository.get().adminUser() }
            };
        }

        /**
         * <h1>Correct info on Administation Page is displayed</h1>
         * This test goes to Administration Page
         * 1) reads the quantity of found users,
         * 2) executes the SQL query for finding the quantity of user from DB
         * 3) compares these two parameters
         * 4) then reads all rows from the table on page
         * 5) executes the SQL query to read the same from DB
         * 6) compares the two lists created in 4) and 5)
         *
         *<p>Note: the quantity of rows is 5 not 10 rows</p>
         *
         * Based on LVSETOMS-45 in Jira
         *
         * @author Viktoriia Bybel
         * @version 1.0
         * @since 15.12.16
         * @param admUser
         */
        @TestCaseId("LVSETOMS-45")
        @Severity(SeverityLevel.NORMAL)
        @Description("This test case verifies that all active registered users display in the table "
                + "on 'Administration' tab and that the users attributes matches the columns of the table.")
        @Test(dataProvider = "admUser")
        @Step("CorrectUserInfoIsDisplayedTest")
        public void correctUserInfoIsDisplayedTest(IUser admUser) {

            logger.info("Test correctUserInfoIsDisplayed started");

            innerStep("Log in and go to users.html page");
            AdministrationPage administrationPage = loginPage
                    .successAdminLogin(admUser)
                    .gotoAdministrationPage();

            innerStep("Read a number of found users from page");
            int numberOfFoundUsersFromPage = administrationPage
                    .getFoundUsersNumber();

            innerStep("Read a number of found users from DB");
            DBUtils dbUtils = new DBUtils();
            int numberOfFoundUsersFromDB = dbUtils.countAllUsers();

            innerStep("Verify that quantity users from page equal quantity users from DB");
            Assert.assertEquals(numberOfFoundUsersFromDB, numberOfFoundUsersFromPage);

            innerStep("Read all users data displayed on page");
            List<IUser> usersFromPage = administrationPage
                    .getUsersFromCurrentPage();

            innerStep("Read top 5 users from DB");
            List<IUser> usersFromDB = dbUtils.getTopFiveUsers();

            innerStep("Verify that users in DB are the same as users on page");
            for(int i = 0; i<usersFromPage.size();i++){
                Assert.assertTrue(usersFromDB.get(i).CompareTo(usersFromPage.get(i)));
            }

            logger.info("Test correctUserInfoIsDisplayedTest finished");
        }
    }
