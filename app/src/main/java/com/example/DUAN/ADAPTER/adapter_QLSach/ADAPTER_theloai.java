package com.example.DUAN.ADAPTER.adapter_QLSach;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.DUAN.DAO.DAO_theLoai;
import com.example.DUAN.DTO.DTO_theLoai;
import com.example.DUAN.R;
import com.example.DUAN.viewHolder.viewholder_TheLoaiSach;

import java.util.ArrayList;

public class ADAPTER_theloai extends RecyclerView.Adapter<viewholder_TheLoaiSach> {
    DAO_theLoai dao;
    ArrayList<DTO_theLoai> list;

    private ImageView no,yes;
    private EditText edTentheloai,edMoTa,edViTri;
    private Button btnSave,btnOk,btnExit;
    private TextView tvMatlsach,tvTheloai,tvMota,tvVitri;
    private String theLoai,moTa,viTri;
    
    public ADAPTER_theloai(DAO_theLoai dao, ArrayList<DTO_theLoai> list) {
        this.dao = dao;
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder_TheLoaiSach onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View row= inflater.inflate(R.layout.item_theloaisach,parent,false);
        return new viewholder_TheLoaiSach(row);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder_TheLoaiSach holder, int position) {
        final int index= position;
        DTO_theLoai obj= list.get(index);

        holder.tvTieuDe.setText(obj.getTenTheLoai());
        holder.img_showchitiet.setOnClickListener(view->{
            showdialog_showChiTiet(view.getContext(), obj.getMaTheLoai());
        });
        holder.imgEdit.setOnClickListener(view -> {
            showdialog_editTheLoaiSach(view.getContext(), obj, index);
        });
        holder.imgDelete.setOnClickListener(view -> {
            Dialog dialog= new Dialog(view.getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.customdialog);
            dialog.setContentView(R.layout.delete_dialog);
            anhxa(dialog);
            dialog.show();
            yes.setOnClickListener(view1 -> {
                showdialog_delete(view1.getContext(), obj, index);
                dialog.dismiss();
            });
            no.setOnClickListener(view1 -> {
                dialog.dismiss();
            });
        });
    }

    private void showdialog_delete(Context context, DTO_theLoai obj, int index) {
        int res= dao.delete(obj);
        if(res>0){
            list.remove(index);
            notifyDataSetChanged();
            Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Xóa Không Thành Công", Toast.LENGTH_SHORT).show();
        }
    }

    private void showdialog_editTheLoaiSach(Context context, DTO_theLoai obj, int index) {
        Dialog dialog= new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.customdialog);
        dialog.setContentView(R.layout.edit_theloaisachdialog);
        dialog.show();
        anhxa(dialog);
        edMoTa.setText(obj.getMoTa());
        edTentheloai.setText(obj.getTenTheLoai());
        edViTri.setText(obj.getViTri());

        btnSave.setOnClickListener(view -> {
            getValueEdittext();
            obj.setTenTheLoai(theLoai);
            obj.setViTri(viTri);
            obj.setMoTa(moTa);
            long res= dao.update(obj);
            if(res>0){
                notifyDataSetChanged();
                list.set(index,obj);
                Toast.makeText(context, "Sửa Thể Loại Thành Công", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Sửa Thể Loại Thất Bại", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
        });
        btnExit.setOnClickListener(view -> {
            dialog.dismiss();
        });
    }

    public void showdialog_addTheLoaiSach(Context context) {
        Dialog dialog= new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.customdialog);
        dialog.setContentView(R.layout.add_theloaisachdialog);
        dialog.show();
        anhxa(dialog);
        btnSave.setOnClickListener(view -> {
            getValueEdittext();
            DTO_theLoai obj= new DTO_theLoai();
            obj.setTenTheLoai(theLoai);
            obj.setViTri(viTri);
            obj.setMoTa(moTa);
            long res= dao.insert(obj);
            if(res>0){
                notifyDataSetChanged();
                list.clear();
                list.addAll(dao.selectAll());
                Toast.makeText(context, "Thêm Thể Loại Thành Công", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Thêm Thể Loại Thất Bại", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
        });
        btnExit.setOnClickListener(view -> {
            dialog.dismiss();
        });
    }

    private void showdialog_showChiTiet(Context context, int id) {
        Dialog dialog= new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.showtheloaisach_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.customdialog);
        dialog.show();
        anhxa(dialog);
        DTO_theLoai obj= dao.showChiTiet(id);
        tvMatlsach.setText("Mã Thể Loại: "+obj.getMaTheLoai());
        tvTheloai.setText("Thể Loại: "+obj.getTenTheLoai());
        tvMota.setText("Mô Tả: "+obj.getMoTa());
        tvVitri.setText("Vị Trí: "+obj.getViTri()+"");
        btnOk.setOnClickListener(view->{
            dialog.dismiss();
        });
    }
    
    @Override
    public int getItemCount() {
        return list.size();
    }

    private void anhxa(Dialog dialog){
        no = (ImageView) dialog.findViewById(R.id.no);
        yes = (ImageView) dialog.findViewById(R.id.yes);

        edTentheloai = (EditText) dialog.findViewById(R.id.ed_tentheloai);
        edMoTa = (EditText) dialog.findViewById(R.id.ed_moTa);
        edViTri = (EditText) dialog.findViewById(R.id.ed_viTri);
        btnSave = (Button) dialog.findViewById(R.id.btn_save);
        btnExit= dialog.findViewById(R.id.btn_exit);

        tvMatlsach = (TextView) dialog.findViewById(R.id.tv_matlsach);
        tvTheloai = (TextView) dialog.findViewById(R.id.tv_theloai);
        tvMota = (TextView) dialog.findViewById(R.id.tv_mota);
        tvVitri = (TextView) dialog.findViewById(R.id.tv_vitri);
        btnOk = (Button) dialog.findViewById(R.id.btn_ok);
    }
    private void getValueEdittext(){
        theLoai= edTentheloai.getText().toString();
        moTa= edMoTa.getText().toString();
        viTri=edViTri.getText().toString();
    }
}
