package com.example.KontakTemanku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.KontakTemanku.adapter.TemanAdapter;
import com.example.KontakTemanku.database.DBController;
import com.example.KontakTemanku.database.Teman;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, TemanAdapter.TemanAdapterListener{
    //deklarasi
    private RecyclerView recyclerView;
    private TemanAdapter adapter;

    //untuk menyimpan data teman
    private ArrayList<Teman> temanArrayList;
    DBController controller = new DBController(this);
    String id,nm,tlp;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.floatingBtn);

        BacaData();
        adapter = new TemanAdapter(temanArrayList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TemanBaru.class);
                startActivity(intent);
            }
        });

    }

    private void BacaData() {
        ArrayList<HashMap<String,String>> daftarTeman = controller.getAllTeman();
        temanArrayList = new ArrayList<>();
        //memindah dari hasil query kedalam teman
        for (int i=0;i<daftarTeman.size();i++) {
            Teman teman = new Teman();

            teman.setId(daftarTeman.get(i).get("id").toString());
            teman.setNama(daftarTeman.get(i).get("nama").toString());
            teman.setTelpon(daftarTeman.get(i).get("telpon").toString());
            //pindahkan dari Teman kedalam ArrayList teman di adapter
            temanArrayList.add(teman);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popupmenu, popup.getMenu());
        popup.show();
    }
}