package com.softserve.edu.oms.tests.administration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.softserve.edu.oms.data.DBUtils;
import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.enums.Region;
import com.softserve.edu.oms.enums.Role;
import com.softserve.edu.oms.enums.SQLQueries;
import com.softserve.edu.oms.pages.AdminHomePage;
import com.softserve.edu.oms.pages.AdministrationPage;
import com.softserve.edu.oms.pages.CreateNewUserPage;
import com.softserve.edu.oms.tests.TestRunner;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.model.SeverityLevel;

/**
 * This test verifies that new user creation affects
 * 'Number of Found User' and pagination label on main
 * page of 'Administration' tab
 *
 * Based on LVSETOMS-53 in Jira
 *
 * @author Roman Raba
 * @since 16.12.16
 * @link http://ssu-jira.softserveinc.com/browse/LVSETOMS-53
 */

@Features("Administration")
@Stories("LVSETOMS-3 As Administrator I want to create new user so he/she can log into the application")

public class ChangeNumberOfUsersTest extends TestRunner{

    private AdministrationPage administrationPage;
    private int pagesCount;
    private int numberUsers;
    public static final Logger logger = LoggerFactory.getLogger(ChangeNumberOfUsersTest.class);
    
    @DataProvider
    public Object[][] validUser() {
        return new Object[][]{{UserRepository.get().userForDelete()},
        };
    }


    /**
     * Verify that number of active registered users is aliquot to 5.
     * And creates necessary number of users.
     */
   
    @BeforeMethod
    public void verifyAndCreateUsers() {
        int numberOfImems;
        IUser user = UserRepository.get().someUser();

        //Go to Administration page
        innerStep("Go to Administration page");
        AdminHomePage adminHomePage =
                loginPage.successAdminLogin(UserRepository.get().adminUser());
        administrationPage =
                adminHomePage.gotoAdministrationPage();

        //Total number of users.
        innerStep("Total number of users.");
        numberUsers = administrationPage.getFoundUsersNumber();

        //Number of pages. (One page can displays 5 or 10 users
        //which equivalent variable #numberOfImems)
        innerStep("Number of pages. (One page can displays 5 or 10 users "
        		+ "which equivalent variable #numberOfImems");
        pagesCount = administrationPage.getPagesQuantity();

        //Number of users which are shown in a table on an Administration page
        innerStep("Number of users which are shown in a table on an Administration page");
        numberOfImems = administrationPage.getUsersPerPageNumber();

        //Verify if number of active registered users is aliquot to 5.
        //And creating some users if necessary.
        innerStep("Verify if number of active registered users is aliquot to 5. "
        		+ "And creating some users if necessary.");
        if ((numberUsers % numberOfImems) != 0) {
            for (int i = 0; i < (numberOfImems - (numberUsers % numberOfImems)); i++) {
                CreateNewUserPage createNewUserPage =
                        administrationPage.gotoCreateNewUserPage();

                createNewUserPage.setLoginInput(user.getLoginname() + user.getLoginname().charAt(i)).                     
                        setFirstNameInput(user.getFirstname()).
                        setLastNameInput(user.getLastname()).
                        setPasswordInput(user.getPassword()).
                        setConfirmPasswordInput(user.getPassword()).
                        setEmailInput(user.getEmail()).
                        setSelectRegion(Region.getRegion(user.getRegion())).
                        setSelectRole(Role.valueOf(user.getRole().toUpperCase()));

                administrationPage = createNewUserPage.successCreateNewUser();
            }
            numberUsers = numberUsers + (numberOfImems - (numberUsers % numberOfImems));
        }
    }


    /**
     * Deletes users from DB after creation in test.
     */
    @AfterMethod
    public void deleteUsersFromDB() {
        DBUtils dbUtils = new DBUtils();
        dbUtils.deleteUsersFromDB(SQLQueries.DELETE_USERS_BY_FIRSTNAME.getQuery(),
                UserRepository.get().someUser().getFirstname());
    }


    /**
     * Verify that values in 'Number of found users' and 'Page#:' links
     * are properly updated after creation a new user.
     * @param user - user which should be created
     */
    @TestCaseId("LVSETOMS-53")
	@Severity(SeverityLevel.MINOR)
	@Description("This test case verifies that after new use creation the values in "
			+ "'Number of Found Users' and 'Page#:' links are properly updated.")
    @Test(dataProvider = "validUser")
    @Step("Verify changing number of pages and number of users after adding one user")
    public void verifyChangePageNumber(IUser user) {
        
        logger.info("Test verifyChangePageNumber start");

        int newNumberOfUsers;
        int newPagesCount;

        //Go to Creation New User page
        innerStep("Go to Creation New User page");
        CreateNewUserPage createNewUserPage =
                administrationPage.gotoCreateNewUserPage();

        //Creation new user.
        innerStep("Creation new user.");
        administrationPage = createNewUserPage.setLoginData(user)
                .successCreateNewUser();

        //Total number of users.
        innerStep("Total number of users.");
        newNumberOfUsers =  administrationPage.getFoundUsersNumber();

        //Number of pages. (One page can displays 5 or 10 users
        //which equivalent variable #numberOfImems)
        innerStep("Number of pages. (One page can displays 5 or 10 "
        		+ "userswhich equivalent variable #numberOfImems)");
        newPagesCount =  administrationPage.getPagesQuantity();

        //Verify if number of users and number of pages are changed
        //after creation a new user.
        innerStep("Verify if number of users and number of pages are changed after creation a new user.");
        Assert.assertEquals(numberUsers + 1, newNumberOfUsers);
        Assert.assertEquals(pagesCount + 1, newPagesCount);
        
        logger.info("Test verifyChangePageNumber done");
    }
}