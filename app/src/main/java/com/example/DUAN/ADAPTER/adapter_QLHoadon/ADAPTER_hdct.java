package com.example.DUAN.ADAPTER.adapter_QLHoadon;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.DUAN.ADAPTER.adapter_QLSach.ADAPTER_spinner_sach;
import com.example.DUAN.DAO.DAO_hdct;
import com.example.DUAN.DAO.DAO_hoaDon;
import com.example.DUAN.DAO.DAO_sach;
import com.example.DUAN.DTO.DTO_hdct;
import com.example.DUAN.DTO.DTO_hoaDon;
import com.example.DUAN.DTO.DTO_sach;
import com.example.DUAN.R;
import com.example.DUAN.viewHolder.viewholder_HDCT;

import java.util.ArrayList;

public class ADAPTER_hdct extends RecyclerView.Adapter<viewholder_HDCT> {
    DAO_hdct dao;
    ArrayList<DTO_hdct> list;

    private Spinner spnIdHd;
    private Spinner spnIdSach;
    private EditText edSoLuong;
    private Button btnSave;
    private Button btnExit;


    private ImageView no;
    private ImageView yes;

    private DAO_hoaDon dao_hoaDon;
    private DAO_sach dao_sach;
    private ADAPTER_spinner_hoaDon spinner_hoaDon;
    private ADAPTER_spinner_sach spinner_sach;
    private DTO_hoaDon dto_hoaDon;
    private DTO_sach dto_sach;
    private DTO_hdct obj;

    public ADAPTER_hdct(DAO_hdct dao, ArrayList<DTO_hdct> list) {
        this.dao = dao;
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder_HDCT onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.item_hdct, parent, false);
        return new viewholder_HDCT(row);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder_HDCT holder, int position) {
        final int index = position;
        DTO_hdct obj = list.get(index);
        holder.tvIdHdct.setText("ID HĐCT: " + obj.getMaHDCT());
        holder.tvMaHd.setText("ID Hóa Đơn: " + obj.getMaHD());
        holder.tvIdSach.setText("ID Sách: " + obj.getMaSach());
        holder.tvSoluong.setText("Số Lượng Mua: " + obj.getSoLuongMua());

        holder.imgDelete.setOnClickListener(view -> {
            Dialog dialog = new Dialog(view.getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
            dialog.setContentView(R.layout.delete_dialog);
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.customdialog);
            dialog.show();
            anhxa(dialog);
            yes.setOnClickListener(view1 -> {
                dialog.dismiss();
                deleteHDCT(view.getContext(), obj, index);
            });
            no.setOnClickListener(view2 -> {
                dialog.dismiss();
            });
        });

        holder.imgEdit.setOnClickListener(view -> {
            showDiaLog_EditHDCT(view.getContext(), obj, index);
        });
    }

    private void deleteHDCT(Context context, DTO_hdct obj, int index) {
        int res = dao.delete(obj);
        if (res > 0) {
            notifyDataSetChanged();
            list.remove(index);
            Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Xóa Không Thành Công", Toast.LENGTH_SHORT).show();
        }
    }

    private void showDiaLog_EditHDCT(Context context, DTO_hdct obj, int index) {
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.edit_hdctdialog);
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.customdialog);
        anhxa(dialog);
        spinner(context);
        ArrayList<DTO_hoaDon> list_hd = dao_hoaDon.selectAll();
        ArrayList<DTO_sach> list_sach = dao_sach.selectAll();
        for (int x = 0; x < list_sach.size() - 1; x++) {
            dto_sach = list_sach.get(x);
            if (dto_sach.getMaSach() == obj.getMaSach()) {
                spnIdSach.setSelection(x);
                spnIdSach.setSelected(true);
            }
        }
        for (int y = 0; y < list_hd.size() - 1; y++) {
            dto_hoaDon = list_hd.get(y);
            if (dto_hoaDon.getMaHD() == obj.getMaHD()) {
                spnIdHd.setSelection(y);
                spnIdHd.setSelected(true);
            }
        }
        edSoLuong.setText(obj.getSoLuongMua() + "");
        btnSave.setOnClickListener(view -> {
            if (edSoLuong.getText().toString().isEmpty()) {
                edSoLuong.setError("Vui Lòng Nhập Dữ Liệu");
                edSoLuong.requestFocus();
            } else {
                dto_hoaDon = (DTO_hoaDon) spnIdHd.getSelectedItem();
                dto_sach = (DTO_sach) spnIdSach.getSelectedItem();
                obj.setMaHD(dto_hoaDon.getMaHD());
                obj.setMaSach(dto_sach.getMaSach());
                obj.setSoLuongMua(Integer.parseInt(edSoLuong.getText().toString()));
                long res = dao.update(obj);
                if (res > 0) {
                    notifyDataSetChanged();
                    Toast.makeText(context, "Sửa Thành Công", Toast.LENGTH_SHORT).show();
                    list.set(index, obj);
                } else {
                    Toast.makeText(context, "Sửa Thất Bại", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        btnExit.setOnClickListener(view -> {
            dialog.dismiss();
        });
    }

    public void showDiaLog_AddHDCT(Context context) {
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.add_hdctdialog);
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.customdialog);
        anhxa(dialog);
        spinner(context);
        btnSave.setOnClickListener(view -> {

            if (edSoLuong.getText().toString().isEmpty()) {
                edSoLuong.setError("Vui Lòng Nhập Dữ Liệu");
                edSoLuong.requestFocus();
            } else {
                obj = new DTO_hdct();
                dto_hoaDon = (DTO_hoaDon) spnIdHd.getSelectedItem();
                dto_sach = (DTO_sach) spnIdSach.getSelectedItem();
                obj.setMaSach(dto_sach.getMaSach());
                obj.setMaHD(dto_hoaDon.getMaHD());
                obj.setSoLuongMua(Integer.parseInt(edSoLuong.getText().toString()));

                long res = dao.insert(obj);
                if (res > 0) {
                    notifyDataSetChanged();
                    list.clear();
                    list.addAll(dao.selectAll());
                    Toast.makeText(context, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Thêm Không Thành Công", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        btnExit.setOnClickListener(view -> {
            dialog.dismiss();
        });
    }

    private void spinner(Context context) {
        dao_hoaDon = new DAO_hoaDon(context);
        dao_sach = new DAO_sach(context);
        spinner_sach = new ADAPTER_spinner_sach(dao_sach.selectAll());
        spinner_hoaDon = new ADAPTER_spinner_hoaDon(dao_hoaDon.selectAll());
        spnIdSach.setAdapter(spinner_sach);
        spnIdHd.setAdapter(spinner_hoaDon);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void anhxa(Dialog dialog) {

        spnIdHd = (Spinner) dialog.findViewById(R.id.spn_idHd);
        spnIdSach = (Spinner) dialog.findViewById(R.id.spn_idSach);
        edSoLuong = (EditText) dialog.findViewById(R.id.ed_soLuong);
        btnSave = (Button) dialog.findViewById(R.id.btn_save);
        btnExit = dialog.findViewById(R.id.btn_exit);
        no = (ImageView) dialog.findViewById(R.id.no);
        yes = (ImageView) dialog.findViewById(R.id.yes);
    }

}
