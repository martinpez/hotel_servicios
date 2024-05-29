package acividad2.hotel_servicios.data;

import android.content.ContentValues;
import android.database.Cursor;

import acividad2.hotel_servicios.data.CartContract.CartEntry;


public class Cart {
    private int id_cart;
    private String name_item;
    private String price_item;


    public Cart(int id_cart, String name_item, String prece_item) {
        this.id_cart = id_cart;
        this.name_item = name_item;
        this.price_item = prece_item;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(CartEntry._ID ,id_cart);
        values.put(CartEntry.COLUMN_NAME, name_item);
        values.put(CartEntry.COLUMN_ITEM_PRICE, price_item);


        return values;
    }

    public Cart(Cursor cursor){
        this.id_cart = Integer.parseInt(cursor.getString( cursor.getColumnIndex( CartEntry._ID)));
        this.name_item = cursor.getString(cursor.getColumnIndex(CartEntry.COLUMN_NAME));
        this.price_item = cursor.getString(cursor.getColumnIndex(CartEntry.COLUMN_ITEM_PRICE));
    }

    public int getId_cart() {
        return id_cart;
    }

    public String getName_item() {
        return name_item;
    }

    public String getPrice_item() {
        return price_item;
    }
}
