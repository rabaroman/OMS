package com.softserve.edu.oms.tests.login;

import static org.testng.AssertJUnit.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.pages.CreateNewUserPage;
import com.softserve.edu.oms.tests.TestRunner;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.model.SeverityLevel;

/**
 * The Class AdminUserSmokeTest.
 * 
 * @author Softserve Academy
 * @since 22.12.2016
 */
@Features("Authorization")
@Stories("As User Admin I want to login so I can enter the system and add new users to system")

public class AdminUserSmokeTest extends TestRunner {

	/**
	 * Gets the admin user DataProvider for adminUserSmokeTest
	 *
	 * @return the admin user
	 */
	@DataProvider
	public Object[][] getAdminUser() {
		return new Object[][] { { UserRepository.get().adminUser() } };
	}

	/**
	 * Admin user smoke test. This test verifies that 'Create new user'
	 * button is available.
	 *
	 */
	@TestCaseId("LVSETOMS-28")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Checking if 'Create New User' Button is Enabled")
	@Test(dataProvider="getAdminUser")
	public void adminUserSmokeTest(IUser adminUser) {
		
		innerStep("Creating chain of steps to create new user page ");
		
		CreateNewUserPage createNewUserPage = loginPage
				.successAdminLogin(adminUser)
				.gotoAdministrationPage()
				.gotoCreateNewUserPage();
		
		innerStep("Testing inside methods steps");
		
		assertTrue(createNewUserPage.getCreateButton().isEnabled());

	}

}
