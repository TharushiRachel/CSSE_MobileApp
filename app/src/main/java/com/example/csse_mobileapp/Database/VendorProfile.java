package com.example.csse_mobileapp.Database;

import android.provider.BaseColumns;

public class VendorProfile {


    private VendorProfile() {
    }


    public static class Vendor implements BaseColumns {
        public static final String TABLE_NAME = "VendorDetails";
        public static final String COLUMN_1 = "vendorName";
        public static final String COLUMN_2 = "vendorNumber";
        public static final String COLUMN_3 = "vendorEmail";
        public static final String COLUMN_4 = "vendorAddress";
    }
}
