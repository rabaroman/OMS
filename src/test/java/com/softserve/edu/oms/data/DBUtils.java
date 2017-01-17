package com.softserve.edu.oms.data;

import com.softserve.edu.oms.enums.ErrorMessagesEnum;
import com.softserve.edu.oms.enums.SQLQueries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.allure.annotations.Step;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which is responsible for communication with database.
 *
 * @version 1.0
 * @since 15.12.16
 */
public class DBUtils {

    private String username = System.getenv("db_username");
    private String password = System.getenv("db_password");
    private String url = System.getenv("db_url");

    public static final Logger logger = LoggerFactory.getLogger(DBUtils.class);

    /**
     * Method, which creates connection with Database
     */
    private Connection createConnection() {
        Connection connection;

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessagesEnum.SQL_EXCEPTION_MESSAGE.message, e);
        }

        return connection;
    }

    /**
     * Method, which closes connection with Database
     *
     * @see DBUtils#closeConnection(Connection, Statement, ResultSet)
     */
    private void closeConnection(Connection connection, Statement statement, ResultSet resultSet) throws Exception {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * Method, which creates list of list string type from Database by sqlquery
     */
    public List<List<String>> getAllCells() {
        List<List<String>> allCells = new ArrayList<>();
        List<String> rowCells;
        Statement statement;
        ResultSet resultSet;
        int columnCount;
        Connection connection = createConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLQueries.GET_ALL_USERS_JOIN_ROLE.getQuery());
            columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                rowCells = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowCells.add(resultSet.getString(i));
                    logger.info("+++\t" + resultSet.getString(i) + "\t");
                }
                allCells.add(rowCells);
            }
            closeConnection(connection, statement, resultSet);
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessagesEnum.SQL_EXCEPTION_MESSAGE.message, e);
        }
        return allCells;
    }

    /**
     * Method, which deletes user or users from Database by SQL
     *
     * @param sqlQuery - query
     * @param value    - value which adds to sqlquery and responsibles to Login or FirstName etc.
     */
    public void deleteUsersFromDB(String sqlQuery, String value) {
        PreparedStatement statement;
        Connection connection = createConnection();

        try {
            statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, value);
            statement.execute();
            closeConnection(connection, statement, null);
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessagesEnum.SQL_EXCEPTION_MESSAGE.message, e);
        }
    }

    /**
     * Method, which gets list of string type from Database by sqlquery
     * which is responsible for one column from table.
     *
     * @param sqlQuery       - query
     * @param nameOfColumn   -name of column
     * @param searchingValue - parameter into sql query
     */
    public List<String> getOneColumn(String sqlQuery, String nameOfColumn, String searchingValue) {
        List<String> listOfOneColumn = new ArrayList<>();
        PreparedStatement statement;
        ResultSet resultSet = null;
        Connection connection = createConnection();
        try {
            statement = connection.prepareStatement(sqlQuery);
            switch (nameOfColumn) {
                case ("lastName"):
                    statement.setString(1, searchingValue + "%");
                    resultSet = statement.executeQuery();
                    break;
                case ("login"):
                    statement.setString(1, "%" + searchingValue + "%");
                    resultSet = statement.executeQuery();
                    break;
                case ("role"):
                    statement.setString(1, "%" + searchingValue + "%");
                    resultSet = statement.executeQuery();
                    break;
            }
            while (resultSet.next()) {
                listOfOneColumn.add(resultSet.getString(1));
            }
            closeConnection(connection, statement, resultSet);
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessagesEnum.SQL_EXCEPTION_MESSAGE.message, e);
        }
        return listOfOneColumn;
    }

    /**
     * Method, which gets user from Database by sqlquery.
     *
     * @param login - value of Login
     */
    public IUser getUserByLogin(String login) {
        User user = null;
        PreparedStatement statement;
        ResultSet resultSet;
        int columnCount;
        Connection connection = createConnection();

        try {
            statement = connection.prepareStatement(SQLQueries.GET_USER_BY_LOGIN_JOIN_ROLE.getQuery());
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            columnCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                user = new User("", "", "", "", "", "", "");
                for (int i = 1; i <= columnCount; i++) {
                    switch (i) {
                        case 1:
                            user.setLoginname(resultSet.getString(i));
                            break;
                        case 2:
                            user.setFirstname(resultSet.getString(i));
                            break;
                        case 3:
                            user.setLastname(resultSet.getString(i));
                            break;
                        case 4:
                            user.setPassword(resultSet.getString(i));
                            break;
                        case 5:
                            user.setEmail(resultSet.getString(i));
                            break;
                        case 6:
                            user.setRole(resultSet.getString(i));
                            break;
                        case 7:
                            user.setRegion(resultSet.getString(i));
                            break;
                    }
                }
            }
            closeConnection(connection, statement, resultSet);
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessagesEnum.SQL_EXCEPTION_MESSAGE.message, e);
        }
        return user;
    }


    /**
     * Method, which gets list of string type from Database by sqlquery
     * which is responsible for column Login from table.
     *
     * @param sqlQuery - query
     */
    public List<String> getLogins(String sqlQuery) {
        List<String> logins = new ArrayList<>();
        Statement statement;
        ResultSet resultSet;
        Connection con = createConnection();

        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                logins.add(resultSet.getString("Login"));
            }
            closeConnection(con, statement, resultSet);
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessagesEnum.SQL_EXCEPTION_MESSAGE.message, e);
        }
        return logins;
    }


    /**
     * Method, which gets number of users from Database by sqlquery.
     */
    @Step("Count all users in DB")
    public int countAllUsers() {

        Statement statement;
        ResultSet resultSet;
        int userCount = 0;

        Connection connection = createConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLQueries.COUNT_ALL_USERS.getQuery());
            resultSet.next();
            userCount = resultSet.getInt(1);
            closeConnection(connection, statement, resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userCount;
    }


    /**
     * Method, which gets list of first five users from Database by sqlquery.
     */
    @Step("Get first five users from DB")
    public List<IUser> getTopFiveUsers() {

        List<IUser> users = new ArrayList<>();
        Statement statement;
        ResultSet resultSet;

        Connection connection = createConnection();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLQueries.GET_5_USERS_JOIN_ROLE.getQuery());

            while (resultSet.next()) {
                IUser user = new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                );
                users.add(user);
            }
            closeConnection(connection, statement, resultSet);

        } catch (Exception e) {
            throw new RuntimeException(ErrorMessagesEnum.SQL_EXCEPTION_MESSAGE.message, e);
        }

        return users;
    }


    /**
     * Method, which verifies if the user is in Database by sqlquery.
     */
    @Step("Verification - is a user in DB")
    public boolean verifyThatUserIsInDB(String loginOfUser) {

        PreparedStatement statement;
        ResultSet resultSet;
        boolean userIsInDB = false;
        Connection connection = createConnection();

        try {
            statement = connection.prepareStatement(SQLQueries.GET_USER_BY_LOGIN.getQuery());
            statement.setString(1, loginOfUser);
            resultSet = statement.executeQuery();
            userIsInDB = resultSet.next();
            closeConnection(connection, statement, resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userIsInDB;
    }
}