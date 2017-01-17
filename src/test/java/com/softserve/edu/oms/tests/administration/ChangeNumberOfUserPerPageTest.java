package com.softserve.edu.oms.tests.administration;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.enums.UsersPerPage;
import com.softserve.edu.oms.pages.AdministrationPage;
import com.softserve.edu.oms.tests.TestRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.model.SeverityLevel;

import static com.softserve.edu.oms.enums.LabelsNamesEnum.SHOW_10_ITEMS_LINK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;


    /**
     * Verify that correct number of records displays in a table on 'Administration' tab
     *
     * Based on LVSETOMS-43 in Jira
     *
     * @author Anastasiia Maidanska
     * @version 1.0
     * @since 20.12.16
     */
    @Features("Administration")
    @Stories("LVSETOMS-4 As Admin I want to see all existing users and perform "
    		+ "user searching on the 'Administration' tab so I can manage them ")
    public class ChangeNumberOfUserPerPageTest extends TestRunner{

        public static final Logger logger = LoggerFactory.getLogger(ChangeNumberOfUserPerPageTest.class);

        /**
         * Verify that correct number of records displays in a table on 'Administration' tab,
         * when user clicks on 'show 5 items' and 'show 10 items' links.
         */
    	@TestCaseId("LVSETOMS-43")
    	@Severity(SeverityLevel.MINOR)
    	@Description("This test case verifies that number of records displayed in a table"
    			+ " on 'Administration' tab and pagination correspond to the current settings "
    			+ "and is 5 by default. ")
        @Test
        @Step
        public void verifyChangeUserNumberPerPage() {

            logger.info("Test verifyChangeUserNumberPerPage start");

            // Get admin user from UserRepository
            innerStep("Get admin user from UserRepository");
    		IUser admin = UserRepository.get().adminUser();

            // Login and go to administration page
            innerStep("Login and go to administration page");
            AdministrationPage administrationPage = loginPage
                    .successAdminLogin(admin)
                    .gotoAdministrationPage();


            innerStep("Verification that 5 records are displayed in a table"); 
            // Verification that 5 records are displayed in a table
            Assert.assertEquals(administrationPage.getQuantityOfUsersPerPage(),
                    UsersPerPage.FIVE.getResultsPerPage());

            innerStep("Verification that 'Show 10 items' link is displayed");
            // Verification that 'Show 10 items' link is displayed
            assertTrue(administrationPage.showItemsLinkIsDisplayed());

            innerStep("Verification that 'Show 10 items' link has correct name");
            // Verification that 'Show 10 items' link has correct name
            Assert.assertEquals(administrationPage.getShowItemsLinkText(), SHOW_10_ITEMS_LINK.name);

            // Count number of pages
            double pagesNumber = administrationPage.getFoundUsersNumber() / (UsersPerPage.FIVE.getResultsPerPage() * 1.0);

            
            // Verification that correct number of pages displayed
            innerStep("Verification that correct number of pages displayed");
            Assert.assertEquals(administrationPage.getPagesQuantity(),(int)Math.ceil(pagesNumber));

            administrationPage.changeQuantityOfUsersPerPage();

            // Verification that 10 records are displayed in a table
            innerStep("Verification that 10 records are displayed in a table");
            Assert.assertEquals(administrationPage.getQuantityOfUsersPerPage() ,
                    UsersPerPage.TEN.getResultsPerPage());

            // Count number of pages
            innerStep("Count number of pages");
            pagesNumber = administrationPage.getFoundUsersNumber() / (UsersPerPage.TEN.getResultsPerPage() * 1.0);

            // Verification that correct number of pages displayed
            innerStep("Verification that correct number of pages displayed");
            Assert.assertEquals(administrationPage.getPagesQuantity(), (int)Math.ceil(pagesNumber));

            logger.info("Test verifyChangeUserNumberPerPage done");
        }
    }

