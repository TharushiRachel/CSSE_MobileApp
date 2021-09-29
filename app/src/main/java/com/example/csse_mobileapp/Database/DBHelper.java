package com.example.csse_mobileapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Purchase.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + SiteProfile.Site.TABLE_NAME + " (" +
                    SiteProfile.Site._ID + " INTEGER PRIMARY KEY," +
                    SiteProfile.Site.COLUMN_1 + " TEXT," +
                    SiteProfile.Site.COLUMN_2 + " TEXT," +
                    SiteProfile.Site.COLUMN_3 + " TEXT," +
                    SiteProfile.Site.COLUMN_4 + " TEXT," +
                    SiteProfile.Site.COLUMN_5 + " TEXT," +
                    SiteProfile.Site.COLUMN_6 + " TEXT," +
                    SiteProfile.Site.COLUMN_7 + " TEXT," +
                    SiteProfile.Site.COLUMN_8 + " TEXT," +
                    SiteProfile.Site.COLUMN_9 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + SiteProfile.Site.TABLE_NAME;

    public long addSiteInfo(String siteManagerName, String department, String vendorType, String deliveryDate,
                            String deliveryLocation, String status, String orderDate, String number, String email) {

        SQLiteDatabase db = getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(SiteProfile.Site.COLUMN_1, siteManagerName);
        values.put(SiteProfile.Site.COLUMN_2, department);
        values.put(SiteProfile.Site.COLUMN_3, vendorType);
        values.put(SiteProfile.Site.COLUMN_4, deliveryDate);
        values.put(SiteProfile.Site.COLUMN_5, deliveryLocation);
        values.put(SiteProfile.Site.COLUMN_6, status);
        values.put(SiteProfile.Site.COLUMN_7, orderDate);
        values.put(SiteProfile.Site.COLUMN_8, number);
        values.put(SiteProfile.Site.COLUMN_9, email);


        long newRowId = db.insert(SiteProfile.Site.TABLE_NAME, null, values);

        return newRowId;
    }
}
