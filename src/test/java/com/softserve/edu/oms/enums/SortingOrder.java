    package com.softserve.edu.oms.enums;

    /**
     * This class represents all available sorting orders
     */
    public enum SortingOrder {
        ASCENDING("ASC"),
        DESCENDING("DESC");

        private String sortingOrderInColumn;

        SortingOrder(String sortingOrderInColumn) {
            this.sortingOrderInColumn = sortingOrderInColumn;
        }

        public String getSortingOrderInColumn() {
            return sortingOrderInColumn;
        }


    }
