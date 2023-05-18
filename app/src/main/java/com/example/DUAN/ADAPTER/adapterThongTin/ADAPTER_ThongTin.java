package com.example.DUAN.ADAPTER.adapterThongTin;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.DUAN.DAO.DAO_user;
import com.example.DUAN.DTO.DTO_user;
import com.example.DUAN.R;
import com.example.DUAN.viewHolder.viewholder_ThongTin;

import java.util.ArrayList;

public class ADAPTER_ThongTin extends RecyclerView.Adapter<viewholder_ThongTin> {
    ArrayList<DTO_user> list;
    DAO_user dao;
    private ImageView no;
    private ImageView yes;
    private TextView tvIdUser;
    private TextView tvUserName;
    private TextView tvNameUser;
    private TextView tvPhoneUser;
    private Button btn_ok;


    public ADAPTER_ThongTin(ArrayList<DTO_user> list, DAO_user dao) {
        this.list = list;
        this.dao = dao;
    }

    @NonNull
    @Override
    public viewholder_ThongTin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.item_user, parent, false);
        return new viewholder_ThongTin(row);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder_ThongTin holder, int position) {
        final int index= position;
        DTO_user obj= list.get(index);

        holder.tbNameUser.setText("Username: "+obj.getUsername_user());
        holder.tbCheckInfo.setOnClickListener(view->{
            showChiTiet_User(view.getContext(),obj.getId_user());
        });
        holder.imgDelete.setOnClickListener(view -> {
            Dialog dialog= new Dialog(view.getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
            dialog.setContentView(R.layout.delete_dialog);
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.customdialog);
            dialog.show();
            anhxa(dialog);
            yes.setOnClickListener(view1 -> {
                deleteUser(view1.getContext(), obj, index);
                dialog.dismiss();
            });
            no.setOnClickListener(view1 -> {
                dialog.dismiss();
            });
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void deleteUser(Context context, DTO_user obj, int index) {
        int res= dao.delete(obj);
        if(res>0){
            list.remove(index);
            notifyDataSetChanged();
            Toast.makeText(context, "Xóa Tài Khoản Thành Công", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Xóa Tài Khoản Thất Bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void showChiTiet_User(Context context, int ID) {
        Dialog dialog= new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.showuser);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.customdialog);
        dialog.show();
        anhxa(dialog);
        DTO_user obj= dao.showChiTiet_User(ID);
        tvIdUser.setText("ID User: "+obj.getId_user());
        tvUserName.setText("Username: "+obj.getUsername_user());
        tvNameUser.setText("Full Name: "+obj.getHoTen());
        tvPhoneUser.setText("Phone: "+obj.getSoDienThoai());
        btn_ok.setOnClickListener(view->{
            dialog.dismiss();
        });
    }

    private void anhxa(Dialog dialog){
        no = (ImageView) dialog.findViewById(R.id.no);
        yes = (ImageView) dialog.findViewById(R.id.yes);

        tvIdUser = (TextView) dialog.findViewById(R.id.tv_idUser);
        tvUserName = (TextView) dialog.findViewById(R.id.tv_userName);
        tvNameUser = (TextView) dialog.findViewById(R.id.tv_nameUser);
        tvPhoneUser = (TextView) dialog.findViewById(R.id.tv_phone_user);
        btn_ok= dialog.findViewById(R.id.btn_ok);
    }

}
