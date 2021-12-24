package com.dianmediana.prakprogmob;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditContact extends AppCompatActivity {

    DatabaseHelper db;
    EditText nama, number, email;
    Button updateBtn, deleteBtn;
    String id, getName, getNumber, getEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
//        getSupportActionBar().hide();

        db = new DatabaseHelper(this);
        nama = findViewById(R.id.editNama);
        number = findViewById(R.id.editPhone);
        email = findViewById(R.id.editEmail);
        updateBtn = findViewById(R.id.updateContact);
        deleteBtn = findViewById(R.id.deleteBtn);

        getAndSetIntentData();
        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(getName);
        }

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                db = new DatabaseHelper(EditContact.this);
                getName = nama.getText().toString().trim();
                getNumber = number.getText().toString().trim();
                getEmail = email.getText().toString().trim();
                db.updateData(id, getName, getNumber, getEmail);

                Intent intent = new Intent(EditContact.this, ContactList.class);
                startActivity(intent);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("number") && getIntent().hasExtra("email")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            getName = getIntent().getStringExtra("name");
            getNumber = getIntent().getStringExtra("number");
            getEmail = getIntent().getStringExtra("email");

            //Setting Intent Data
            nama.setText(getName);
            number.setText(getNumber);
            email.setText(getEmail);
            Log.d("stev", nama+" "+number+" "+email);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + getName + " ?");
        builder.setMessage("Are you sure you want to delete " + getName + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db = new DatabaseHelper(EditContact.this);
                db.deleteOneRow(id);
                Intent intent = new Intent(EditContact.this, ContactList.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}