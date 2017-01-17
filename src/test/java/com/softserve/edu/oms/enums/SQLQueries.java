    package com.softserve.edu.oms.enums;

    /**
     * this class represents all SQL queries needed
     * for testing
     */
    public enum SQLQueries {
        GET_USER_BY_LOGIN("SELECT * FROM Users WHERE Users.Login=?"),
        GET_USER_BY_LOGIN_JOIN_ROLE("select Users.Login, Users.FirstName, Users.LastName,"
                + " Users.Password, Users.Email, Regions.RegionName, Roles.RoleName"
                + " from (Users join Regions on Users.RegionRef = Regions.ID)"
                + " join Roles on Users.RoleRef = Roles.ID"
                + " where IsUserActive=1 and Users.login=?"),
        GET_5_USERS_JOIN_ROLE("select Users.Login, Users.FirstName, Users.LastName, Users.Password,"
                + " Users.Email, Regions.RegionName, Roles.RoleName from (Users join Regions on Users.RegionRef = Regions.ID)"
                + " join Roles on Users.RoleRef = Roles.ID ORDER BY Users.ID LIMIT 5;"),
        GET_ALL_USERS_JOIN_ROLE("select Users.Login, Users.FirstName, Users.LastName,"
                + " Users.Password, Users.Email, Regions.RegionName, Roles.RoleName"
                + " from (Users join Regions on Users.RegionRef = Regions.ID)"
                + " join Roles on Users.RoleRef = Roles.ID;"),
        GET_LASTNAME_LIKE("SELECT LastName FROM Users WHERE IsUserActive=1 AND LastName LIKE ? "),
        GET_LOGIN_LIKE("SELECT Login FROM Users WHERE IsUserActive=1 AND Login LIKE ? "),
        GET_ROLE_NOT_LIKE("SELECT r.RoleName FROM Users as u JOIN Roles as r on u.RoleRef=r.ID WHERE u.IsUserActive=1 AND r.RoleName not like ? "),
        COUNT_ALL_USERS("SELECT COUNT(*) FROM Users WHERE IsUserActive=1"),
        DELETE_USER_BY_LOGIN("DELETE FROM Users WHERE Users.Login=?"),
        DELETE_USERS_BY_FIRSTNAME("DELETE FROM Users WHERE FirstName=?"),
        SORT_USERS_BY_ID_ASC("SELECT * FROM Users WHERE IsUserActive=1 ORDER BY ID"),
        SORT_USERS_BY_FIRSTNAME_ASC("SELECT * FROM Users WHERE IsUserActive=1 ORDER BY FirstName ASC"),
        SORT_USERS_BY_FIRSTNAME_DESC("SELECT * FROM Users WHERE IsUserActive=1 ORDER BY FirstName DESC"),
        SORT_USERS_BY_LASTNAME_ASC("SELECT * FROM Users WHERE IsUserActive=1 ORDER BY LastName ASC"),
        SORT_USERS_BY_LASTNAME_DESC("SELECT * FROM Users WHERE IsUserActive=1 ORDER BY LastName DESC");

        private String query;

        public String getQuery() { return query;}

        SQLQueries(String query) { this.query = query; }
    }