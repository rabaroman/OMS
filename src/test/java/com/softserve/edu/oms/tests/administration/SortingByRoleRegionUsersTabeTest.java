package com.softserve.edu.oms.tests.administration;

import static org.testng.AssertJUnit.assertTrue;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.oms.data.ReadDataFromFile;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.pages.AdministrationPage;
import com.softserve.edu.oms.tests.TestRunner;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import ru.yandex.qatools.allure.model.SeverityLevel;

/**
 * The Class SortingByLoginRoleRegionUsersTabeTest.
 * 
 * @author Softserve Academy
 * @since 22.12.2016
 */
@Features("Administration")
@Stories("LVSETOMS-4 As Admin I want to see all existing users and perform user "
		+ "searching on the 'Administration' tab so I can manage them ")

public class SortingByRoleRegionUsersTabeTest extends TestRunner {

	/** The administration page. */
	AdministrationPage administrationPage;

	/**
	 * Login for tests. 
	 *
	 * @param adminUser
	 *            the admin user
	 */

	@BeforeMethod
	public void loginForTests() {
		administrationPage = loginPage.successAdminLogin(UserRepository.get().adminUser())
				.gotoAdministrationPage();
		administrationPage.showTenRows();
	}

	
	/**
	 * Sort table by login test.
	 *
	 * @param query            the query
	 * @throws InterruptedException 
	 */
	@TestCaseId("LVSETOMS-46")
	@Severity(SeverityLevel.NORMAL)
	@Description("This test case verifies that the records in the table on 'Administration' "
			+ "tab are sorted by default in the order of their creation and can be sorted by "
			+ "'Role', 'Region' in asc or desc order")
	@Step("Sorting of Users Table Test")
	@Test(dataProvider = "sortingTableDataProvider")
	
	public void sortUsersTableTest(String query) throws InterruptedException {

		String switchCond = null;
		Pattern p = Pattern.compile("\\w+\\W*(ASC|DESC)?\\W?$");
		Matcher m = p.matcher(query);
		
		while (m.find()) {
			switchCond = query.substring(m.start(), m.end())
					          .toUpperCase()
					          .replace(" ", "_");
		}
		switch (switchCond) {
		
		case "ROLEREF_ASC":
			administrationPage.sortByFirstNameASC();
			break;
		case "ROLEREF_DESC":
			administrationPage.sortByRoleDESC();
			break;
		case "REGIONREF_ASC":
			administrationPage.sortByRegionASC();
			break;
		case "REGIONREF_DESC":
			administrationPage.sortByRegionDESC();
			break;
		default:
			throw new RuntimeException("Invalid query in Test Data!");
		}
		assertTrue(administrationPage.compareLogins(query));
		
		
	}
	
    @Step("Getting data from file")
	@DataProvider(name = "sortingTableDataProvider")
	public static Iterator<Object[]> sortingTableDataProvider() {
		return ReadDataFromFile.readSortUsersTableTest("sortUsersTableTest").iterator();
	}
	
	@AfterMethod
	public void gotostartpage(){
		loginPage = administrationPage.logout();
	}

}
