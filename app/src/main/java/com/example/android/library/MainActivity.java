package com.example.android.library;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.android.library.data.BookContract.BookEntry;
import com.example.android.library.data.BooksDbHelper;

public class MainActivity extends AppCompatActivity {

    private BooksDbHelper mDbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new BooksDbHelper(this);
        db = mDbHelper.getReadableDatabase();
        insertBook();
        displayDatabaseInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {

        String[] projection = {
                BookEntry._ID,
                BookEntry.COLUMN_PRODUCT_NAME,
                BookEntry.COLUMN_PRICE,
                BookEntry.COLUMN_QUANTITY,
                BookEntry.COLUMN_SUPPLIER_NAME,
                BookEntry.COLUMN_PHONE_NUMBER
        };

        Cursor cursor = db.query(
                BookEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        TextView displayTextView = (TextView) findViewById(R.id.text_view_book);

        try {
            displayTextView.setText(
                    "The books table contains " +
                            cursor.getCount() +
                            " books.\n\n");
            displayTextView.append(
                    BookEntry._ID + " - " +
                            BookEntry.COLUMN_PRODUCT_NAME + " - " +
                            BookEntry.COLUMN_PRICE + " - " +
                            BookEntry.COLUMN_QUANTITY + " - " +
                            BookEntry.COLUMN_SUPPLIER_NAME + " - " +
                            BookEntry.COLUMN_PHONE_NUMBER + "\n"
            );

            int idColumnIndex = cursor.getColumnIndex(BookEntry._ID);
            int idProductNameIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_NAME);
            int idPriceIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRICE);
            int idQuantityIndex = cursor.getColumnIndex(BookEntry.COLUMN_QUANTITY);
            int idSupplierIndex = cursor.getColumnIndex(BookEntry.COLUMN_SUPPLIER_NAME);
            int idPhoneIndex = cursor.getColumnIndex(BookEntry.COLUMN_PHONE_NUMBER);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentProduct = cursor.getString(idProductNameIndex);
                int currentPrice = cursor.getInt(idPriceIndex);
                int currentQuantity = cursor.getInt(idQuantityIndex);
                String currentSupplier = cursor.getString(idSupplierIndex);
                String currentPhone = cursor.getString(idPhoneIndex);

                displayTextView.append(
                                "\n" +
                                currentID + " - " +
                                currentProduct + " - " +
                                currentPrice + " - " +
                                currentQuantity + " - " +
                                currentSupplier + " - " +
                                currentPhone + " - "
                );
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    public void insertBook(){
        ContentValues values = new ContentValues();
        values.put(BookEntry.COLUMN_PRODUCT_NAME, "HG2G");
        values.put(BookEntry.COLUMN_PRICE, 9.99);
        values.put(BookEntry.COLUMN_QUANTITY, 42);
        values.put(BookEntry.COLUMN_SUPPLIER_NAME, "Ford Prefect");
        values.put(BookEntry.COLUMN_PHONE_NUMBER, "101010");
        db.insert(BookEntry.TABLE_NAME, null, values);
    }
}