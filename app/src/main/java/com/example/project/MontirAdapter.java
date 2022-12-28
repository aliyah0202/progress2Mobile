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

public class MontirAdapter extends RecyclerView.Adapter<MontirAdapter.MyHolder> {

    Context context;
    ArrayList<ModelClass> arrayList;
    LayoutInflater layoutInflater;

    public MontirAdapter(Context context, ArrayList<ModelClass> arrayList) {
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
        holder.namaMontir.setText(arrayList.get(position).getNamaMontir());
        holder.numMontir.setText(arrayList.get(position).getNumMontir());
        holder.img.setImageResource(arrayList.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView namaMontir,numMontir;
        ImageView img;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            namaMontir = itemView.findViewById(R.id.txt);
            numMontir = itemView.findViewById(R.id.txt2);
            img = itemView.findViewById(R.id.img);
        }
    }
}
