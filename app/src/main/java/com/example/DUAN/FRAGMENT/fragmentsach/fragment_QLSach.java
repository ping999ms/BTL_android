package com.example.DUAN.FRAGMENT.fragmentsach;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.DUAN.ADAPTER.adapter_QLSach.ADAPTER_qlSsach;
import com.example.DUAN.R;
import com.example.DUAN.amination.DepthPageTransformer;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class fragment_QLSach extends Fragment {
    private TabLayout toolbarQlsach;
    private ViewPager2 viewpagerQlsach;
    private ADAPTER_qlSsach adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quanlysach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbarQlsach = (TabLayout) view.findViewById(R.id.toolbar_qlsach);
        viewpagerQlsach = (ViewPager2) view.findViewById(R.id.viewpager_qlsach);

        viewpagerQlsach.setPageTransformer(new DepthPageTransformer());
        adapter = new ADAPTER_qlSsach(getActivity());
        viewpagerQlsach.setAdapter(adapter);

        new TabLayoutMediator(toolbarQlsach, viewpagerQlsach, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("SÁCH");
                    break;
                case 1:
                    tab.setText("THỂ LOẠI");
                    break;
            }
        }).attach();
    }
}
