package com.example.DUAN.ADAPTER;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.DUAN.FRAGMENT.onBroading_Fragment01;
import com.example.DUAN.FRAGMENT.onBroading_Fragment02;
import com.example.DUAN.FRAGMENT.onBroading_Fragment03;

public class onBroading_adpater extends FragmentStateAdapter {
    public onBroading_adpater(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==0){
            return new onBroading_Fragment01();
        }else if(position==1){
            return new onBroading_Fragment02();
        }else{
            return new onBroading_Fragment03();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
