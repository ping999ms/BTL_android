package com.example.DUAN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ManHinhCho extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_cho);
        new Handler().postDelayed(() -> {
            Intent intent= new Intent(getBaseContext(), onBroading_activity.class);
            startActivity(intent);
            finish();
        },3000);
    }
}