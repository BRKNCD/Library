package com.example.android.library;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import static com.example.android.library.data.BookContract.BookEntry;

public class BookCursorAdapter extends CursorAdapter{

    public BookCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // this will inflate a newView everytime is needed
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
    }

    // this will bind all the Layout's View
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = view.findViewById(R.id.name_text_view);
        TextView priceTextView = view.findViewById(R.id.price_text_view);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(BookEntry.COLUMN_PRODUCT_NAME));
        String price = cursor.getString(cursor.getColumnIndexOrThrow(BookEntry.COLUMN_PRICE));

        nameTextView.setText(name);
        priceTextView.setText(price);
    }
}
