package com.example.KontakTemanku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

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
    private Context context;

    //untuk menyimpan data teman
    private ArrayList<Teman> temanArrayList;
    DBController controller = new DBController(this);
    String id,nm,tlp;
    private FloatingActionButton fab;
    private Button btn;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private Teman temanSelected;
        public TextView nama, telepon;
        public DBController DB;

        public MyViewHolder(View view, DBController db) {
            this(view);
            this.DB = db;
        }

        public MyViewHolder(@NonNull View view) {
            super(view);
            nama = view.findViewById(R.id.nama);
            telepon = view.findViewById(R.id.telepon);
        }

        private void ViewDetail(View view) {
            Teman kontakItem = temanArrayList.get(getAdapterPosition());
            Intent i = new Intent(getApplicationContext(), MenampilkanData.class);
            Bundle b = new Bundle();
            b.putString("nama", kontakItem.getNama());
            b.putString("nomor", kontakItem.getTelpon());
            b.putBoolean("edit", false);
            i.putExtras(b);
            startActivity(i);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.floatingBtn);
        btn = findViewById(R.id.bHapus);

        btn.setOnClickListener((v ->{
            new AlertDialog.Builder(this).setMessage("Hapus semuanya?")
                    .setPositiveButton("Hapus",(y, x)-> {
                        Toast.makeText(this, "Hapus", Toast.LENGTH_SHORT).show();
                    } )
                    .setNegativeButton("Batal",(y, x)-> {
                        Toast.makeText(this, "Batal", Toast.LENGTH_SHORT).show();
                    }).show();
        }));

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
    public void onTemanSelected(View v, Teman teman, int pos) {
        showPopup(v);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.edit:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                intent = new Intent(getApplicationContext(),MengeditData.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.hapus:
                Toast.makeText(getApplicationContext(),"Ini untuk delete kontak",
                        Toast.LENGTH_LONG).show();
                break;
        }
        return false;
    }


    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popupmenu, popup.getMenu());
        popup.show();
    }
}