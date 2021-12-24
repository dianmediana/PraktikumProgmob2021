package com.dianmediana.prakprogmob;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

//MODUL 3 ACTIVITY FORM DATABASE SQLITE
public class CreateContact extends AppCompatActivity {

    DatabaseHelper db;
    EditText nama, number, email;
    Button saveBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact); //LAYOUT FORM MODUL 3

        db = new DatabaseHelper(this);
        nama = findViewById(R.id.addNama);
        number = findViewById(R.id.addPhone);
        email = findViewById(R.id.addEmail);
        saveBtn = findViewById(R.id.addContact);

        // MODUL 3 'TOMBOL SAVE' UNTUK MENYIMPAN DATA KONTAK KE SQLITE
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getNama = nama.getText().toString();
                String getNumber = number.getText().toString();
                String getEmail = email.getText().toString();

                if (getNama.matches("") || getNumber.matches("") || getEmail.matches("")) {
                    Toast.makeText(CreateContact.this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();
                } else {
//                  //MODUL 3 MENGAMBIL DATA KONTAK UNTUK DISIMPAN KE TABEL KONTAK DI DATABASE SQLITE
                    ContentValues values = new ContentValues();
                    values.put(DatabaseHelper.row_name, getNama);
                    values.put(DatabaseHelper.row_phone, getNumber);
                    values.put(DatabaseHelper.row_mail, getEmail);
                    DatabaseHelper.insertContact(values);

                    Intent intent = new Intent(CreateContact.this, ContactList.class);
                    Toast.makeText(CreateContact.this, "Data successfully saved.", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Add Contact");
    }
}