package com.example.DUAN.FRAGMENT.fragmentsach;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.DUAN.ADAPTER.adapter_QLSach.ADAPTER_sach;
import com.example.DUAN.DAO.DAO_sach;
import com.example.DUAN.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class fragment_sach extends Fragment {
    private RecyclerView lvSach;
    private FloatingActionButton btnAddSach;
    ADAPTER_sach adapter;
    DAO_sach dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvSach = (RecyclerView) view.findViewById(R.id.lv_sach);
        btnAddSach = (FloatingActionButton) view.findViewById(R.id.btn_addSach);
        dao = new DAO_sach(getContext());
        btnAddSach.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.add));
        btnAddSach.setOnClickListener(view1 -> {
            adapter.addSach(getContext());
        });
        adapter = new ADAPTER_sach(dao.selectAll(), dao);
        lvSach.setAdapter(adapter);
    }
}
