package com.example.DUAN;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.DUAN.DAO.DAO_user;
import com.example.DUAN.FRAGMENT.fragmenr_ThongKe.fragment_ThongKe;
import com.example.DUAN.FRAGMENT.fragment_QLHoaDon.fragment_QLHoaDon;
import com.example.DUAN.FRAGMENT.fragment_info.fragment_info;
import com.example.DUAN.FRAGMENT.fragmentsach.fragment_QLSach;
import com.example.DUAN.LOGIN.signin_user;
import com.example.DUAN.LOGIN.login_user;
import com.example.DUAN.LOGIN.changePass_user;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private DAO_user dao;
    private static final int Fragment_home = 0;
    private static final int Fragment_quanlyhd = 1;
    private static final int Fragment_thongke = 2;
    private static final int Fragment_thongtin = 3;
    private int mCurrentFragment = Fragment_home;
    BottomNavigationView bottomnavi;
    Toolbar toolbar;
    int id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new DAO_user(this);
        Intent intent = getIntent();
        id_user = intent.getIntExtra("id_user", 0);

        bottom_navi();
        setSupportActionBar(toolbar);
        replaceFragment(new fragment_QLSach());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }

    private void bottom_navi() {
        bottomnavi = findViewById(R.id.bottom_navi);
        toolbar = findViewById(R.id.toolbar);
        bottomnavi.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_home) {
                if (mCurrentFragment != Fragment_home) ;
                replaceFragment(new fragment_QLSach());
                mCurrentFragment = Fragment_home;
                toolbar.setTitle("Quản Lý Sách");

            } else if (id == R.id.menu_qlHoaDon) {
                if (mCurrentFragment != Fragment_quanlyhd) ;
                replaceFragment(new fragment_QLHoaDon());
                mCurrentFragment = Fragment_quanlyhd;
                toolbar.setTitle("Quản Lý Hóa Đơn");

            } else if (id == R.id.menu_ThongKe) {
                if (mCurrentFragment != Fragment_thongke) ;
                replaceFragment(new fragment_ThongKe());
                mCurrentFragment = Fragment_thongke;
                toolbar.setTitle("Thống Kê");
            } else if (id == R.id.menu_ThongTin) {
                if (mCurrentFragment != Fragment_thongtin) ;
                replaceFragment(new fragment_info());
                mCurrentFragment = Fragment_thongtin;
                toolbar.setTitle("Quản Lý Tài Khoản" +
                        "");
            }
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                Intent intent = new Intent(getBaseContext(), signin_user.class);
                startActivity(intent);
                break;
            case R.id.menu_updatePass:
                Intent intent1 = new Intent(getBaseContext(), changePass_user.class);
                intent1.putExtra("id_user", id_user);
                startActivity(intent1);
                break;
            case R.id.menu_dangXuat:
                Intent intent2 = new Intent(getBaseContext(), login_user.class);
                startActivity(intent2);
                break;
            case R.id.menu_baithi:
                Intent intent3 = new Intent(getBaseContext(), BaiThiActivity.class);
                startActivity(intent3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}