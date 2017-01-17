package com.softserve.edu.oms.tests.login;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.enums.ErrorMessagesEnum;
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
 * Test verifies that error messages is shown when user tries to login without
 * being registered in the system
 *
 * Based on LVSETOMS-29 in Jira
 *
 * @author Dmytro Voropai
 */
@Features("Authorization")
@Stories("LVSETOMS-1 As User Admin I want to login so I can enter the system and add new users to system")

public class ResetButtonErrorMessagesTest extends TestRunner {

//  Provides not register user login and password
    @DataProvider
    public Object[][] invalidUser() {
        return new Object[][] {
                {UserRepository.get().invalidUser()}
        };
    }
    @TestCaseId("LVSETOMS-29")
  	@Severity(SeverityLevel.CRITICAL)
  	@Description("This test case verifies that error message is shown "
  			+ "when user tries to login without being registered to OMS.")
    @Test(dataProvider = "invalidUser", alwaysRun = true)
    @Step("Error messages verification for non registered user")
    
    public void verifyResetButtonErrorMessagesForNonRegisteredUser(IUser invalidUser){
//      Check if Object of String error message is not null.
        Assert.assertNotNull(loginPage.unsuccessfulLogin(invalidUser)
                .getBadCredentialsErrorMessageText());
//      Check if error message is the same as was expected
        Assert.assertEquals(loginPage.unsuccessfulLogin(invalidUser)
                .getBadCredentialsErrorMessageText(), ErrorMessagesEnum.EXPECTED_ERROR_MESSAGE_TC29.message);
    }
}
