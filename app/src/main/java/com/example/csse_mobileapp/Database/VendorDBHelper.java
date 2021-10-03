package com.example.csse_mobileapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VendorDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Vendor.db";

    public VendorDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(VENDOR);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private static final String VENDOR =
            "CREATE TABLE "+ VendorProfile.Vendor.TABLE_NAME + " (" +
                    VendorProfile.Vendor._ID + " INTEGER PRIMARY KEY," +
                    VendorProfile.Vendor.COLUMN_1 + " TEXT," +
                    VendorProfile.Vendor.COLUMN_2 + " TEXT," +
                    VendorProfile.Vendor.COLUMN_3 + " TEXT," +
                    VendorProfile.Vendor.COLUMN_4+ " TEXT)";


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

}
