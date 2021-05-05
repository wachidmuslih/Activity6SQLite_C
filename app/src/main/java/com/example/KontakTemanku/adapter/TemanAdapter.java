package com.example.KontakTemanku.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.KontakTemanku.R;
import com.example.KontakTemanku.database.Teman;

import java.util.ArrayList;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> implements Filterable {

    private ArrayList<Teman> listdata;
    private final TemanAdapterListener listener;

    public interface TemanAdapterListener {
        void onTemanSelected(View v, Teman teman, int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private Teman Teman;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    //construktor
    public TemanAdapter(ArrayList<Teman> listdata, TemanAdapterListener listener) {
        this.listdata = listdata;
        this.listener = listener;
    }

    //1 memanggil tampilan/layout dari adapternya  menggunakan Inflater
    @NonNull
    @Override
    public TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        View view = layoutInf.inflate(R.layout.row_data_teman,parent,false);
        return new TemanViewHolder(view);

    }

    //3 untuk menampilkan
    @Override
    public void onBindViewHolder(@NonNull TemanViewHolder holder, int position) {
        String nm,tlp;

        nm = listdata.get(position).getNama();
        tlp = listdata.get(position).getTelpon();

        holder.namaTxt.setTextColor(Color.BLUE);
        holder.namaTxt.setTextSize(20);
        holder.namaTxt.setText(nm);
        holder.telponTxt.setText(tlp);
    }

    //4 menghitung ukuran dari arraylist
    // bisa ditambahin / biarkan saja
    @Override
    public int getItemCount() {
        return (listdata != null)?listdata.size() : 0;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    // untuk mendaftarkan terlebih dahulu
    public class TemanViewHolder extends RecyclerView.ViewHolder {
        private CardView cardku;
        private TextView namaTxt,telponTxt;
        public TemanViewHolder(View view) {
            super(view);
            cardku = (CardView) view.findViewById(R.id.kartuku);
            namaTxt = (TextView) view.findViewById(R.id.textNama);
            telponTxt = (TextView) view.findViewById(R.id.textTelpon);

            cardku.setOnClickListener(v->{
                Teman KontakItem = listdata.get(getAdapterPosition());
                listener.onTemanSelected(v, KontakItem, getAdapterPosition());

            });
        }
    }



}
