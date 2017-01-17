    package com.softserve.edu.oms.enums;

    /**
     * This enum class represents all search fields needed for
     * search filter on Administration Page
     */
    public enum FieldFilterDropdownList {
        ALL_COLUMNS("All columns"),
        FIRST_NAME("First Name"),
        LAST_NAME("Last Name"),
        ROLE("Role"),
        LOGIN("Login Name");

        private String fieldname;

        FieldFilterDropdownList(String fieldname) {
            this.fieldname = fieldname;
        }

        public String getFieldName() {
            return fieldname;
        }
    }
