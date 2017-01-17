	package com.softserve.edu.oms.tests.administration;

	import com.softserve.edu.oms.data.IUser;
	import com.softserve.edu.oms.data.UserRepository;
	import com.softserve.edu.oms.tests.TestRunner;

	import ru.yandex.qatools.allure.annotations.Description;
	import ru.yandex.qatools.allure.annotations.Features;
	import ru.yandex.qatools.allure.annotations.Severity;
	import ru.yandex.qatools.allure.annotations.Stories;
	import ru.yandex.qatools.allure.annotations.TestCaseId;
	import ru.yandex.qatools.allure.model.SeverityLevel;

	import org.testng.Assert;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

	import static com.softserve.edu.oms.enums.SQLQueries.*;

	/**
	 * This test verifies that site performs different user sorts
	 * exactly like site database would do it using
	 * corresponding SQL queries
	 *
	 * Based on LVSETOMS-46 in Jira
	 *
	 * @author Anton Tokmakov
	 * @since 16.12.16
	 */
	@Features("Administration")
	@Stories("LVSETOMS-4 As Admin I want to see all existing users and perform "
			+ "user searching on the 'Administration' tab so I can manage them ")


	public class SortingByIDFirstNameLastNameTest extends TestRunner {

		@DataProvider
		public Object[][] admUser() {
			return new Object[][] {
					{ UserRepository.get().adminUser() }
			};
		}

		@TestCaseId("LVSETOMS-46")
		@Severity(SeverityLevel.NORMAL)
		@Description("This test case verifies that the records in the table on 'Administration' tab are sorted "
				+ "by default in the order of their creation and can be sorted by 'ID'"
				+ "  in asc  order")


		@Test(dataProvider = "admUser")
		public void assertSortById(IUser admUser) {
			Assert.assertTrue(loginPage.logout()
					.successAdminLogin(admUser)
					.gotoAdministrationPage()
					.showTenRows()
					.compareLogins(SORT_USERS_BY_ID_ASC.getQuery()));
		}

		@TestCaseId("LVSETOMS-46")
		@Severity(SeverityLevel.NORMAL)
		@Description("This test case verifies that the records in the table on 'Administration' tab are sorted "
				+ "by default in the order of their creation and can be sorted by 'FistName' "
				+ " in asc order")


		@Test(dataProvider = "admUser")
		public void assertSortByFirstNameASC(IUser admUser) {
			Assert.assertTrue(loginPage.logout()
					.successAdminLogin(admUser)
					.gotoAdministrationPage()
					.showTenRows()
					.sortByFirstNameASC()
					.compareLogins(SORT_USERS_BY_FIRSTNAME_ASC.getQuery()));
		}

		@TestCaseId("LVSETOMS-46")
		@Severity(SeverityLevel.NORMAL)
		@Description("This test case verifies that the records in the table on 'Administration' tab are sorted "
				+ "by default in the order of their creation and can be sorted by 'FistName' "
				+ " in  desc order")

		@Test(dataProvider = "admUser")
		public void assertSortByFirstNameDESC(IUser admUser) {
			Assert.assertTrue(loginPage.logout()
					.successAdminLogin(admUser)
					.gotoAdministrationPage()
					.showTenRows()
					.sortByFirstNameDESC()
					.compareLogins(SORT_USERS_BY_FIRSTNAME_DESC.getQuery()));
		}

		@TestCaseId("LVSETOMS-46")
		@Severity(SeverityLevel.NORMAL)
		@Description("This test case verifies that the records in the table on 'Administration' tab are sorted "
				+ "by default in the order of their creation and can be sorted by 'LastName' "
				+ " in asc order")

		@Test(dataProvider = "admUser")
		public void assertSortByLastNameASC(IUser admUser) {
			Assert.assertTrue(loginPage.logout()
					.successAdminLogin(admUser)
					.gotoAdministrationPage()
					.sortByLastNameASC()
					.compareLogins(SORT_USERS_BY_LASTNAME_ASC.getQuery()));
		}

		@TestCaseId("LVSETOMS-46")
		@Severity(SeverityLevel.NORMAL)
		@Description("This test case verifies that the records in the table on 'Administration' tab are sorted "
				+ "by default in the order of their creation and can be sorted by 'LastName' "
				+ "  in desc order")

		@Test(dataProvider = "admUser")
		public void assertSortByLastNameDESC(IUser admUser) {
			Assert.assertTrue(loginPage.logout()
					.successAdminLogin(admUser)
					.gotoAdministrationPage()
					.sortByLastNameDESC()
					.compareLogins(SORT_USERS_BY_LASTNAME_DESC.getQuery()));
		}
	}



