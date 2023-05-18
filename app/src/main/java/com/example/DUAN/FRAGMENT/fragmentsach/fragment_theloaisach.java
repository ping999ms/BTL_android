package com.example.DUAN.FRAGMENT.fragmentsach;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.DUAN.ADAPTER.adapter_QLSach.ADAPTER_theloai;
import com.example.DUAN.DAO.DAO_theLoai;
import com.example.DUAN.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class fragment_theloaisach extends Fragment {
    private RecyclerView lvTheloaisach;
    private FloatingActionButton btnAddTheLoaiSach;
    ADAPTER_theloai adapter;
    DAO_theLoai dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_theloaisach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvTheloaisach = (RecyclerView) view.findViewById(R.id.lv_theloaisach);
        btnAddTheLoaiSach = (FloatingActionButton) view.findViewById(R.id.btn_addTheLoaiSach);
        dao= new DAO_theLoai(getContext());
        btnAddTheLoaiSach.setOnClickListener(view1 -> {
            adapter.showdialog_addTheLoaiSach(view1.getContext());
        });
        adapter= new ADAPTER_theloai(dao,dao.selectAll());
        lvTheloaisach.setAdapter(adapter);
    }
}
