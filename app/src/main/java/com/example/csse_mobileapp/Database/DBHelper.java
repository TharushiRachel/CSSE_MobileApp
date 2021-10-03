package com.example.csse_mobileapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Purchase.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

//        String SQL_CREATE_ENTRIES =
//                "CREATE TABLE " + SiteProfile.Site.TABLE_NAME + " (" +
//                        SiteProfile.Site._ID + " INTEGER PRIMARY KEY," +
//                        SiteProfile.Site.COLUMN_1 + " TEXT," +
//                        SiteProfile.Site.COLUMN_2 + " TEXT," +
//                        SiteProfile.Site.COLUMN_3 + " TEXT," +
//                        SiteProfile.Site.COLUMN_4 + " TEXT," +
//                        SiteProfile.Site.COLUMN_5 + " TEXT," +
//                        SiteProfile.Site.COLUMN_6 + " TEXT," +
//                        SiteProfile.Site.COLUMN_7 + " TEXT," +
//                        SiteProfile.Site.COLUMN_8 + " TEXT," +
//                        SiteProfile.Site.COLUMN_9 + " TEXT)" ;
//                        SiteProfile.Site.COLUMN_10 +" TEXT)";
        db.execSQL(SQL_CREATE_ENTRIES);


        db.execSQL(SQL_CREATE_ENTRIES2);
    }



    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//        db.execSQL("CREATE TABLE  " + SQL_CREATE_ENTRIES + " (ID TEXT PRIMARY KEY, FIRSTNAME TEXT , LASTNAME TEXT,CONTACT TEXT, VEHICLE TEXT)");

//        db.execSQL(SQL_DELETE_ENTRIES);
//        onCreate(db);




//        switch (oldVersion) {
//            case 1:
//                db.execSQL(SQL_CREATE_ENTRIES);
//
//            case 2:
//                db.execSQL("ALTER TABLE SiteDetails ADD COLUMN poNumber TEXT");
//        }
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
                    SiteProfile.Site.COLUMN_9 + " TEXT)" ;
//                    SiteProfile.Site.COLUMN_10 +" TEXT)";


    private static final String SQL_CREATE_ENTRIES2 =
            "CREATE TABLE "+ VendorProfile.Vendor.TABLE_NAME + " (" +
                    VendorProfile.Vendor._ID + " INTEGER PRIMARY KEY," +
                    VendorProfile.Vendor.COLUMN_1 + " TEXT," +
                    VendorProfile.Vendor.COLUMN_2 + " TEXT," +
                    VendorProfile.Vendor.COLUMN_3 + " TEXT," +
                    VendorProfile.Vendor.COLUMN_4+ " TEXT)";



    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + SiteProfile.Site.TABLE_NAME;

//    public long addSiteInfo(String siteManagerName, String department, String vendorType, String deliveryDate,
//                            String deliveryLocation, String status, String orderDate, String number, String email, String poNumber) {
//
//        SQLiteDatabase db = getWritableDatabase();
//
//
//        ContentValues values = new ContentValues();
//        values.put(SiteProfile.Site.COLUMN_1, siteManagerName);
//        values.put(SiteProfile.Site.COLUMN_2, department);
//        values.put(SiteProfile.Site.COLUMN_3, vendorType);
//        values.put(SiteProfile.Site.COLUMN_4, deliveryDate);
//        values.put(SiteProfile.Site.COLUMN_5, deliveryLocation);
//        values.put(SiteProfile.Site.COLUMN_6, status);
//        values.put(SiteProfile.Site.COLUMN_7, orderDate);
//        values.put(SiteProfile.Site.COLUMN_8, number);
//        values.put(SiteProfile.Site.COLUMN_9, email);
//        values.put(SiteProfile.Site.COLUMN_10,poNumber);
//
//
//        long newRowId = db.insert(SiteProfile.Site.TABLE_NAME, null, values);
//
//        return newRowId;
//    }

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


    public long addVendorInfo(String vendorName, String vendorNumber, String vendorEmail, String vendorAddress){

        SQLiteDatabase db = getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(VendorProfile.Vendor.COLUMN_1, vendorName);
        values.put(VendorProfile.Vendor.COLUMN_2, vendorNumber);
        values.put(VendorProfile.Vendor.COLUMN_3, vendorEmail);
        values.put(VendorProfile.Vendor.COLUMN_4, vendorAddress);

        long newVendorId = db.insert(VendorProfile.Vendor.TABLE_NAME, null, values);

        return newVendorId;
    }


    public List readSiteInfo(String siteManagerName){

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                SiteProfile.Site.COLUMN_1,
                SiteProfile.Site.COLUMN_8,
                SiteProfile.Site.COLUMN_9,
//                SiteProfile.Site.COLUMN_10
        };

        String selection = SiteProfile.Site.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { siteManagerName };

        String sortOrder =
                SiteProfile.Site.COLUMN_1+ " ASC";

        Cursor cursor = db.query(
                SiteProfile.Site.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List siteInfo = new ArrayList<>();
        while (cursor.moveToNext()){
            String site_manager_name = cursor.getString(cursor.getColumnIndexOrThrow(SiteProfile.Site.COLUMN_1));
            String site_number= cursor.getString(cursor.getColumnIndexOrThrow(SiteProfile.Site.COLUMN_8));
            String site_email= cursor.getString(cursor.getColumnIndexOrThrow(SiteProfile.Site.COLUMN_9));
//            String po_number= cursor.getString(cursor.getColumnIndexOrThrow(SiteProfile.Site.COLUMN_10));

            siteInfo.add(site_manager_name);
            siteInfo.add(site_number);
            siteInfo.add(site_email);
//            siteInfo.add(po_number);
        }

        cursor.close();
        return siteInfo;
    }
}
