package com.example.project.view.fragment.pembelian.add;

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


public class AddPembelianViewModel extends AndroidViewModel{
    private DatabaseDao databaseDao;

    public AddPembelianViewModel(@NonNull Application application) {
        super(application);

        databaseDao = DatabaseClient.getInstance(application).getAppDatabase().databaseDao();
    }

    public void addPembelian(final String type, final String note, final String date, final int price) {
        Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        ModelDatabase pembelian = new ModelDatabase();
                        pembelian.tipe = type;
                        pembelian.keterangan = note;
                        pembelian.tanggal = date;
                        pembelian.jmlUang = price;
                        databaseDao.insertPembelian(pembelian);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
    public void updatePembelian(final int uid, final String note, final String date, final int price) {
        Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        databaseDao.updateDataPembelian(note, date, price, uid);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

}
