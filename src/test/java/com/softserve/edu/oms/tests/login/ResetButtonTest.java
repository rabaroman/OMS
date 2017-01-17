package com.softserve.edu.oms.tests.login;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.tests.TestRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * This test verifies that entered user credentials values
 * are cleared by clicking on 'Reset' button
 *
 * Based on LVSETOMS-37 in Jira
 *
 * @author Iryna Kyselchuk
 * @since 15.12.16
 */
@Features("Authorization")
@Stories("LVSETOMS-1 As User Admin I want to login so I can enter the system and add new users to system")

public class ResetButtonTest extends TestRunner {

    public static final Logger logger = LoggerFactory.getLogger(ResetButtonTest.class);
    /**
     * Provides data for user login
     *
     * @return user from UserRepository
     */
    @DataProvider
    public Object[][] someUser() {
        return new Object[][]{
                {UserRepository
                        .get()
                        .invalidUser()}
        };
    }

    /**
     * This method verifies that after
     * entering user credentials in 'User' and 'Password' fields
     * and clicking 'Reset' button
     * both fields are empty
     *
     * @param someUser {@link com.softserve.edu.oms.data.UserRepository}
     */
    @TestCaseId("LVSETOMS-37")
   	@Severity(SeverityLevel.NORMAL)
   	@Description("This test case verifies that entered by user values in 'User'"
   			+ " and 'Password' fields are cleared by clicking on 'Reset' button")
    @Test(dataProvider = "someUser")
    @Step("Verify 'Reset' button functionality")
    public void verifyResetButtonFunctionality(IUser someUser) {

        logger.info("Test verifyResetButtonFunctionality start");
        loginPage.setLoginDataAndReset(someUser);
        innerStep("Verify that 'User' field is cleared by clicking on 'Reset' button");
        Assert.assertTrue(loginPage
                .getLoginnameInputText()
                .isEmpty());
        innerStep("Verify that 'Password' field is cleared by clicking on 'Reset' button");
        Assert.assertTrue(loginPage
                .getPasswordInputText()
                .isEmpty());
        logger.info("Test verifyResetButtonFunctionality done");
    }
}


