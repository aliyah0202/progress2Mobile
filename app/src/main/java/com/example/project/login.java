package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class login extends AppCompatActivity {

    Button callSignUp,login_btn;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout username, password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide status bar from the screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //Hooks
        callSignUp = findViewById(R.id.signup_screen);
        image = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.logo_name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDashboard();
            }
        });

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, SignUp.class);
                //startActivity(intent);
                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View,String>(image,"logo_image");
                pairs[1] = new Pair<View,String>(logoText,"logo_text");
                pairs[2] = new Pair<View,String>(sloganText,"logo_desc");
                pairs[3] = new Pair<View,String>(username,"username_tran");
                pairs[4] = new Pair<View,String>(password,"password_tran");
                pairs[5] = new Pair<View,String>(login_btn,"button_tran");
                pairs[6] = new Pair<View,String>(callSignUp,"login_signup_tran");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(login.this,pairs);
                startActivity(intent,options.toBundle());
            }
        });
    }
    public void openDashboard(){
        Intent intent = new Intent(this,dashboard.class);
        startActivity(intent);
    }

    public static class Produk extends AppCompatActivity {
        private static RecyclerView.Adapter adapter;
        private static RecyclerView.LayoutManager layoutManager;
        private static RecyclerView recyclerView;
        private static ArrayList<DataModel> data;
        static View.OnClickListener myOnClickListener;
        private static ArrayList<Integer> removedItems;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_produk);

            myOnClickListener = new MyOnClickListener(this);

            recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            data = new ArrayList<DataModel>();
            for (int i = 0; i < DataProduk.nameArray.length; i++) {
                data.add(new DataModel(
                        DataProduk.nameArray[i],
                        DataProduk.versionArray[i],
                        DataProduk.id_[i],
                        DataProduk.drawableArray[i]
                ));
            }
            removedItems = new ArrayList<Integer>();

            adapter = new ProdukAdapter(data);
            recyclerView.setAdapter(adapter);
        }
        private static class MyOnClickListener implements View.OnClickListener {

            private final Context context;

            private MyOnClickListener(Context context) {
                this.context = context;
            }

            @Override
            public void onClick(View v) {
                removeItem(v);
            }

            private void removeItem(View v) {
                int selectedItemPosition = recyclerView.getChildPosition(v);
                RecyclerView.ViewHolder viewHolder
                        = recyclerView.findViewHolderForPosition(selectedItemPosition);
                TextView textViewName
                        = (TextView) viewHolder.itemView.findViewById(R.id.textViewName);
                String selectedName = (String) textViewName.getText();
                int selectedItemId = -1;
                for (int i = 0; i < DataProduk.nameArray.length; i++) {
                    if (selectedName.equals(DataProduk.nameArray[i])) {
                        selectedItemId = DataProduk.id_[i];
                    }
                }
                removedItems.add(selectedItemId);
                data.remove(selectedItemPosition);
                adapter.notifyItemRemoved(selectedItemPosition);
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            super.onCreateOptionsMenu(menu);
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            super.onOptionsItemSelected(item);
            if (item.getItemId() == R.id.add_item) {
                //check if any items to add
                if (removedItems.size() != 0) {
                    addRemovedItemToList();
                } else {
                    Toast.makeText(this, "Nothing to add", Toast.LENGTH_SHORT).show();
                }
            }
            return true;
        }

        private void addRemovedItemToList() {
            int addItemAtListPosition = 3;
            data.add(addItemAtListPosition, new DataModel(
                    DataProduk.nameArray[removedItems.get(0)],
                    DataProduk.versionArray[removedItems.get(0)],
                    DataProduk.id_[removedItems.get(0)],
                    DataProduk.drawableArray[removedItems.get(0)]
            ));
            adapter.notifyItemInserted(addItemAtListPosition);
            removedItems.remove(0);
        }
    }
}