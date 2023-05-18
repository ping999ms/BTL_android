package com.example.DUAN.Test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.DUAN.R;

public class testCommitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_commit);
        Toast.makeText(this, "ahahahha", Toast.LENGTH_SHORT).show();
    }
}