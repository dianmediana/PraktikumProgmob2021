package com.dianmediana.prakprogmob;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    public static final String database_name = "db_praktikumProgmob";
    public static final String table_name = "tb_regis";
    public static final String row_id = "id";
    public static final String row_nama = "nama";
    public static final String row_email = "email";
    public static final String row_jenis_kelamin = "jeniskelamin";
    public static final String row_umur = "umur";
    public static final String table_contact = "tb_contact";
    public static final String row_name = "nama";
    public static final String row_phone = "phone";
    public static final String row_mail = "email";

    private static SQLiteDatabase db;

    //MODUL 3 BUAT DATABASE 'db_praktikumProgmob'
    DatabaseHelper(@Nullable Context context) {
        super(context, database_name, null, 2);
        db = getWritableDatabase();
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //MODUL 3 BUAT TABEL 'tb_regis' UNTUK MENYIMPAN DATA REGIS
        String query = "CREATE TABLE " + table_name + "(" + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_nama + " TEXT," + row_email + " TEXT," + row_jenis_kelamin + " TEXT," + row_umur + " INTEGER)";
        db.execSQL(query);

        //MODUL 3 BUAT TABEL 'tb_contact' UNTUK MENYIMPAN DATA KONTAK
        String query_contact = "CREATE TABLE " + table_contact + "(" + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_name + " TEXT," + row_phone + " TEXT," + row_mail + " TEXT)";
        db.execSQL(query_contact);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        db.execSQL("DROP TABLE IF EXISTS " + table_contact);
    }

    //MODUL 3 FUNGSI INSERT (DATA REGISTER) KE DATABASE SQLITE
    public static void insertUser(ContentValues values){
        db.insert(table_name, null, values);
    }

    //MODUL 3 FUNGSI INSERT (DATA KONTAK) KE DATABASE SQLITE
    public static void insertContact(ContentValues values){
        db.insert(table_contact, null, values);
    }

    public Cursor readAllData(){
        String sql = "select * from " + table_contact;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(sql, null);
        }
        return cursor;
    }

    void updateData(String row_id, String nama, String phone, String email){
        ContentValues cv = new ContentValues();
        cv.put(row_name, nama);
        cv.put(row_phone, phone);
        cv.put(row_mail, email);

        long result = db.update(table_contact, cv, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id){
        long result = db.delete(table_contact, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}

