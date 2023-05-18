package com.example.DUAN.LOGIN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.DUAN.DAO.DAO_user;
import com.example.DUAN.DTO.DTO_user;
import com.example.DUAN.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class changePass_user extends AppCompatActivity {
    private TextInputLayout edPassCu;
    private TextInputLayout edPassMoi;
    private TextInputLayout edNhapLaiPass;
    private Button btnSignin;
    int id_user;
    DAO_user dao;
    ArrayList<DTO_user> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user);
        dao = new DAO_user(this);
        list = dao.selectAll();
        anhxa();
        Intent intent = getIntent();
        id_user = intent.getIntExtra("id_user", 0) ;
        btnSignin.setOnClickListener(view -> {
            boolean check = dao.checkPass(edPassCu.getEditText().getText().toString().trim());
            if (check == true) {
                kiemtra();
            }else {
                Toast.makeText(this, "Mật Khẩu Cũ Không Chính Xác", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void anhxa() {
        edPassCu = findViewById(R.id.ed_passCu);
        edPassMoi = findViewById(R.id.ed_passMoi);
        edNhapLaiPass = findViewById(R.id.ed_nhapLaiPass);
        btnSignin = (Button) findViewById(R.id.btn_Signin);
    }

    private void kiemtra() {
        String newPass = edPassMoi.getEditText().getText().toString();
        String reNewPass = edNhapLaiPass.getEditText().getText().toString();
        if (newPass.length() < 6) {
            edPassMoi.setError("Mật Khẩu Có Ít Nhất 6 Ký Tự");
        } else if (newPass.equals(reNewPass) == false) {
            edPassMoi.setErrorEnabled(false);
            edNhapLaiPass.setError("Mật Khẩu Không Trùng Khớp");
        } else {
            edNhapLaiPass.setErrorEnabled(false);
            DTO_user obj= list.get(id_user);
            obj.setPass_user(edPassMoi.getEditText().getText().toString().trim());
            int res= dao.update(obj);
            if(res>0){
                list.set(id_user, obj);
                Intent intent= new Intent(this, login_user.class);
                startActivity(intent);
                Toast.makeText(this, "Đổi Mật Khẩu Thành Công", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Đổi Mật Khẩu Thất Bại", Toast.LENGTH_SHORT).show();
            }
        }

    }
}