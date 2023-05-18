package com.example.DUAN.LOGIN;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.DUAN.DAO.DAO_user;
import com.example.DUAN.DTO.DTO_user;
import com.example.DUAN.R;
import com.google.android.material.textfield.TextInputLayout;

public class signin_user extends AppCompatActivity {
    private TextInputLayout edUserUser;
    private TextInputLayout edPassUser;
    private TextInputLayout edNameUser;
    private TextInputLayout edSodtUser;
    private Button btnLogin;
    private DAO_user dao;
    boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_thuthu);
        anhxa();
        dao = new DAO_user(signin_user.this);
        btnLogin.setOnClickListener(view -> {
            if (!check == dao.checkUserName(edUserUser.getEditText().getText().toString())) {
                edUserUser.setError("Tên Tài Khoản Đã Có Người Dùng");
            } else if (checkUser()) {
                DTO_user obj = new DTO_user();
                obj.setUsername_user(edUserUser.getEditText().getText().toString());
                obj.setPass_user(edPassUser.getEditText().getText().toString());
                obj.setHoTen(edNameUser.getEditText().getText().toString());
                obj.setSoDienThoai(edSodtUser.getEditText().getText().toString());
                long res = dao.insert(obj);
                if (res > 0) {
                    Toast.makeText(this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, login_user.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Đăng Ký Không Thành Công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void anhxa() {
        edUserUser = (TextInputLayout) findViewById(R.id.ed_usernameUser);
        edPassUser = (TextInputLayout) findViewById(R.id.ed_passUser);
        edNameUser = (TextInputLayout) findViewById(R.id.ed_nameUser);
        edSodtUser = (TextInputLayout) findViewById(R.id.ed_sodtUser);
        btnLogin = (Button) findViewById(R.id.btn_login);
    }

    private boolean checkUser() {
        if (edUserUser.getEditText().getText().length() <= 6) {
            edUserUser.setError("Tên Tài Khoản Trên 6 Kí Tự");
        } else if (edPassUser.getEditText().getText().length() <= 6) {
            edPassUser.setError("Mật Khẩu Trên 6 Kí Tự");
            edUserUser.setErrorEnabled(false);
        } else if (edNameUser.getEditText().getText().toString().isEmpty()) {
            edNameUser.setError("Họ và Tên Không Hợp Lệ");
            edPassUser.setErrorEnabled(false);
        } else if (edSodtUser.getEditText().getText().length() != 10) {
            edSodtUser.setError("Số Điện Thoại Đủ 10 số");
            edNameUser.setErrorEnabled(false);
        } else {
            edSodtUser.setErrorEnabled(false);
            return true;
        }
        return false;
    }
}
