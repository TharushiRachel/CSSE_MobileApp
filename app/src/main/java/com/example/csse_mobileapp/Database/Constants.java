package com.example.csse_mobileapp.Database;

import android.provider.BaseColumns;

public class Constants {

    public Constants() {
    }

    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }
}
