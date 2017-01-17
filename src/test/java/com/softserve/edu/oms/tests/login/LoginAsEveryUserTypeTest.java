package com.softserve.edu.oms.tests.login;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.tests.TestRunner;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.model.SeverityLevel;


/**
 * This test verifies that one user of every
 * available user type:
 * -Administrator
 * -Customer
 * -Merchandiser
 * -Supervisor
 * can successfully log in the site
 *
 * Based on LVSETOMS-28 in Jira
 *
 * @author Anton Tokmakov
 * @since 16.12.16
 */
@Features("Authorization")
@Stories("LVSETOMS-1 As User Admin I want to login so I can enter the system and add new users to system")

public class LoginAsEveryUserTypeTest extends TestRunner {

    @DataProvider
    public Object[][] admUser() {
        return new Object[][] {
                { UserRepository.get().adminUser() }
        };
    }
    @DataProvider
    public Object[][] customerUser() {
        return new Object[][] {
                { UserRepository.get().customerUser() }
        };
    }
    @DataProvider
    public Object[][] merchandiserUser() {
        return new Object[][] {
                { UserRepository.get().merchandiserUser() }
        };
    }
    @DataProvider
    public Object[][] supervisorUser() {
        return new Object[][] {
                { UserRepository.get().supervisorUser() }
        };
    }
    
    @TestCaseId("LVSETOMS-28")
	@Severity(SeverityLevel.BLOCKER)
	@Description("This test case verifies that registered Admin user can successfully "
			+ "login to OMS by entering correct 'User' and 'Password'")
    @Test(dataProvider = "admUser")
    public void assertAdministratorLogin(IUser admUser) {
        Assert.assertEquals(loginPage.logout()
                .successAdminLogin(admUser)
                .getRoleText(), admUser.getRole());
    }

    @TestCaseId("LVSETOMS-28")
  	@Severity(SeverityLevel.BLOCKER)
  	@Description("This test case verifies that registered Customer user can successfully "
  			+ "login to OMS by entering correct 'User' and 'Password'")
    @Test(dataProvider = "customerUser")
    public void assertCustomerLogin(IUser customerUser) {
        Assert.assertEquals(loginPage.logout()
                .successCustomerLogin(customerUser)
                .getRoleText(), customerUser.getRole());
    }

    @TestCaseId("LVSETOMS-28")
  	@Severity(SeverityLevel.BLOCKER)
  	@Description("This test case verifies that registered Merchandiser user can successfully "
  			+ "login to OMS by entering correct 'User' and 'Password'")
    @Test(dataProvider = "merchandiserUser")
    public void assertMerchandiserLogin(IUser merchandiserUser) {
        Assert.assertEquals(loginPage.logout()
                .successMerchandiserLogin(merchandiserUser)
                .getRoleText(), merchandiserUser.getRole());
    }
    
    @TestCaseId("LVSETOMS-28")
  	@Severity(SeverityLevel.BLOCKER)
  	@Description("This test case verifies that registered Supervisor user can successfully "
  			+ "login to OMS by entering correct 'User' and 'Password'")
    @Test(dataProvider = "supervisorUser")
    public void assertSupervisorLogin(IUser supervisorUser) {
        Assert.assertEquals(loginPage.logout()
                .successSupervisorLogin(supervisorUser)
                .getRoleText(), supervisorUser.getRole());
    }





}
