package com.example.android.library;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        TextView quantityTextView = view.findViewById(R.id.quantity_text_view);
        Button buyButton = view.findViewById(R.id.buy_button);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(BookEntry.COLUMN_PRODUCT_NAME));
        String price = cursor.getString(cursor.getColumnIndexOrThrow(BookEntry.COLUMN_PRICE));
        String quantity = cursor.getString(cursor.getColumnIndexOrThrow(BookEntry.COLUMN_QUANTITY));

        nameTextView.setText(name);
        priceTextView.setText(price);
        quantityTextView.setText(quantity);
        buyButton.setOnClickListener(buyClickListener);
    }

    private View.OnClickListener buyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            View parentRow = (View) v.getParent();
            ListView listViewParent = (ListView) parentRow.getParent();
            TextView mQuantity = parentRow.findViewById(R.id.quantity_text_view);
            final int position = listViewParent.getPositionForView(parentRow);
            long id = getItemId(position);
            String quantity = mQuantity.getText().toString().trim();
            int updatedQuantity = Integer.parseInt(quantity) - 1;
            Uri currentBookUri = ContentUris.withAppendedId(BookEntry.CONTENT_URI, id);
            if (updatedQuantity < 0) {
                Toast.makeText(v.getContext(), v.getContext().getString(R.string.illegal_quantity_toast), Toast.LENGTH_LONG).show();
            } else {
                mQuantity.setText(Integer.toString(updatedQuantity));
                ContentValues values = new ContentValues();
                values.put(BookEntry.COLUMN_QUANTITY, updatedQuantity);
                int rowsUpdated = v.getContext().getContentResolver().update(BookEntry.CONTENT_URI, values, "_ID = ?", new String[]{String.valueOf(id)});
            }
        }

    };
}
