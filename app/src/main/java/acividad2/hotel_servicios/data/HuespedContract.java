package acividad2.hotel_servicios.data;

import android.provider.BaseColumns;

public class HuespedContract {
    public static abstract class HuespedEntry implements BaseColumns{
        public static final String TABLE_NAME = "huesped";
        public static final String col_name = "nombre";
        public static final String col_id = "id";
        public static final String col_email = "email";
        public static final String col_password = "password";
    }
}
