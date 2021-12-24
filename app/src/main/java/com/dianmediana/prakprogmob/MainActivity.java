package com.dianmediana.prakprogmob;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //MODUL 1 BUAT FORM PENDAFTARAN
    DatabaseHelper db;
    TextView alertNama, alertEmail, alertGender, alertUmur, alertStatus; //MODUL 1 TEXTVIEW
    EditText nama, email; //MODUL 1 EDITTEXT
    RadioGroup radioGroup;
    RadioButton pria, wanita; //MODUL 1 RADIOBUTTON
    SeekBar umur; //MODUL 1 SEEKBAR
    TextView angkaUmur;
    CheckBox checkBox; //MODUL 1 CHECKBOX
    Button submitButton; //MODUL 1 BUTTON

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //LAYOUT FORM PENDAFTARAN MODUL 1
        getSupportActionBar().hide();

        db = new DatabaseHelper(this);
        nama = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmail);
        radioGroup = findViewById(R.id.radioGroup);
        pria = findViewById(R.id.male);
        wanita = findViewById(R.id.female);
        umur = findViewById(R.id.age);
        angkaUmur = findViewById(R.id.numberAge);
        checkBox = findViewById(R.id.checkbox);
        submitButton = findViewById(R.id.registerButton);

        //MODUL 1 SEEKBAR
        umur.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                angkaUmur.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //MODUL 1 TOMBOL REGISTER UNTUK SUBMIT
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getNama = nama.getText().toString();
                String getEmail = email.getText().toString();
                int getGender = radioGroup.getCheckedRadioButtonId();
                int getUmur = umur.getProgress();

                if (getNama.matches("") || getEmail.matches("") || umur.getProgress() == 0 || radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MainActivity.this, "Empty field not allowed!", Toast.LENGTH_SHORT).show();
                } else if (!checkBox.isChecked()){
                    Toast.makeText(MainActivity.this, "You need to agree the License Terms and Privacy Policy.", Toast.LENGTH_SHORT).show();
                } else {
                    // MODUL 1 BUAT ALERT DIALOG UNTUK MENMAPILKAN INPUTAN USER
                    AlertDialog.Builder dialog1 = new AlertDialog.Builder(MainActivity.this);
                    View view = getLayoutInflater().inflate(R.layout.alert_dialogs,null); //LAYOUT ALERT DIALOG MODUL 1

                    alertNama = (TextView) view.findViewById(R.id.alert_name);
                    alertNama.setText(" " + getNama);

                    alertEmail = (TextView) view.findViewById(R.id.alert_email);
                    alertEmail.setText(" " + getEmail);

                    if (getGender == pria.getId()){
                        alertGender = (TextView) view.findViewById(R.id.alert_gender);
                        alertGender.setText(" Male");
                    } else if (getGender == wanita.getId()){
                        alertGender = (TextView) view.findViewById(R.id.alert_gender);
                        alertGender.setText(" Female");
                    }

                    alertUmur = (TextView) view.findViewById(R.id.alert_age);
                    alertUmur.setText(" " + String.valueOf(getUmur));

                    if (checkBox.isChecked()) {
                        alertStatus = (TextView) view.findViewById(R.id.alert_status);
                        alertStatus.setText("User agree to the License Terms and Privacy Policy.");
                    }

                    dialog1.setPositiveButton("submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            Toast.makeText(MainActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);

                            String getNama = nama.getText().toString();
                            String getEmail = email.getText().toString();
                            int getGender = radioGroup.getCheckedRadioButtonId();
                            int getUmur = umur.getProgress();
                            wanita = findViewById(getGender);

                            String jenisKelamin = alertGender.getText().toString();

                            //MODUL 3 MENGAMBIL DATA REGISTER UNTUK DISIMPAN KE TABEL REGIS DI DATABASE SQLITE
                            ContentValues values = new ContentValues();
                            values.put(DatabaseHelper.row_nama, getNama);
                            values.put(DatabaseHelper.row_email, getEmail);
                            values.put(DatabaseHelper.row_jenis_kelamin, jenisKelamin);
                            values.put(DatabaseHelper.row_umur, getUmur);
                            DatabaseHelper.insertUser(values);

                            //MODUL 2 AMBIL DATA INPUTAN USER UNTUK DITAMPILKAN DI DASHOARD
                            intent.putExtra("nama",nama.getText().toString());
                            intent.putExtra("email",email.getText().toString());
                            intent.putExtra("jeniskelamin",wanita.getText().toString());
                            intent.putExtra("umur",angkaUmur.getText().toString());
                            startActivity(intent);
                        }
                    });

                    dialog1.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    dialog1.setView(view);

                    AlertDialog dialog2 = dialog1.create();
                    dialog2.show();
                }
            }
        });
    }
}