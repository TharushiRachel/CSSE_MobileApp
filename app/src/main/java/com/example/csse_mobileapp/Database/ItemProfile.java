package com.example.csse_mobileapp.Database;

import android.provider.BaseColumns;

public class ItemProfile {

    private ItemProfile() {
    }


    public static class Item implements BaseColumns {
        public static final String TABLE_NAME = "ItemDetails";
        public static final String COLUMN_1 = "itemName";
        public static final String COLUMN_2 = "unitPrice";
        public static final String COLUMN_3 = "thresholdUnits";
        public static final String COLUMN_4 = "currentUnits";

    }
}
