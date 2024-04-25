package acividad2.hotel_servicios.data;

import android.content.ContentValues;
import android.database.Cursor;

import acividad2.hotel_servicios.data.HuespedContract.HuespedEntry;
import acividad2.hotel_servicios.data.TelefonoContract.TelefonoEntry;
public class Telefono {
    private int id_huesped;
    private int telefono_husped;

    public Telefono(int id_huesped, int telefono_husped) {
        this.id_huesped = id_huesped;
        this.telefono_husped = telefono_husped;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put( HuespedEntry.col_id, id_huesped);
        values.put( TelefonoEntry.col_telefono, telefono_husped);
        return values;
    }

    public Telefono(Cursor cursor){
        this.id_huesped = Integer.parseInt(cursor.getString( cursor.getColumnIndex( HuespedEntry.col_id ) ));
        this.telefono_husped = (int) cursor.getLong( cursor.getColumnIndex( TelefonoEntry.col_telefono ) );
    }

    public int getId_huesped() {
        return id_huesped;
    }

    public int getTelefono_husped() {
        return telefono_husped;
    }

}
