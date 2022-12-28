package com.example.project.view.fragment.pembelian;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.model.ModelDatabase;
import com.example.project.utils.FunctionHelper;
import com.example.project.view.fragment.pembelian.add.AddPembelianActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class PembelianFragment extends Fragment implements PembelianAdapter.PembelianAdapterCallback {

    private PembelianAdapter pembelianAdapter;
    private PembelianViewModel pembelianViewModel;
    private List<ModelDatabase> modelDatabase = new ArrayList<>();
    TextView tvTotal, tvNotFound;
    Button btnHapus;
    FloatingActionButton fabAdd;
    RecyclerView rvListData;

    public PembelianFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pembelian, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTotal = view.findViewById(R.id.tvTotal);
        tvNotFound = view.findViewById(R.id.tvNotFound);
        btnHapus = view.findViewById(R.id.btnHapus);
        fabAdd = view.findViewById(R.id.fabAdd);
        rvListData = view.findViewById(R.id.rvListData);

        tvNotFound.setVisibility(View.GONE);

        initAdapter();
        observeData();
        initAction();
    }

    private void initAction() {
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPembelianActivity.startActivity(requireActivity(), false, null);
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pembelianViewModel.deleteAllData();
                tvTotal.setText("0");
            }
        });
    }

    private void initAdapter() {
        pembelianAdapter = new PembelianAdapter(requireContext(), modelDatabase, this);
        rvListData.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvListData.setItemAnimator(new DefaultItemAnimator());
        rvListData.setAdapter(pembelianAdapter);
    }

    private void observeData() {
        pembelianViewModel = ViewModelProviders.of(this).get(PembelianViewModel.class);
        pembelianViewModel.getPembelian().observe(requireActivity(),
                new Observer<List<ModelDatabase>>() {
                    @Override
                    public void onChanged(List<ModelDatabase> pembelian) {
                        if (pembelian.isEmpty()) {
                            btnHapus.setVisibility(View.GONE);
                            tvNotFound.setVisibility(View.VISIBLE);
                            rvListData.setVisibility(View.GONE);
                        } else {
                            btnHapus.setVisibility(View.VISIBLE);
                            tvNotFound.setVisibility(View.GONE);
                            rvListData.setVisibility(View.VISIBLE);
                        }
                        pembelianAdapter.addData(pembelian);
                    }
                });

        pembelianViewModel.getTotalPembelian().observe(requireActivity(),
                new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        if (integer == null) {
                            int totalPrice = 0;
                            String initPrice = FunctionHelper.rupiahFormat(totalPrice);
                            tvTotal.setText(initPrice);
                        } else {
                            int totalPrice = integer;
                            String initPrice = FunctionHelper.rupiahFormat(totalPrice);
                            tvTotal.setText(initPrice);
                        }
                    }
                });
    }

    @Override
    public void onEdit(ModelDatabase modelDatabase) {
        AddPembelianActivity.startActivity(requireActivity(), true, modelDatabase);
    }

    @Override
    public void onDelete(ModelDatabase modelDatabase) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(requireContext());
        alertDialogBuilder.setMessage("Hapus riwayat ini?");
        alertDialogBuilder.setPositiveButton("Ya, Hapus", (dialogInterface, i) -> {
            int uid = modelDatabase.uid;
            String sKeterangan = pembelianViewModel.deleteSingleData(uid);
            if (sKeterangan.equals("OK")) {
                Toast.makeText(requireContext(), "Data yang dipilih sudah dihapus", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialogBuilder.setNegativeButton("Batal", (dialogInterface, i) -> dialogInterface.cancel());

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}