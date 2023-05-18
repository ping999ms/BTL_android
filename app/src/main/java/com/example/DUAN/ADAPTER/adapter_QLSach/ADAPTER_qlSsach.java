package com.example.DUAN.ADAPTER.adapter_QLSach;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.DUAN.FRAGMENT.fragmentsach.fragment_sach;
import com.example.DUAN.FRAGMENT.fragmentsach.fragment_theloaisach;

public class ADAPTER_qlSsach extends FragmentStateAdapter {
    public ADAPTER_qlSsach(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return new fragment_sach();
        }
        return new fragment_theloaisach();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
