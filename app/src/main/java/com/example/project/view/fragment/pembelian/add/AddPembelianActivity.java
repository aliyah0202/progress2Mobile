package com.example.project.view.fragment.pembelian.add;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.model.ModelDatabase;
import com.example.project.view.Penjualan;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddPembelianActivity extends AppCompatActivity{
    private static String KEY_IS_EDIT = "key_is_edit";
    private static String KEY_DATA = "key_data";

    public static void startActivity(Context context, boolean isEdit, ModelDatabase pembelian) {
        Intent intent = new Intent(new Intent(context, AddPembelianActivity.class));
        intent.putExtra(KEY_IS_EDIT, isEdit);
        intent.putExtra(KEY_DATA, pembelian);
        context.startActivity(intent);
    }
    private AddPembelianViewModel addPembelianViewModel;

    private boolean mIsEdit = false;
    private int strUid = 0;

    Toolbar toolbar;
    TextInputEditText etKeterangan, etTanggal, etJmlUang;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        toolbar = findViewById(R.id.toolbar);
        etKeterangan = findViewById(R.id.etKeterangan);
        etTanggal = findViewById(R.id.etTanggal);
        etJmlUang = findViewById(R.id.etJmlUang);
        btnSimpan = findViewById(R.id.btnSimpan);

        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        addPembelianViewModel = ViewModelProviders.of(this).get(AddPembelianViewModel.class);

        loadData();
        initAction();
    }
    private void initAction() {
        etTanggal.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog.OnDateSetListener date = (view1, year, monthOfYear, dayOfMonth) -> {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String strFormatDefault = "d MMMM yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormatDefault, Locale.getDefault());
                etTanggal.setText(simpleDateFormat.format(calendar.getTime()));
            };

            new DatePickerDialog(AddPembelianActivity.this, date,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strTipe = "pembelian";
                String strKeterangan = etKeterangan.getText().toString();
                String strTanggal = etTanggal.getText().toString();
                String strJmlUang = etJmlUang.getText().toString();

                if (strKeterangan.isEmpty() || strJmlUang.isEmpty()) {
                    Toast.makeText(AddPembelianActivity.this, "Ups, form tidak boleh kosong!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (mIsEdit) {
                        addPembelianViewModel.updatePembelian(strUid, strKeterangan,
                                strTanggal, Integer.parseInt(strJmlUang));
                    } else {
                        addPembelianViewModel.addPembelian(strTipe, strKeterangan,
                                strTanggal, Integer.parseInt(strJmlUang));
                    }
                    finish();
                }
            }
        });
    }
    private void loadData() {
        mIsEdit = getIntent().getBooleanExtra(KEY_IS_EDIT, false);
        if (mIsEdit) {
            ModelDatabase pembelian = getIntent().getParcelableExtra(KEY_DATA);
            if (pembelian != null) {
                strUid = pembelian.uid;
                String keterangan = pembelian.keterangan;
                String tanggal = pembelian.tanggal;
                int uang = pembelian.jmlUang;

                etKeterangan.setText(keterangan);
                etTanggal.setText(tanggal);
                etJmlUang.setText(String.valueOf(uang));
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(AddPembelianActivity.this,
                    Penjualan.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}



