package com.example.DUAN.FRAGMENT.fragment_QLHoaDon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.DUAN.ADAPTER.adapter_QLHoadon.ADAPTER_hdct;
import com.example.DUAN.DAO.DAO_hdct;
import com.example.DUAN.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class fragment_HoaDonChiTiet extends Fragment {
    private RecyclerView lvHoadonchitiet;
    private FloatingActionButton btnAdd;
    private ADAPTER_hdct adapter_hdct;
    private DAO_hdct dao_hdct;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hoadonchitiet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvHoadonchitiet = (RecyclerView) view.findViewById(R.id.lv_hoadonchitiet);
        btnAdd = (FloatingActionButton) view.findViewById(R.id.btn_add);
        dao_hdct= new DAO_hdct(getContext());
        btnAdd.setOnClickListener(view1 -> {
            adapter_hdct.showDiaLog_AddHDCT(getContext());
        });
        adapter_hdct= new ADAPTER_hdct(dao_hdct, dao_hdct.selectAll());
        lvHoadonchitiet.setAdapter(adapter_hdct);
    }
}
