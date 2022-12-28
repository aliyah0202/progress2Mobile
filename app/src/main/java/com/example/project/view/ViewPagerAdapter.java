package com.example.project.view;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.project.view.fragment.penjualan.PenjualanFragment;
import com.example.project.view.fragment.pembelian.PembelianFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter{
    public ViewPagerAdapter(@NonNull FragmentManager manager) {
        super(manager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new PembelianFragment();
                break;
            case 1:
                fragment = new PenjualanFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String strTitle = "";
        switch (position) {
            case 0:
                strTitle = "Pembelian";
                break;
            case 1:
                strTitle = "Penjualan";
                break;
        }
        return strTitle;
    }
}
