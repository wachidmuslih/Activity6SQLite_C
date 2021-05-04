package com.example.KontakTemanku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MengeditData extends AppCompatActivity {

    private EditText editNama, editTelepon;

    private Button btnBatal, btnSimpan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mengedit_data);

        editNama = findViewById(R.id.edNama);
        editTelepon = findViewById(R.id.edTelpon);
        btnBatal = findViewById(R.id.bBatal);
        btnSimpan = findViewById(R.id.bSimpan);
    }
}