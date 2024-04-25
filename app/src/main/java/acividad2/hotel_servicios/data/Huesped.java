package acividad2.hotel_servicios.data;

import android.content.ContentValues;
import android.database.Cursor;

import acividad2.hotel_servicios.data.HuespedContract.HuespedEntry;

public class Huesped {
    private String name;
    private int id_husped;
    private String email_huesped;
    private String password_huesped;

    public Huesped(String name, int id_husped, String email_huesped, String password_huesped) {
        this.name = name;
        this.id_husped = id_husped;
        this.email_huesped = email_huesped;
        this.password_huesped = password_huesped;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(HuespedEntry.col_name, name);
        values.put(HuespedEntry.col_id, id_husped);
        values.put(HuespedEntry.col_email, email_huesped);
        values.put(HuespedEntry.col_password, password_huesped);
        return values;
    }

    public Huesped(Cursor cursor){
        this.name = cursor.getString( cursor.getColumnIndex( HuespedEntry.col_name ) );
        this.id_husped = Integer.parseInt(cursor.getString( cursor.getColumnIndex( HuespedEntry.col_id ) ));
        this.email_huesped = cursor.getString( cursor.getColumnIndex( HuespedEntry.col_email ) );
        this.password_huesped = cursor.getString( cursor.getColumnIndex( HuespedEntry.col_password ) );
    }

    public String getName() {
        return name;
    }

    public int getId_husped() {
        return id_husped;
    }

    public String getEmail_huesped() {
        return email_huesped;
    }

    public String getPassword_huesped() {
        return password_huesped;
    }
}


