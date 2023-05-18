package com.example.DUAN.FRAGMENT.fragment_QLHoaDon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.DUAN.ADAPTER.adapter_QLHoadon.ADAPTER_HoaDon;
import com.example.DUAN.DAO.DAO_hoaDon;
import com.example.DUAN.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class fragment_HoaDon extends Fragment {
    private RecyclerView lvHoadon;
    private FloatingActionButton btnAdd;
    private ADAPTER_HoaDon adapter;
    private DAO_hoaDon dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hoadon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvHoadon = (RecyclerView) view.findViewById(R.id.lv_hoadon);
        btnAdd = (FloatingActionButton) view.findViewById(R.id.btn_add);
        dao = new DAO_hoaDon(getContext());
        adapter = new ADAPTER_HoaDon(dao.selectAll(), dao);
        btnAdd.setOnClickListener(view1 -> {
            adapter.showDiaLog_AddHD(getContext());
        });
        lvHoadon.setAdapter(adapter);
    }
}
