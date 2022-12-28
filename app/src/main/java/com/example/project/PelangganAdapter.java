package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PelangganAdapter extends RecyclerView.Adapter<PelangganAdapter.MyHolder> {

    Context context;
    ArrayList<ModelClass> arrayList;
    LayoutInflater layoutInflater;

    public PelangganAdapter(Context context, ArrayList<ModelClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_file,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.namaPelanggan.setText(arrayList.get(position).getNamaPelanggan());
        holder.numPelanggan.setText(arrayList.get(position).getNumPelanggan());
        holder.img.setImageResource(arrayList.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView namaPelanggan,numPelanggan;
        ImageView img;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            namaPelanggan = itemView.findViewById(R.id.txt);
            numPelanggan = itemView.findViewById(R.id.txt2);
            img = itemView.findViewById(R.id.img);
        }
    }
}
