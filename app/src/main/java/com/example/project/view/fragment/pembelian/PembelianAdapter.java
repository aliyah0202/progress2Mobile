package com.example.project.view.fragment.pembelian;

import android.os.Bundle;
import android.content.Context;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.model.ModelDatabase;
import com.example.project.utils.FunctionHelper;
import java.util.List;

public class PembelianAdapter extends RecyclerView.Adapter<com.example.project.view.fragment.pembelian.PembelianAdapter.ViewHolder>{
    private Context context;
    private List<ModelDatabase> list;
    private com.example.project.view.fragment.pembelian.PembelianAdapter.PembelianAdapterCallback mAdapterCallback;

    public PembelianAdapter(Context context, List<ModelDatabase> list, com.example.project.view.fragment.pembelian.PembelianAdapter.PembelianAdapterCallback adapterCallback) {
        this.context = context;
        this.list = list;
        this.mAdapterCallback = adapterCallback;
    }
    @Override
    public com.example.project.view.fragment.pembelian.PembelianAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_data, parent, false);
        return new com.example.project.view.fragment.pembelian.PembelianAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(com.example.project.view.fragment.pembelian.PembelianAdapter.ViewHolder holder, int position) {
        ModelDatabase modelDatabase = list.get(position);
        holder.bindData(modelDatabase);
    }
    @Override
    public int getItemCount() {

        return list.size();
    }

    public void clear() {
        int size = this.list.size();
        this.list.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addData(List<ModelDatabase> penjualans) {
        this.list = penjualans;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvPrice, tvNote, tvDate;
        public ImageView ivDelete;

        ViewHolder(View itemView) {
            super(itemView);

            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvNote = itemView.findViewById(R.id.tvNote);
            tvDate = itemView.findViewById(R.id.tvDate);
            ivDelete = itemView.findViewById(R.id.ivDelete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ModelDatabase pembelian = list.get(getAdapterPosition());
                    mAdapterCallback.onEdit(pembelian);
                }
            });

            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ModelDatabase pembelian = list.get(getAdapterPosition());
                    mAdapterCallback.onDelete(pembelian);
                }
            });
        }

        void bindData(ModelDatabase item) {
            int price = item.jmlUang;
            String initPrice = FunctionHelper.rupiahFormat(price);
            tvPrice.setText(initPrice);

            String note = item.keterangan;
            tvNote.setText(note);

            String date = item.tanggal;
            tvDate.setText(date);
        }
    }

    public interface PembelianAdapterCallback {
        void onEdit(ModelDatabase modelDatabase);

        void onDelete(ModelDatabase modelDatabase);
    }

}





