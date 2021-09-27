package com.example.csse_mobileapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UserInfo.db";
    private static final String TAG = "DBHelper" ;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Constants.Users.TABLE_NAME + " ("+
                        Constants.Users._ID + " INTEGER PRIMARY KEY," +
                        Constants.Users.COLUMN_NAME_EMAIL + " TEXT,"+
                        Constants.Users.COLUMN_NAME_PASSWORD +  " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long addInfo(String email, String password){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.Users.COLUMN_NAME_EMAIL,email);
        values.put(Constants.Users.COLUMN_NAME_PASSWORD,password);

        long newRowId = db.insert(Constants.Users.TABLE_NAME,null,values);
        return newRowId;
    }

    public List readAllInfo(String req){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                Constants.Users._ID,
                Constants.Users.COLUMN_NAME_EMAIL,
                Constants.Users.COLUMN_NAME_PASSWORD
        };

        String sortOrder = Constants.Users.COLUMN_NAME_EMAIL + " DESC";

        Cursor cursor = db.query(
                Constants.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List emails = new ArrayList<>();
        List passwords = new ArrayList<>();

        while (cursor.moveToNext()){
            String email = cursor.getString(cursor.getColumnIndexOrThrow(Constants.Users.COLUMN_NAME_EMAIL));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(Constants.Users.COLUMN_NAME_PASSWORD));
            emails.add(email);
            passwords.add(password);
        }
        cursor.close();
        Log.i(TAG,"readAllInfo: " + emails);

        if (req=="email"){
            return emails;
        }else if (req=="password"){
            return passwords;
        }else {
            return null;
        }

    }
}
