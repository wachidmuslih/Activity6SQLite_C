package com.example.KontakTemanku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MenampilkanData extends AppCompatActivity {
    private TextView tNama, tTelpon;

    Bundle bundle = getIntent().getExtras();

    String nama = bundle.getString("nama");
    String telp = bundle.getString("nomor");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menampilkan_data);

    }
}