package com.softserve.edu.oms.tests.login;

import com.softserve.edu.oms.tests.TestRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.softserve.edu.oms.enums.ErrorMessagesEnum.ERROR_MESSAGE;

/**
 * Test verifies that error message is shown,
 * when user try to login with empty "Login" and "Password" fields.
 *
 * Based on LVSETOMS-30 in Jira
 *
 * @author Anastasiia Maidanska
 * @version 1.0
 * @since 20.12.16
 */
@Features("Authorization")
@Stories("LVSETOMS-1 As User Admin I want to login so I can enter the system and add new users to system")

public  class LoginWithEmptyCredentialsTest extends TestRunner {

    public static final Logger logger = LoggerFactory.getLogger(LoginWithEmptyCredentialsTest.class);

    /**
     * Test verifies that error message is shown,
     * when user try to login with empty "Login" and "Password" fields.
     */
	@TestCaseId("LVSETOMS-30")
	@Severity(SeverityLevel.BLOCKER)
	@Description("This test case verifies that error message is shown when "
			+ "user tries to login with empty 'User' and 'Password' fields")
	

    @Test()
    @Step
    public void verifyErrorMessageWhenUserLoginWithEmptyCredentials(){
        logger.info("Test verifyErrorMessageWhenUserLoginWithEmptyCredentials start");

        // Click on 'Submit' button and get  error message
        innerStep("Click on 'Submit' button and get  error message");
        String currentErrorMessage = loginPage
                .loginWithEmptyCredentials()
                .getBadCredentialsErrorMessageText();

        // Verify that error message is correct
        innerStep("Verify that error message is correct");
        Assert.assertEquals(currentErrorMessage, ERROR_MESSAGE.message);

        logger.info("Test verifyErrorMessageWhenUserLoginWithEmptyCredentials done");
    }
}
