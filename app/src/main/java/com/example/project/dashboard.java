package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import com.example.project.view.Penjualan;

public class dashboard extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);
        
    }

    private void setSingleEvent(GridLayout mainGrid) {
        for (int i=0; i < mainGrid.getChildCount(); i++){
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalI == 0){
                        Intent intent = new Intent(dashboard.this, login.Produk.class);
                        startActivity(intent);
                    }
                    if (finalI == 1){
                        Intent intent = new Intent(dashboard.this, Pelanggan.class);
                        startActivity(intent);
                    }
                    if (finalI == 2){
                        Intent intent = new Intent(dashboard.this, Pemasok.class);
                        startActivity(intent);
                    }
                    if (finalI == 3){
                        Intent intent = new Intent(dashboard.this, Penjualan.class);
                        startActivity(intent);
                    }
                    if (finalI == 4){
                        Intent intent = new Intent(dashboard.this, Montir.class);
                        startActivity(intent);
                    }
                    if (finalI == 5){
                        Intent intent = new Intent(dashboard.this, CariBengkel.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}