package acividad2.hotel_servicios.data;

import android.provider.BaseColumns;

public class CartContract {
    public static abstract class CartEntry implements BaseColumns {
    public static final String TABLE_NAME = "cart";
    public static final String COLUMN_NAME = "item_name";
    public static final  String COLUMN_ITEM_PRICE = "item_price";
    }
}
