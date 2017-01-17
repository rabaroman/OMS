    package com.softserve.edu.oms.tests.userinfo;

    import com.softserve.edu.oms.data.IUser;
    import com.softserve.edu.oms.data.UserRepository;
    import com.softserve.edu.oms.pages.AdminHomePage;
    import com.softserve.edu.oms.tests.TestRunner;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.testng.Assert;
    import org.testng.annotations.DataProvider;
    import org.testng.annotations.Test;
    import ru.yandex.qatools.allure.annotations.*;
    import ru.yandex.qatools.allure.model.SeverityLevel;

    @Features("Administration")
    @Stories("LVSETOMS-2 As an Admin I want to see 'Administration' "
    		+ "and 'User Info' tab after login so I can create & manage users")

    public class SwitchingTabsFunctionalityTest extends TestRunner {

        public static final Logger logger = LoggerFactory.getLogger(SwitchingTabsFunctionalityTest.class);

    @DataProvider
    public Object[][] admUser() {
        return new Object[][] {
            { UserRepository.get().adminUser() }
        };
    }
    /**
    * <h1>Switching tabs on UserInfo page for Admin user</h1>
    * This test validates that Admin has an ability to switch between
    * tabs on UserInfo page
    *
    * @author Viktoriia Bybel
     * @version 1.0
     * @since 15.12.16
     * @param admUser
     */
    @TestCaseId("LVSETOMS-41")
 	@Severity(SeverityLevel.MINOR)
 	@Description("This test case verifies that user  with 'Administrator' role sees 'User Info'"
 			+ " (opened by default) and 'Administration' tab after login and can switch between them")

    @Test(dataProvider = "admUser")
    @Step("TabSwitchingTest")
    public void tabSwitchingTest(IUser admUser) {

        logger.info("Test tabSwitching started");

        innerStep("Log in as Administrator");
        AdminHomePage admHomeUserPage = loginPage.successAdminLogin(admUser);

        innerStep("Verify that 'User Info' tab is active");
        Assert.assertTrue(admHomeUserPage
                .getUserInfoTab()
                .isEnabled());

        innerStep("Verify that 'Administration' tab is present");
        Assert.assertTrue(admHomeUserPage
                .getAdministrationTab()
                .isDisplayed());

        innerStep("Go to Admin page");
        admHomeUserPage
                .gotoAdministrationPage();

        innerStep("Verify that we are at Admin page");
        Assert.assertTrue((driver.getCurrentUrl()).contains("users.htm"));

        innerStep("Return to User Info page");
        admHomeUserPage
                .clickUserInfoTab();

        innerStep("Verify that we are at User Info page");
        Assert.assertTrue((driver.getCurrentUrl()).contains("userInfo.htm"));

        logger.info("Test tabSwitching finished");
    }
    }
