package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.MenuItem;
import java.util.ArrayList;

import java.util.ArrayList;

public class Montir extends AppCompatActivity {

    SearchView searchView;
    RecyclerView recyclerView;
    ArrayList<ModelClass> arrayList = new ArrayList<>();
    ArrayList<ModelClass> searchList;

    String[] listMontir = new String[]{"Arham", "Muhammad Fikri", "Ilham", "Muhammad Kurniawan",
    "M. Resky Pahlevi", "Farhan", "Ikbal", "Rasfiawal", "Syahrul", "Taufiq Hidayat"};
    String[] numMontir = new String[]{"Montir 1", "Montir 2", "Montir 3", "Montir 4",
            "Montir 5", "Montir 6", "Montir 7", "Montir 8", "Montir 9", "Montir 10"};
    int[] listImg = new int[]{R.drawable.montir1, R.drawable.montir2, R.drawable.montir3, R.drawable.montir4,
            R.drawable.montir5, R.drawable.montir6, R.drawable.montir7, R.drawable.montir8, R.drawable.montir9, R.drawable.montir10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_montir);

        Toolbar toolbar = findViewById(R.id.toolbar);
        //getSupportActionBar().setTitle("Montir");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);
        searchView.setIconified(false);
        searchView.clearFocus();

        for (int i=0; i<listMontir.length; i++){
            ModelClass modelClass = new ModelClass();
            modelClass.setNamaMontir(listMontir[i]);
            modelClass.setNumMontir(numMontir[i]);
            modelClass.setImg(listImg[i]);
            arrayList.add(modelClass);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Montir.this);
        recyclerView.setLayoutManager(layoutManager);

        MontirAdapter montirAdapter = new MontirAdapter(Montir.this,arrayList);
        recyclerView.setAdapter(montirAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchList = new ArrayList<>();

                if (query.length()>0){
                    for (int i = 0; i < arrayList.size(); i++) {
                        if (arrayList.get(i).getNamaMontir().toUpperCase().contains(query.toUpperCase()) || arrayList.get(i).getNumMontir().toUpperCase().contains(query.toUpperCase())){
                            ModelClass modelClass = new ModelClass();
                            modelClass.setNamaMontir(arrayList.get(i).getNamaMontir());
                            modelClass.setNumMontir(arrayList.get(i).getNumMontir());
                            modelClass.setImg(arrayList.get(i).getImg());
                            searchList.add(modelClass);
                        }
                    }
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Montir.this);
                    recyclerView.setLayoutManager(layoutManager);

                    MontirAdapter montirAdapter = new MontirAdapter(Montir.this,searchList);
                    recyclerView.setAdapter(montirAdapter);
                }
                else {
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Montir.this);
                    recyclerView.setLayoutManager(layoutManager);

                    MontirAdapter montirAdapter = new MontirAdapter(Montir.this,arrayList);
                    recyclerView.setAdapter(montirAdapter);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList = new ArrayList<>();

                if (newText.length()>0){
                    for (int i = 0; i < arrayList.size(); i++) {
                        if (arrayList.get(i).getNamaMontir().toUpperCase().contains(newText.toUpperCase()) || arrayList.get(i).getNumMontir().toUpperCase().contains(newText.toUpperCase())){
                            ModelClass modelClass = new ModelClass();
                            modelClass.setNamaMontir(arrayList.get(i).getNamaMontir());
                            modelClass.setNumMontir(arrayList.get(i).getNumMontir());
                            modelClass.setImg(arrayList.get(i).getImg());
                            searchList.add(modelClass);
                        }
                    }
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Montir.this);
                    recyclerView.setLayoutManager(layoutManager);

                    MontirAdapter montirAdapter = new MontirAdapter(Montir.this,searchList);
                    recyclerView.setAdapter(montirAdapter);
                }
                else {
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Montir.this);
                    recyclerView.setLayoutManager(layoutManager);

                    MontirAdapter montirAdapter = new MontirAdapter(Montir.this,arrayList);
                    recyclerView.setAdapter(montirAdapter);
                }
                return false;
            }
        });
    }
}