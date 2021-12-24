package com.dianmediana.prakprogmob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContactList extends AppCompatActivity {
    DatabaseHelper db;
    RecyclerView contactList;
    FloatingActionButton add_button;
    ArrayList<String> contact_id, contact_name, contact_no, contact_email;
    ContactAdapter contactAdapter;
    ImageView emptyImg;
    TextView noData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
//        getSupportActionBar().hide();

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Contact List");
//        ImageView back = findViewById(R.id.back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ContactList.this, DashboardActivity.class);
//                startActivity(intent);
//            }
//        });

        db = new DatabaseHelper(this);
        contactList = findViewById(R.id.contactList);
        add_button = findViewById(R.id.addBtn);
        emptyImg = findViewById(R.id.emptyImg);
        noData = findViewById(R.id.noData);

        contact_id = new ArrayList<>();
        contact_name = new ArrayList<>();
        contact_no = new ArrayList<>();
        contact_email = new ArrayList<>();

        storeDataInArrays();

        contactAdapter = new ContactAdapter(ContactList.this,this, contact_id, contact_name, contact_no, contact_email);
        contactList.setAdapter(contactAdapter);
        contactList.setLayoutManager(new LinearLayoutManager(ContactList.this));

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactList.this, CreateContact.class);
                startActivity(intent);
            }
        });
    }

    void storeDataInArrays(){
        Cursor cursor = db.readAllData();
        if(cursor.getCount() == 0){
            emptyImg.setVisibility(View.VISIBLE);
            noData.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                contact_id.add(cursor.getString(0));
                contact_name.add(cursor.getString(1));
                contact_no.add(cursor.getString(2));
                contact_email.add(cursor.getString(3));
            }
            emptyImg.setVisibility(View.GONE);
            noData.setVisibility(View.GONE);
        }
    }
}