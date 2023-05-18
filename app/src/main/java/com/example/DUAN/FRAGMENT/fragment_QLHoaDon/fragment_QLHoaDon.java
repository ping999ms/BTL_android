package com.example.DUAN.FRAGMENT.fragment_QLHoaDon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.DUAN.ADAPTER.adapter_QLHoadon.ADAPTER_qlHoaDon;
import com.example.DUAN.R;
import com.example.DUAN.amination.DepthPageTransformer;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class fragment_QLHoaDon extends Fragment {
    private TabLayout toolbar;
    private ViewPager2 viewpager;
    private ADAPTER_qlHoaDon adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quanlyhoadon,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar = (TabLayout) view.findViewById(R.id.toolbar_qlhoadon);
        viewpager = (ViewPager2) view.findViewById(R.id.viewpager_qlhd);

        viewpager.setPageTransformer(new DepthPageTransformer());
        adapter= new ADAPTER_qlHoaDon(getActivity());
        viewpager.setAdapter(adapter);

        new TabLayoutMediator(toolbar,viewpager,(tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("HÓA ĐƠN");
                    break;
                case 1:
                    tab.setText("HÓA ĐƠN CHI TIẾT");
                    break;
            }
        } ).attach();
    }
}
