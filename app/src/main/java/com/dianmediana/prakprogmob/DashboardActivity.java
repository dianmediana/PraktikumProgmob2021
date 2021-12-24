package com.dianmediana.prakprogmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//MODUL 2 BUAT ACTIVITY BARU
public class DashboardActivity extends AppCompatActivity {
    TextView nama, email, umur, jk, header;
    Button contactList, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard); //LAYOUT DASHBOARD MODUL 2

        this.getSupportActionBar().setTitle("User Profile");
        this.getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));
        this.getSupportActionBar().setDisplayOptions( getSupportActionBar().DISPLAY_SHOW_TITLE
                | getSupportActionBar().DISPLAY_SHOW_HOME
                //| getSupportActionBar().DISPLAY_HOME_AS_UP
                | getSupportActionBar().DISPLAY_SHOW_CUSTOM
                | getSupportActionBar().DISPLAY_USE_LOGO);

        header = findViewById(R.id.header);
        nama = findViewById(R.id.vname);
        email = findViewById(R.id.vemail);
        umur = findViewById(R.id.vage);
        jk = findViewById(R.id.vgender);
        contactList = findViewById(R.id.contactBtn);
        logout = findViewById(R.id.logoutBtn);

        //MODUL 2 AMBIL DATA INPUTAN USER UNTUK DITAMPILKAN DI DASHOARD
        String name = getIntent().getExtras().getString("nama");
        String email1 = getIntent().getExtras().getString("email");
        String gender = getIntent().getExtras().getString("jeniskelamin");
        String age = getIntent().getExtras().getString("umur");

        //MODUL 2 SET DATA INPUTAN USER PADA TAMPILAN DASHOARD
        header.setText(name);
        nama.setText(name);
        email.setText(email1);
        umur.setText(age);
        jk.setText(gender);

        contactList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ContactList.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //MODUL 2 Activity Lifecycle
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "On Start", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "On Resume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "On Stop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "On Destroy", Toast.LENGTH_SHORT).show();
    }
}