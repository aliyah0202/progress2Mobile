package com.example.project.view.fragment.pembelian;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

import com.example.project.database.DatabaseClient;
import com.example.project.database.dao.DatabaseDao;
import com.example.project.model.ModelDatabase;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PembelianViewModel extends AndroidViewModel {
    private LiveData<List<ModelDatabase>> mPembelians;
    private DatabaseDao databaseDao;
    private LiveData<Integer> mTotalPrice;

    public PembelianViewModel(@NonNull Application application) {
        super(application);

        databaseDao = DatabaseClient.getInstance(application).getAppDatabase().databaseDao();
        mPembelians = databaseDao.getAllPembelian();
        mTotalPrice = databaseDao.getTotalPembelian();
    }

    public LiveData<List<ModelDatabase>> getPembelian() {
        return mPembelians;
    }

    public LiveData<Integer> getTotalPembelian() {
        return mTotalPrice;
    }

    public void deleteAllData() {
        Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        databaseDao.deleteAllPembelian();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public String deleteSingleData(final int uid) {
        String sKeterangan;
        try {
            Completable.fromAction(() -> databaseDao.deleteSinglePembelian(uid))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
            sKeterangan = "OK";
        } catch (Exception e) {
            sKeterangan = "NO";
        }
        return sKeterangan;
    }

}
