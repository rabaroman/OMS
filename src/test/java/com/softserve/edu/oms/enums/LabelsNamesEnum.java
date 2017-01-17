    package com.softserve.edu.oms.enums;

    /**
     * this class represents all labels present on pages
     */
    public enum LabelsNamesEnum {
        SHOW_10_ITEMS_LINK ("Show 10 items"),
        SEARCH_TEXT_NONE ("none"),
        SEARCH_TEXT_ER ("er"),
        BY_LAST_NAME ("lastName"),
        BY_LOGIN_NAME ("login"),
        BY_ROLE ("role"),
        TOO_LONG_NAME("zxcvbnm asdfghjk qwertyuio pxmfjfn jvnvkh");
        public final String name;

        LabelsNamesEnum (String name){
             this.name = name;
         }
    }
