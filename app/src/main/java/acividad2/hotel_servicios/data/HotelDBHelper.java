package acividad2.hotel_servicios.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.hardware.input.HostUsiVersion;
import android.os.Build;
import androidx.annotation.Nullable;
import acividad2.hotel_servicios.R;
import acividad2.hotel_servicios.data.HuespedContract.HuespedEntry;
import acividad2.hotel_servicios.data.CartContract.CartEntry;
import acividad2.hotel_servicios.data.TelefonoContract.TelefonoEntry;
import acividad2.hotel_servicios.data.ReservationContract.ReservationEntry;

public class HotelDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Hotel";
    private static final int DATABASE_VERSION = 3;

    private Context mContext;
    public HotelDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + HuespedEntry.TABLE_NAME + "(" +
                HuespedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HuespedEntry.col_name + " TEXT NOT NULL, " +
                HuespedEntry.col_id + " INTEGER NOT NULL, " +
                HuespedEntry.col_email + " TEXT NOT NULL, " +
                HuespedEntry.col_password + " TEXT NOT NULL, " +
                "UNIQUE ("+ HuespedEntry.col_email + "), UNIQUE (" + HuespedEntry.col_id + "))");

        db.execSQL(" CREATE TABLE " + TelefonoEntry.TABLE_NAME + "(" +
                HuespedEntry.col_id + " TEXT NOT NULL, " +
                TelefonoEntry.col_telefono + " TEXT NOT NULL, "     +
                "PRIMARY KEY ("+HuespedEntry.col_id + ", " + TelefonoEntry.col_telefono + ")," +
                "UNIQUE ("+HuespedEntry.col_id + " )," +
                "FOREIGN KEY (" +HuespedEntry.col_id + ") " +
                "REFERENCES " +HuespedEntry.TABLE_NAME + "(" + HuespedEntry.col_id + ") ON DELETE CASCADE)");

        db.execSQL(" CREATE TABLE " + CartEntry.TABLE_NAME + "("
                + CartEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CartEntry.COLUMN_NAME + " TEXT NOT NULL,"
                + CartEntry.COLUMN_ITEM_PRICE + " TEXT NOT NULL);");

        db.execSQL("CREATE TABLE " + ReservationEntry.TABLE_NAME + " ("
                + ReservationEntry.COLUMN_nRESERVA + " INTEGER NOT NULL, "
                + ReservationEntry.COLUMN_USER_ID + " INTEGER NOT NULL, "  // Renombra _ID a USER_ID para evitar duplicados
                + ReservationEntry.COLUMN_PHONE_ID + " INTEGER NOT NULL, " // Renombra id a PHONE_ID
                + ReservationEntry.COLUMN_ITEM_ID + " INTEGER NOT NULL, "  // Renombra _ID a ITEM_ID para evitar duplicados
                + ReservationEntry.COLUM_FECH_RESE + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                + "PRIMARY KEY (" + ReservationEntry.COLUMN_nRESERVA + ", " + ReservationEntry.COLUMN_USER_ID + ", " + ReservationEntry.COLUMN_PHONE_ID + ", " + ReservationEntry.COLUMN_ITEM_ID + "), "
                + "FOREIGN KEY(" + ReservationEntry.COLUMN_USER_ID + ") REFERENCES " + HuespedEntry.TABLE_NAME + "(" + HuespedEntry._ID + ") ON DELETE CASCADE, "
                + "FOREIGN KEY(" + ReservationEntry.COLUMN_PHONE_ID + ") REFERENCES " + HuespedEntry.TABLE_NAME + "(" + HuespedEntry.col_id + ") ON DELETE CASCADE, "
                + "FOREIGN KEY(" + ReservationEntry.COLUMN_ITEM_ID + ") REFERENCES " + CartEntry.TABLE_NAME + "(" + CartEntry._ID + ") ON DELETE CASCADE)");


        // Pre-poblar la tabla "cart"
        prePopulateCartTable(db);
    }

    private void prePopulateCartTable(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put(CartEntry.COLUMN_NAME, mContext.getString(R.string.radio_one));
        values.put(CartEntry.COLUMN_ITEM_PRICE, "600.000");
        db.insert(CartEntry.TABLE_NAME, null, values);

        values.put(CartEntry.COLUMN_NAME , mContext.getString(R.string.radio_two));
        values.put(CartEntry.COLUMN_ITEM_PRICE , "2.000.000");
        db.insert(CartEntry.TABLE_NAME,null ,values);

        values.put(CartEntry.COLUMN_NAME , mContext.getString(R.string.radio_three));
        values.put(CartEntry.COLUMN_ITEM_PRICE , "1.200.000");
        db.insert(CartEntry.TABLE_NAME,null ,values);

        values.put(CartEntry.COLUMN_NAME , mContext.getString(R.string.radio_four));
        values.put(CartEntry.COLUMN_ITEM_PRICE , "800.000");
        db.insert(CartEntry.TABLE_NAME,null ,values);

        values.put(CartEntry.COLUMN_NAME , mContext.getString(R.string.radio_five));
        values.put(CartEntry.COLUMN_ITEM_PRICE , "400.000");
        db.insert(CartEntry.TABLE_NAME,null ,values);

        values.put(CartEntry.COLUMN_NAME , mContext.getString(R.string.radio_six));
        values.put(CartEntry.COLUMN_ITEM_PRICE , "500.000");
        db.insert(CartEntry.TABLE_NAME,null ,values);

        values.put(CartEntry.COLUMN_NAME , mContext.getString(R.string.radio_seven));
        values.put(CartEntry.COLUMN_ITEM_PRICE , "50.000");
        db.insert(CartEntry.TABLE_NAME,null ,values);



    }

    public int getLastReservationNumber() {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {ReservationEntry.COLUMN_nRESERVA};
        String orderBy = ReservationEntry.COLUMN_nRESERVA + " DESC";
        String limit = "1";

        Cursor cursor = db.query(
                ReservationEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                orderBy,
                limit
        );

        int lastReservationNumber = -1;
        if (cursor != null && cursor.moveToFirst()) {
            lastReservationNumber = cursor.getInt(cursor.getColumnIndexOrThrow(ReservationEntry.COLUMN_nRESERVA));
            cursor.close();
        }

        return lastReservationNumber;
    }

    public int getColIdByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {HuespedEntry.col_id};
        String selection = HuespedEntry.col_email + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(
                HuespedEntry.TABLE_NAME,   // Nombre de la tabla
                projection,                // Columnas a devolver
                selection,                 // Columnas para la cláusula WHERE
                selectionArgs,             // Valores para la cláusula WHERE
                null,                      // No agrupar las filas
                null,                      // No filtrar por grupos de filas
                null                       // El orden de clasificación
        );

        int colId = -1;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                colId = cursor.getInt(cursor.getColumnIndexOrThrow(HuespedEntry.col_id));
            }
            cursor.close();
        }

        return colId;
    }

    public void insertReservation(int nReserva, int userId, int userPhoneId, int itemId) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ReservationEntry.COLUMN_nRESERVA, nReserva);
        values.put(ReservationEntry.COLUMN_USER_ID, userId);
        values.put(ReservationEntry.COLUMN_PHONE_ID, userPhoneId);
        values.put(ReservationEntry.COLUMN_ITEM_ID, itemId);

        db.insert(ReservationEntry.TABLE_NAME, null, values);
    }

        public int getUserIdByEmail(String email) {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {HuespedEntry._ID};
        String selection = HuespedEntry.col_email + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(
                HuespedEntry.TABLE_NAME,   // Nombre de la tabla
                projection,                // Columnas a devolver
                selection,                 // Columnas para la cláusula WHERE
                selectionArgs,             // Valores para la cláusula WHERE
                null,                      // No agrupar las filas
                null,                      // No filtrar por grupos de filas
                null                       // El orden de clasificación
        );

        int userId = -1;
        if (cursor != null && cursor.moveToFirst()) {
            userId = cursor.getInt(cursor.getColumnIndexOrThrow(HuespedEntry._ID));
            cursor.close();
        }

        return userId;
    }




    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }

    public Cursor getHuespedByUser(String user, String password) {
        Cursor c = getReadableDatabase().query(
                HuespedEntry.TABLE_NAME,
                null,
                HuespedEntry.col_email + " LIKE ? AND "+HuespedEntry.col_password + " LIKE ?",
                new String[]{user,password},
                null,
                null,
                null);
        return c;
    }



    public Cursor getPrecioArticulo(String nombre) {
        Cursor cur = getReadableDatabase().query(
                CartEntry.TABLE_NAME,
                null,
                CartEntry.COLUMN_NAME + " = ?",
                new String[]{nombre},
                null,
                null,
                null);
        return cur;
    }

    public Cursor getAllItems() {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
               CartEntry._ID,
                CartEntry.COLUMN_NAME,
                CartEntry.COLUMN_ITEM_PRICE
        };

        Cursor cursor = db.query(
                CartEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        return cursor;

    }

    public long saveHuesped(Huesped huesped, Telefono telefono) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(
                HuespedEntry.TABLE_NAME,
                null,
                huesped.toContentValues());
        return saveTelefono( telefono );
    }

    public long saveTelefono(Telefono telefono){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(
                TelefonoEntry.TABLE_NAME,
                null,
                telefono.toContentValues());
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ReservationEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + HuespedEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TelefonoEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CartEntry.TABLE_NAME);
        onCreate(db);
    }

    public Cursor getHuespedByUser(String email) {
        return null;
    }
}

