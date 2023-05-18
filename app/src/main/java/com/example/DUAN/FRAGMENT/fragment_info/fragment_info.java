package com.example.DUAN.FRAGMENT.fragment_info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.DUAN.ADAPTER.adapterThongTin.ADAPTER_ThongTin;
import com.example.DUAN.DAO.DAO_user;
import com.example.DUAN.R;

public class fragment_info extends Fragment {
    private RecyclerView rcvUser;
    private DAO_user dao;
    private ADAPTER_ThongTin adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvUser = (RecyclerView) view.findViewById(R.id.rcv_user);

        dao= new DAO_user(getContext());
        adapter= new ADAPTER_ThongTin(dao.selectAll(), dao);
        rcvUser.setAdapter(adapter);
    }
}
