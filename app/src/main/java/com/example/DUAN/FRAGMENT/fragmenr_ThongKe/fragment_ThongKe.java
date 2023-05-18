package com.example.DUAN.FRAGMENT.fragmenr_ThongKe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.DUAN.ADAPTER.adapterThongKe.ADAPTER_ThongKe;
import com.example.DUAN.DAO.DAO_sach;
import com.example.DUAN.DAO.DAO_thongKe;
import com.example.DUAN.DTO.DTO_thongKe;
import com.example.DUAN.R;

public class fragment_ThongKe extends Fragment {
    private RecyclerView lvTopSach;
    private TextView tvTienHomNay;
    private TextView tvTienThangNay;
    private TextView tvTienNamNay;
    ADAPTER_ThongKe adapter_thongKe;
    DAO_thongKe dao_thongKe;
    DAO_sach dao_sach;
    DTO_thongKe obj;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thongke,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXa(view);
        dao_sach= new DAO_sach(getContext());
        dao_thongKe= new DAO_thongKe(getContext());
        adapter_thongKe= new ADAPTER_ThongKe(dao_thongKe.getTop10(getContext()),dao_thongKe);
        lvTopSach.setAdapter(adapter_thongKe);
        obj= new DTO_thongKe();

        obj.setDoanhThu(dao_thongKe.getDoanhThuTheoNgay());
        tvTienHomNay.setText("Doanh Thu Theo Ngày: "+obj.getDoanhThu());

        obj.setDoanhThu(dao_thongKe.getDoanhThuTheoThang());
        tvTienThangNay.setText("Doanh Thu Theo Tháng: "+obj.getDoanhThu());

        obj.setDoanhThu(dao_thongKe.getDoanhThuTheoNam());
        tvTienNamNay.setText("Doanh Thu Theo Năm: "+obj.getDoanhThu());
    }
    private void anhXa(View view){

        lvTopSach = (RecyclerView) view.findViewById(R.id.lv_topSach);
        tvTienHomNay = (TextView) view.findViewById(R.id.tv_tienHomNay);
        tvTienThangNay = (TextView) view.findViewById(R.id.tv_tienThangNay);
        tvTienNamNay = (TextView) view.findViewById(R.id.tv_tienNamNay);

    }
}
