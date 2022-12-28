package com.example.project.view.fragment.penjualan;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.project.database.DatabaseClient;
import com.example.project.database.dao.DatabaseDao;
import com.example.project.model.ModelDatabase;

import java.util.List;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PenjualanViewModel extends AndroidViewModel{
    private LiveData<List<ModelDatabase>> mPenjualan;
    private DatabaseDao databaseDao;
    private LiveData<Integer> mTotalPrice;

    public PenjualanViewModel(@NonNull Application application) {
        super(application);

        databaseDao = DatabaseClient.getInstance(application).getAppDatabase().databaseDao();
        mPenjualan = databaseDao.getAllPenjualan();
        mTotalPrice = databaseDao.getTotalPenjualan();
    }
    public LiveData<List<ModelDatabase>> getPenjualan() {
        return mPenjualan;
    }

    public LiveData<Integer> getTotalPenjualan() {
        return mTotalPrice;
    }
    public void deleteAllData() {
        Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        databaseDao.deleteAllPenjualan();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public String deleteSingleData(final int uid) {
        String sKeterangan;
        try {
            Completable.fromAction(() -> databaseDao.deleteSinglePenjualan(uid))
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
