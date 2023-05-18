package com.example.DUAN.FRAGMENT;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.DUAN.ADAPTER.onBroading_adpater;
import com.example.DUAN.LOGIN.login_user;
import com.example.DUAN.R;
import com.example.DUAN.amination.DepthPageTransformer;

import me.relex.circleindicator.CircleIndicator3;

public class onBroading_Fragment extends Fragment {
    private ViewPager2 viewpageOnbroading;
    private CircleIndicator3 CICircle;
    private ImageView skip;
    private onBroading_adpater adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_on_broading_,container,false);

        viewpageOnbroading = (ViewPager2) myView.findViewById(R.id.viewpage_onbroading);
        CICircle = (CircleIndicator3) myView.findViewById(R.id.CI_circle);
        skip= myView.findViewById(R.id.skip);

        skip.setOnClickListener(view -> {
            Intent intent= new Intent(getContext(), login_user.class);
            startActivity(intent);
        });

        adapter= new onBroading_adpater(this);
        viewpageOnbroading.setPageTransformer(new DepthPageTransformer());
        viewpageOnbroading.setAdapter(adapter);

        CICircle.setViewPager(viewpageOnbroading);

/*
        CIImgnext.setOnClickListener(view->{
            if(viewpageOnbroading.getCurrentItem()<2){
                viewpageOnbroading.setCurrentItem(viewpageOnbroading.getCurrentItem()+1);
            }
            if (viewpageOnbroading.getCurrentItem()==2){
                CIImgnext.setVisibility(View.GONE);
                CICircle.setVisibility(View.GONE);
            }
        });
*/

        CICircle.createIndicators(3,0);
        CICircle.animatePageSelected(2);

        return myView;
    }
}