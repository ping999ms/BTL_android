package com.example.DUAN.LOGIN;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.DUAN.DAO.DAO_user;
import com.example.DUAN.DTO.DTO_user;
import com.example.DUAN.MainActivity;
import com.example.DUAN.R;
import com.google.android.material.textfield.TextInputLayout;

public class login_user extends AppCompatActivity {

    private TextInputLayout edUser;
    private TextInputLayout edPass;
    private CheckBox ckSavePass;
    private Button btnSignin;
    private TextView tvLogin;
    private DAO_user dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_thuthu);
        anhxa();
        getData();
        dao = new DAO_user(this);
        btnSignin.setOnClickListener(view -> {
            String user = edUser.getEditText().getText().toString();
            String pass = edPass.getEditText().getText().toString();

            boolean check = dao.check(user, pass);
            if (check) {
                Toast.makeText(this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                setData();
                DTO_user obj = dao.layTaiKhoan(edUser.getEditText().getText().toString(), edPass.getEditText().getText().toString());

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("id_user", obj.getId_user());

                startActivity(intent);
            } else {
                Toast.makeText(this, "Unknow Account!", Toast.LENGTH_SHORT).show();
            }
        });

        tvLogin = (TextView) findViewById(R.id.tv_login);
        tvLogin.setOnClickListener(view -> {

            Intent intent = new Intent(this, signin_user.class);
            startActivity(intent);
        });
    }

    public void setData() {
        SharedPreferences preferences = getSharedPreferences("signinUser", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String user = edUser.getEditText().getText().toString();
        String pass = edPass.getEditText().getText().toString();
        boolean check = ckSavePass.isChecked();
        if (!check) {
            editor.putString("user", user);
            editor.putString("pass", "");
            editor.putBoolean("check", false);
        } else {
            editor.putString("user", user);
            editor.putString("pass", pass);
            editor.putBoolean("check", true);
        }
        editor.commit();
    }

    public void getData() {
        SharedPreferences preferences = getSharedPreferences("signinUser", MODE_PRIVATE);
        boolean check = preferences.getBoolean("check", false);
        edUser.getEditText().setText(preferences.getString("user", ""));
        edPass.getEditText().setText(preferences.getString("pass", ""));
        ckSavePass.setChecked(check);
    }

    private void anhxa() {
        edUser = (TextInputLayout) findViewById(R.id.ed_user);
        edPass = (TextInputLayout) findViewById(R.id.ed_pass);
        ckSavePass = (CheckBox) findViewById(R.id.ck_savePass);
        btnSignin = (Button) findViewById(R.id.btn_Signin);
        tvLogin = (TextView) findViewById(R.id.tv_login);
    }

}