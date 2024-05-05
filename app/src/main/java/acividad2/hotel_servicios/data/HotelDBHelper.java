package acividad2.hotel_servicios.data;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import acividad2.hotel_servicios.data.HuespedContract.HuespedEntry;
import acividad2.hotel_servicios.data.TelefonoContract.TelefonoEntry;
public class HotelDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Hotel";
    private static final int DATABASE_VERSION = 1;

    public HotelDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
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
                TelefonoEntry.col_telefono + " TEXT NOT NULL, " +
                "PRIMARY KEY ("+ HuespedEntry.col_id + ", " + TelefonoEntry.col_telefono + ")," +
                " FOREIGN KEY (" + HuespedEntry.col_id + ") " +
                "REFERENCES " + HuespedEntry.TABLE_NAME + "(" + HuespedEntry.col_id + ") ON DELETE CASCADE)");

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

    public Cursor getUsuarioTelefono(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        String tables = HuespedEntry.TABLE_NAME+" INNER JOIN "+TelefonoEntry.TABLE_NAME+
                " on "+HuespedEntry.TABLE_NAME+"."+HuespedEntry.col_email+" = "+TelefonoEntry.TABLE_NAME+"."+HuespedEntry.col_email;
        builder.setTables( tables);
        //String columnas[] = new String["user","name","tel"];
        //return builder.query( db, columnas,null,null,null,null,null );
        return builder.query( db, null,null,null,null,null,null );
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

    }
}
