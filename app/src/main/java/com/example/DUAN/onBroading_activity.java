package com.example.DUAN;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.DUAN.FRAGMENT.onBroading_Fragment;

public class onBroading_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_broading);
        repalceFragment(new onBroading_Fragment());
    }
    private void repalceFragment(Fragment fragment){
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.onbraoding_content,fragment);
        transaction.commit();
    }
}