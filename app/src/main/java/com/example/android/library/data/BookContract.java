package com.example.android.library.data;

import android.provider.BaseColumns;

public final class BookContract {

    private BookContract(){};

    public static class BookEntry implements BaseColumns{
        public static final String TABLE_NAME = "Books";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "Product";
        public static final String COLUMN_PRICE = "Price";
        public static final String COLUMN_QUANTITY = "Quantity";
        public static final String COLUMN_SUPPLIER_NAME = "Supplier";
        public static final String COLUMN_PHONE_NUMBER = "Phone_Number";
    }
}