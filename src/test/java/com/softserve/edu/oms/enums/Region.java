    package com.softserve.edu.oms.enums;

    /**
     * this class represents all regions from which our
     * users can be
     */
    public enum Region {
        NORTH("North"),
        EAST("East"),
        SOUTH("South"),
        WEST("West");

        private String region;

        Region(String region) {
            this.region = region;
        }

        public String getRegionType() {
            return region;
        }
        public static Region getRegion(String regionString) {
            for (final Region regionEnum : Region.values()) {
                if (regionEnum.getRegionType().trim().equalsIgnoreCase(regionString)) {
                    return regionEnum;
                }
            }

            return null;
        }
    }
