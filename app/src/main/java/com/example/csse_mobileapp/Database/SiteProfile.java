package com.example.csse_mobileapp.Database;

import android.provider.BaseColumns;

public final class SiteProfile {

    private SiteProfile() {
    }


    public static class Site implements BaseColumns {
        public static final String TABLE_NAME = "SiteDetails";
        public static final String COLUMN_1 = "siteManagerName";
        public static final String COLUMN_2 = "department";
        public static final String COLUMN_3 = "vendorType";
        public static final String COLUMN_4 = "deliveryDate";
        public static final String COLUMN_5 = "deliveryLocation";
        public static final String COLUMN_6 = "status";
        public static final String COLUMN_7 = "orderDate";
        public static final String COLUMN_8 = "number";
        public static final String COLUMN_9 = "email";
    }
}
