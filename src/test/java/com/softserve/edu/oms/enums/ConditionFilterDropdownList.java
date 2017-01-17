    package com.softserve.edu.oms.enums;

    /**
     * This enum class represents all conditions needed for
     * search filter on Administration Page
     */
    public enum ConditionFilterDropdownList {
        EQUALS("equals"),
        NOT_EQUAL_TO("not equal to"),
        START_WITH("starts with"),
        CONTAINS("contains"),
        DOES_NOT_CONTAIN("does not contain");

        private String nameOfConditionFilterField;

        ConditionFilterDropdownList(String nameOfConditionFilterField) {
            this.nameOfConditionFilterField = nameOfConditionFilterField;
        }

        public String getNameOfConditionFilterField() {
            return nameOfConditionFilterField;
        }
    }
