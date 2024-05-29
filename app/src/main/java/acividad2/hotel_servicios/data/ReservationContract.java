package acividad2.hotel_servicios.data;

import android.provider.BaseColumns;

public class ReservationContract {
    public static final class ReservationEntry implements BaseColumns {
        public static final String TABLE_NAME = "Reservation";
        public static final String COLUMN_nRESERVA = "nReserva";
        public static final String COLUMN_USER_ID = "user_id"; // Nuevo nombre de columna
        public static final String COLUMN_PHONE_ID = "phone_id"; // Nuevo nombre de columna
        public static final String COLUMN_ITEM_ID = "item_id"; // Nuevo nombre de columna
        public static final String COLUM_FECH_RESE = "fechaReserva";
    }

}
