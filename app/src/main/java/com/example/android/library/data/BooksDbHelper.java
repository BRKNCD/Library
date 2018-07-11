package com.example.android.library.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.library.data.BookContract.BookEntry;

public class BooksDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "BooksDatabase";
    public static final int DATABASE_VERSION = 1;
    private static final String SQL_DELETE_DATABASE =
            "DROP TABLE IF EXISTS " + BookEntry.TABLE_NAME;


    public BooksDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_DATABASE =
                "CREATE TABLE " + BookEntry.TABLE_NAME +
                        " (" + BookEntry._ID + " INTEGER PRIMARY KEY," +
                        BookEntry.COLUMN_PRODUCT_NAME + " TEXT," +
                        BookEntry.COLUMN_PRICE + " INTEGER," +
                        BookEntry.COLUMN_QUANTITY + " INTEGER," +
                        BookEntry.COLUMN_SUPPLIER_NAME + " TEXT," +
                        BookEntry.COLUMN_PHONE_NUMBER + " TEXT)";
        db.execSQL(SQL_CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_DATABASE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
