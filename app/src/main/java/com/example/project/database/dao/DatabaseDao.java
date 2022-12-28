package com.example.project.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.project.model.ModelDatabase;

import java.util.List;

@Dao
public interface DatabaseDao {
    //Data Pembelian
    @Query("SELECT * FROM tbl_keuangan WHERE tipe = 'pembelian'")
    LiveData<List<ModelDatabase>> getAllPembelian();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPembelian(ModelDatabase... pembelian);

    @Query("DELETE FROM tbl_keuangan WHERE tipe = 'pembelian'")
    void deleteAllPembelian();

    @Query("DELETE FROM tbl_keuangan WHERE uid= :uid and tipe = 'pembelian'")
    void deleteSinglePembelian(int uid);

    @Query("SELECT SUM(jml_uang) FROM tbl_keuangan WHERE tipe = 'pembelian'")
    LiveData<Integer> getTotalPembelian();

    @Query("UPDATE tbl_keuangan SET keterangan = :keterangan, tanggal = :tgl, jml_uang = :harga WHERE uid = :uid and tipe = 'pembelian'")
    void updateDataPembelian(String keterangan, String tgl, int harga, int uid);

    //Data Penjualan
    @Query("SELECT * FROM tbl_keuangan WHERE tipe = 'penjualan'")
    LiveData<List<ModelDatabase>> getAllPenjualan();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPenjualan(ModelDatabase... penjualan);

    @Query("DELETE FROM tbl_keuangan WHERE tipe = 'penjualan'")
    void deleteAllPenjualan();

    @Query("DELETE FROM tbl_keuangan WHERE uid= :uid and tipe = 'penjualan'")
    void deleteSinglePenjualan(int uid);

    @Query("SELECT SUM(jml_uang) FROM tbl_keuangan WHERE tipe = 'penjualan'")
    LiveData<Integer> getTotalPenjualan();

    @Query("UPDATE tbl_keuangan SET keterangan = :keterangan, tanggal = :tgl, jml_uang = :harga WHERE uid = :uid and tipe = 'penjualan'")
    void updateDataPenjualan(String keterangan, String tgl, int harga, int uid);
}
