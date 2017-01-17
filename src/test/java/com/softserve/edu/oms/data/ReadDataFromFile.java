package com.softserve.edu.oms.data;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.testng.annotations.DataProvider;

import ru.yandex.qatools.allure.annotations.Step;

// TODO: Auto-generated Javadoc
/**
 * The Class ReadDataFromFile. To use current class in your tests you should: 
 * - in TestData.xls create necessary sheet with test data (users) 
 * - write a method that creates an object required for the test (readUsersData) 
 * - create dataprovider method which invokes method described above (getUsersFromXLS)
 *
 * @author Bohdan Harasym
 * @since 22.12.2016
 */
public class ReadDataFromFile {

	/** The table sheet. */
	private static HSSFSheet tableSheet;

	/** The table book. */
	private static HSSFWorkbook tableBook;

	/** The sheet cell. */
	private static HSSFCell sheetCell;
	// private static XSSFRow SheetRow;

	/** The data file name. */
	private static String dataFileName = "TestData.xls";

	/** The data file path. */
	private static String dataFilePath;

	/**
	 * Open data file.
	 *
	 * @param SheetName
	 *            the sheet name
	 * @throws Exception
	 *             the exception
	 */
	private static void openDataFile(String SheetName) throws Exception {
		FileInputStream TableFile = new FileInputStream(getTestDataFilePath());
		tableBook = new HSSFWorkbook(TableFile);
		tableSheet = tableBook.getSheet(SheetName);
	}

	/**
	 * Gets the specific cell data.
	 *
	 * @param Row
	 *            the row
	 * @param Column
	 *            the column
	 * @return the specific cell data
	 * @throws Exception
	 *             the exception
	 */
	public static String getSpecifficCellData(int Row, int Column) throws Exception {

		sheetCell = tableSheet.getRow(Row).getCell(Column);
		String CellData = sheetCell.getStringCellValue();
		return CellData;
	}

	/**
	 * Gets the test data file path. Do not change this method.
	 *
	 * @return the test data file path
	 */
	private static String getTestDataFilePath() {
		dataFilePath = ReadDataFromFile.class.getResource("/" + dataFileName).getPath();
		System.out.println(dataFilePath);
		return dataFilePath;
	}

	/**
	 * Read all test data from sheet. This is main method of current class which
	 * read all filled cells in sheet, Do not change this method.
	 * 
	 * @param sheetName
	 *            the sheet name
	 * @return this method is returning List of Objects
	 */
	public static List<Object[]> readAllTestDataFromSheet(String sheetName) {
		Cell cell;
		List<Object[]> testDataList = new ArrayList<Object[]>();

		try {
			openDataFile(sheetName);
			Iterator<Row> rowIterator = tableSheet.iterator();
			List<String> cellsInRowData;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// get row
				Iterator<Cell> cellIterator = row.cellIterator();
				cellsInRowData = new ArrayList<>();
				while (cellIterator.hasNext()) {
					cell = cellIterator.next();
					// get cell format
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						cellsInRowData.add(String.valueOf(cell.getNumericCellValue()));
						break;
					case Cell.CELL_TYPE_STRING:
						cellsInRowData.add(cell.getStringCellValue());
						break;
					}
				}
				testDataList.add(cellsInRowData.toArray());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testDataList;
	}

	/**
	 * Read users data. Example how to use current class this method. create
	 * User entity by taking data from selected in your DataProvider sheet name
	 * 
	 * @param sheetName
	 *            the sheet name
	 * @return the list of User
	 */
	private static List<User> readUsersData(String sheetName) {
		List<Object[]> users = readAllTestDataFromSheet(sheetName);
		List<User> listOfUsers = new ArrayList<User>();
		for (Object[] userField : users) {
			User user = new User();
			user.setFirstname(userField[0].toString());
			user.setLastname(userField[1].toString());
			user.setPassword(userField[2].toString());
			user.setLoginname(userField[3].toString());
			user.setEmail(userField[4].toString());
			user.setRegion(userField[5].toString());
			user.setRole(userField[6].toString());
			listOfUsers.add(user);
		}
		return listOfUsers;
	}

	/**
	 * Read some data from sheet. example. the sheet should have two filled
	 * cells in each row
	 * 
	 * @param sheetName
	 *            the sheet name
	 * @return the list
	 */
	@Step("Read queries from file")
	public static List<Object[]> readSortUsersTableTest(String sheetName) {
		List<Object[]> someDataList = readAllTestDataFromSheet(sheetName);
		return someDataList;
	}
	
	
	/**
	 * Read invalis passw user test.
	 *
	 * @param sheetName the sheet name
	 * @return the list
	 */
	@Step("Read queries from file")
	public static List<Object[]> readInvalisPasswUserTest(String sheetName) {
		List<Object[]> someDataList = readAllTestDataFromSheet(sheetName);
		return someDataList;
	}

	/**
	 * Gets the some data from sheet. it is an example of dataprovider which
	 * returns two strings to test
	 * 
	 * @return the some data from sheet
	 */
	@DataProvider(name = "getSomeDataFromSheet")
	public static Iterator<Object[]> getSomeDataFromSheet() {
		return readSortUsersTableTest("example").iterator();
	}

	/**
	 * Get the users data provider. Example how to use current class
	 * 
	 * @return the iterator
	 */
	@DataProvider(name = "getUsersFromXLSDataProvider")
	public static Iterator<User> getUsersFromXLS() {
		return readUsersData("userData").iterator();
	}

	/**
	 * The main method was created to test current class.
	 * Ignore it in your work.
	 *
	 * @param args
	 *            the arguments
	 */

	public static void main(String[] args) {
		Iterator<User> userIter = getUsersFromXLS();
		while (userIter.hasNext()) {
			User user = userIter.next();
			System.out.println(user.getEmail());

		}

	}

	}