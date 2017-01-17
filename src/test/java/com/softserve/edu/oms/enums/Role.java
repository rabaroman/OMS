    package com.softserve.edu.oms.enums;

    /**
     * this class represents all roles available on pages
     */
    public enum Role {
        ADMINISTRATOR("roleID1"),
        MERCHANDISER("roleID3"),
        SUPERVISOR("roleID4"),
        CUSTOMER("roleID2");

        private String roleId;

        Role(String roleId) {
            this.roleId = roleId;
        }

        public String getRoleId() {
            return roleId;
        }

    }
