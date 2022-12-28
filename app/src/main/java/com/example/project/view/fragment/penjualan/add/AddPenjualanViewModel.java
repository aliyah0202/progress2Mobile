package com.example.project.view.fragment.penjualan.add;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.project.database.DatabaseClient;
import com.example.project.database.dao.DatabaseDao;
import com.example.project.model.ModelDatabase;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AddPenjualanViewModel extends AndroidViewModel{
    private DatabaseDao databaseDao;

    public AddPenjualanViewModel(@NonNull Application application) {
        super(application);

        databaseDao = DatabaseClient.getInstance(application).getAppDatabase().databaseDao();

    }
    public void addPenjualan(final String type, final String note, final String date, final int price){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                ModelDatabase penjualan = new ModelDatabase();
                penjualan.tipe = type;
                penjualan.keterangan = note;
                penjualan.tanggal = date;
                penjualan.jmlUang = price;
                databaseDao.insertPenjualan(penjualan);

            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
    public void updatePenjualan(final int uid, final String note, final String date, final int price) {
        Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        databaseDao.updateDataPenjualan(note, date, price, uid);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
