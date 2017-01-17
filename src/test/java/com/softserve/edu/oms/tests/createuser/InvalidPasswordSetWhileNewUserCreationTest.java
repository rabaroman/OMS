package com.softserve.edu.oms.tests.createuser;

import static org.testng.AssertJUnit.*;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.softserve.edu.oms.data.ReadDataFromFile;
import com.softserve.edu.oms.data.UserRepository;
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
 * The Class InvalidPasswordSetWhileNewUserCreationTest.
 * 
 * @author Softserve Academy
 * @since 24.12.2016
 */

@Features("Create New User")
@Stories("LVSETOMS-3 As Administrator I want to create new user so he can log into the application")

public class InvalidPasswordSetWhileNewUserCreationTest extends TestRunner{
	
	/**
	 * Sets the invalid password test.
	 *
	 * @param login the login
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param passw the passw
	 * @param email the email
	 * @throws InterruptedException the interrupted exception
	 */
	@TestCaseId("LVSETOMS-52")
	@Severity(SeverityLevel.NORMAL)
    @Description("Verify that error message is shown when creating new user "
    		+ "with too short password and incorrect email")
	@Step("Set Invalid Password Into Create New User Table")
	@Test(dataProvider="setInvalidPasswordDataProvider")
	public void setInvalidPasswordWhileCreateNewUserTest(String login, String firstName, 
			String lastName, String passw, String email) {
		
		CreateNewUserPage createNewUserPage = loginPage
                .successAdminLogin(UserRepository
                		.get()
                		.adminUser())
                .gotoAdministrationPage()
                .gotoCreateNewUserPage()
                .setLoginInput(login)
                .setFirstNameInput(firstName)
                .setLastNameInput(lastName)
                .setPasswordInput(passw)
                .setConfirmPasswordInput(passw)
                .setEmailInput(email);
		
		assertTrue(createNewUserPage.isPasswordErrorDisplayed());
		
		
	}
	
	/**
	 * Sets the invalid password data provider.
	 *
	 * @return the iterator
	 */
	@Step("Getting data from file")
	@DataProvider(name = "setInvalidPasswordDataProvider")
	public static Iterator<Object[]> setInvalidPasswordDataProvider() {
		return ReadDataFromFile.readSortUsersTableTest("InvalidPasswUserTest").iterator();
	}

}
