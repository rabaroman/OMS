package com.softserve.edu.oms.tests.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Created by Oleh Lavrynenko on 13.12.2016.
 */
import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.pages.HomePage;
//import com.softserve.edu.oms.pages.UserInfoPage;
import com.softserve.edu.oms.pages.LoginPage;
import com.softserve.edu.oms.tests.TestRunner;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Features("Authorization")
@Stories("LVSETOMS-1 As User Admin I want to login so I can enter the system and add new users to system")

public class RememberMeTest extends TestRunner {

    /**
     * Verify that after check "Remember me" checkbox
     * entered login and password save in input fields
     * after logout
     *
     * @author Oleh Lavrynenko
     * @version 1.0
     * @since 16.12.16
     * @link http://ssu-jira.softserveinc.com/browse/LVSETOMS-38
     */
    public static final Logger logger = LoggerFactory.getLogger(RememberMeTest.class);
    @Features("Authorization")
    @Stories("LVSETOMS-1 As User Admin I want to login so I can enter the system and add new users to system")
    @TestCaseId("LVSETOMS-38")
  	@Severity(SeverityLevel.NORMAL)
  	@Description("This test case verifies that 'Login' form is filled by default with the values "
  		+ "in case previously succesfully logged in user did it with checked 'Remember me' option.")
    @Test
    public void rememberMeTest() {
       // private
        logger.info("Test rememberMeTest start");
        SoftAssert softAssert = new SoftAssert();

        IUser user= UserRepository.get().adminUser();
        // check if input fields are empty
        innerStep(" check if input fields are empty");
        softAssert.assertEquals(loginPage.getLoginnameInputText(), "");
        softAssert.assertEquals(loginPage.getPasswordInputText(), "");
        loginPage.clickGetRememberMeCheckbox();
        //log in as admin
        loginPage.successAdminLogin(user);

        HomePage homePage = new HomePage(driver);
        // verify if we log in successfully
        innerStep("verify log in was successful");
        softAssert.assertEquals(homePage.getFirstnameText(), user.getFirstname());
        softAssert.assertEquals(homePage.getLastnameText(), user.getLastname());

        // log out
        homePage.clickLogoutButton();
        loginPage = new LoginPage(driver);
        //verify if text values are saved in text inputs
        innerStep("verify if text values are saved in text inputs");
        softAssert.assertEquals(loginPage.getLoginnameInputText(),user.getLoginname());
        softAssert.assertEquals(loginPage.getPasswordInputText().length(),user.getPassword().length());
        softAssert.assertAll();
        logger.info("Test rememberMeTest done");
    }
}

