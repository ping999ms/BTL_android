package com.example.DUAN.ADAPTER.adapter_QLHoadon;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.DUAN.FRAGMENT.fragment_QLHoaDon.fragment_HoaDon;
import com.example.DUAN.FRAGMENT.fragment_QLHoaDon.fragment_HoaDonChiTiet;
import com.example.DUAN.FRAGMENT.fragmentsach.fragment_sach;
import com.example.DUAN.FRAGMENT.fragmentsach.fragment_theloaisach;

public class ADAPTER_qlHoaDon extends FragmentStateAdapter {
    public ADAPTER_qlHoaDon(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return new fragment_HoaDon();
        }
        return new fragment_HoaDonChiTiet();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
