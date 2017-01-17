    package com.softserve.edu.oms.enums;

    /**
     * This enum class represents all error messages
     * which can appear on different pages
     */
    public enum ErrorMessagesEnum {
        ERROR_MESSAGE("Your login attempt was not successful, try again.\n" + "\n" + "Reason: Bad credentials."),
        FIRST_NAME_ERROR_MESSAGE("First name cannot contain digits"),
        LAST_NAME_ERROR_MESSAGE("Last name cannot contain digits"),
        CONFIRM_PASSWORD_ERROR_MESSAGE("Confirm password has to be equal to password"),
        SQL_EXCEPTION_MESSAGE ("SQL Exception found"),

        //   Expected results for test case 29
        EXPECTED_ERROR_MESSAGE_TC29("Such user does not exist in the system - please try again"),

        //   Expected results for test case 49
        EXPECTED_ERROR_MESSAGE_FOR_lOGIN_TC49("Login name cannot be blank"),
        EXPECTED_ERROR_MESSAGE_FOR_FIRST_NAME_TC49("First name cannot be blank"),
        EXPECTED_ERROR_MESSAGE_FOR_LAST_NAME_TC49("Last name cannot be blank"),
        EXPECTED_ERROR_MESSAGE_FOR_PASSWORD_TC49("Password cannot be shorter than 4 and longer than 10 characters"),
        EXPECTED_ERROR_MESSAGE_FOR_EMAIL_ADDRESS_TC49("Incorrect format of Email Address"),

        //    Expected results for test case 51
        EXPECTED_ERROR_MESSAGE_FOR_lOGIN_TC51("Login name is too long"),
        EXPECTED_ERROR_MESSAGE_FOR_FIRST_NAME_TC51("First name is too long"),
        EXPECTED_ERROR_MESSAGE_FOR_LAST_NAME_TC51("Last name is too long"),
        EXPECTED_ERROR_MESSAGE_FOR_PASSWORD_TC51 ("Password cannot be shorter than 4 and longer than 10 characters");

        public final String message;

        ErrorMessagesEnum (final String message){
            this.message = message;
        }
    }
