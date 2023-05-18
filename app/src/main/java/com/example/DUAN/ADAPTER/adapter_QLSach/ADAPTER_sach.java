package com.example.DUAN.ADAPTER.adapter_QLSach;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
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

import com.example.DUAN.DAO.DAO_sach;
import com.example.DUAN.DAO.DAO_theLoai;
import com.example.DUAN.DTO.DTO_sach;
import com.example.DUAN.DTO.DTO_theLoai;
import com.example.DUAN.R;
import com.example.DUAN.viewHolder.viewholder_Sach;

import java.util.ArrayList;

public class ADAPTER_sach extends RecyclerView.Adapter<viewholder_Sach> {
    ArrayList<DTO_sach> list;
    DAO_sach dao;

    DAO_theLoai dao_theLoai;
    ADAPTER_spinner_theloai adapter_spn_theloai;
    DTO_theLoai dto_theLoai;

    private ImageView no, yes;
    private EditText edTacgia, edNXB, edGiaBia, edSoLuong, edTieuDe;
    private Spinner spnTheloai;
    private Button btnSave, btnExit, btn_ok;
    private TextView tvMasach, tvTieude, tvTacgia, tvMatheloai, tvSoLuong, tvGiaBia, tvNxb;
    private String tieuDe, giaBia, soLuong, nxb, tacGia;
    boolean check = false;
    Exception e;

    public ADAPTER_sach(ArrayList<DTO_sach> list, DAO_sach dao) {
        this.list = list;
        this.dao = dao;
    }

    @NonNull
    @Override
    public viewholder_Sach onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.item_sach, parent, false);
        viewholder_Sach sach = new viewholder_Sach(row);
        return sach;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder_Sach holder, int position) {
        final int vitri = position;
        DTO_sach obj = list.get(vitri);

        holder.tvTieuDe.setText("Tiêu Đề: " + obj.getTieuDe());
        holder.tvTacGia.setText("Tác Giả: " + obj.getTacGia());
        holder.tvShowChiTiet.setOnClickListener(view -> {
            showchitiet(view.getContext(), obj.getMaSach());
        });

        holder.imgDelete.setOnClickListener(view -> {
            Dialog dialog = new Dialog(view.getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.customdialog);
            dialog.setContentView(R.layout.delete_dialog);
            dialog.show();
            init(dialog);
            yes.setOnClickListener(view1 -> {
                deleteSach(view.getContext(), obj, vitri);
                dialog.dismiss();
            });
            no.setOnClickListener(view2 -> {
                dialog.dismiss();
            });
        });

        holder.imgEdit.setOnClickListener(view2 -> {
            editSach(view2.getContext(), obj, vitri);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void deleteSach(Context context, DTO_sach obj, int index) {
        int res = dao.delete(obj);
        if (res > 0) {
            list.remove(index);
            notifyDataSetChanged();
            Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Xóa Không Thành Công", Toast.LENGTH_SHORT).show();
        }
    }

    public void addSach(Context context) {
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.add_sachdialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.customdialog);
        dialog.show();
        init(dialog);
        dao = new DAO_sach(context);
        dao_theLoai = new DAO_theLoai(context);
        adapter_spn_theloai = new ADAPTER_spinner_theloai(dao_theLoai.selectAll());
        spnTheloai.setAdapter(adapter_spn_theloai);
        btnSave.setOnClickListener(view -> {
            checkDuLieu();
            if (check) {
                DTO_sach obj = new DTO_sach();
                obj.setGiaBia(Double.parseDouble(giaBia));
                obj.setSoLuong(Integer.parseInt(soLuong));
                obj.setTacGia(tacGia);
                obj.setTieuDe(tieuDe);
                obj.setNxb(nxb);
                dto_theLoai = (DTO_theLoai) spnTheloai.getSelectedItem();
                obj.setMaTheLoai(dto_theLoai.getMaTheLoai());
                long res = dao.insert(obj);
                if (res > 0) {
                    notifyDataSetChanged();
                    list.clear();
                    list.addAll(dao.selectAll());
                    Toast.makeText(context, "Thêm Sách Thành Công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Thêm Sách Thất Bại", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        btnExit.setOnClickListener(view -> {
            dialog.dismiss();
        });
    }

    private void editSach(Context context, DTO_sach obj, int index) {
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.edit_sachdialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.customdialog);
        dialog.show();
        init(dialog);
        edGiaBia.setText(obj.getGiaBia() + "");
        edNXB.setText(obj.getNxb());
        edSoLuong.setText(obj.getSoLuong() + "");
        edTacgia.setText(obj.getTacGia());
        edTieuDe.setText(obj.getTieuDe());
        dao_theLoai = new DAO_theLoai(context);
        ArrayList<DTO_theLoai> list_theLoai = dao_theLoai.selectAll();
        adapter_spn_theloai = new ADAPTER_spinner_theloai(list_theLoai);
        spnTheloai.setAdapter(adapter_spn_theloai);
        for (int j = 0; j <= list_theLoai.size() - 1; j++) {
            dto_theLoai = list_theLoai.get(j);
            if (dto_theLoai.getMaTheLoai() == obj.getMaTheLoai()) {
                spnTheloai.setSelection(j);
                spnTheloai.setSelected(true);
            }
        }
        btnSave.setOnClickListener(view -> {
            getData();
            obj.setNxb(nxb);
            obj.setTieuDe(tieuDe);
            obj.setTacGia(tacGia);
            obj.setSoLuong(Integer.parseInt(soLuong));
            obj.setGiaBia(Double.parseDouble(giaBia));
            dto_theLoai = (DTO_theLoai) spnTheloai.getSelectedItem();
            obj.setMaTheLoai(dto_theLoai.getMaTheLoai());
            long res = dao.update(obj);
            if (res > 0) {
                notifyDataSetChanged();
                list.set(index, obj);
                Toast.makeText(context, "Sửa Sách Thành Công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Sửa Sách Thất Bại", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
        });
        btnExit.setOnClickListener(view -> {
            dialog.dismiss();
        });
    }

    private void showchitiet(Context context, int id) {
        Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.showchitietsach_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.customdialog);
        dialog.show();
        init(dialog);
        DTO_sach obj = dao.showChiTiet(id);
        tvMasach.setText("Mã Sách: " + obj.getMaSach());
        tvTieude.setText("Tiêu Đề: " + obj.getTieuDe());
        tvGiaBia.setText("Giá Bìa: " + obj.getGiaBia());
        tvMatheloai.setText("Mã Thể Loại: " + obj.getMaTheLoai());
        tvSoLuong.setText("Số Lượng: " + obj.getSoLuong());
        tvNxb.setText("NXB: " + obj.getNxb());
        tvTacgia.setText("Tác Giả: " + obj.getTacGia());
        btn_ok.setOnClickListener(view -> {
            dialog.dismiss();
        });
    }

    private void checkDuLieu() {
        getData();
        if (tieuDe.isEmpty()) {
            edTieuDe.setError("Vui Lòng Nhập Dữ Liệu");
            edTieuDe.requestFocus();
            check = false;
        } else {
            check = true;
        }

        if (giaBia.isEmpty()) {
            edGiaBia.setError("Vui Lòng Nhập Dữ Liệu");
            edGiaBia.requestFocus();
            check = false;
        } else {
            check = true;
        }

        if (soLuong.isEmpty()) {
            edSoLuong.setError("Vui Lòng Nhập Dữ Liệu");
            edSoLuong.requestFocus();
            check = false;
        } else {
            check = true;
        }

        if (nxb.isEmpty()) {
            edNXB.setError("Vui Lòng Nhập Dữ Liệu");
            edNXB.requestFocus();
            check = false;
        } else {
            check = true;
        }

        if (tacGia.isEmpty()) {
            edTacgia.setError("Vui Lòng Nhập Dữ Liệu");
            edTacgia.requestFocus();
            check = false;
        } else {
            check = true;
        }
    }

    private void init(Dialog dialog) {
        // init addSach and editSach
        no = (ImageView) dialog.findViewById(R.id.no);
        yes = (ImageView) dialog.findViewById(R.id.yes);
        edTieuDe = (EditText) dialog.findViewById(R.id.ed_tieude);
        edTacgia = (EditText) dialog.findViewById(R.id.ed_tacgia);
        edNXB = (EditText) dialog.findViewById(R.id.ed_NXB);
        edGiaBia = (EditText) dialog.findViewById(R.id.ed_giaBia);
        edSoLuong = (EditText) dialog.findViewById(R.id.ed_soLuong);
        spnTheloai = (Spinner) dialog.findViewById(R.id.spn_theloai);
        btnSave = (Button) dialog.findViewById(R.id.btn_save);
        btnExit = dialog.findViewById(R.id.btn_exit);
        // init showChiTiet
        tvMasach = (TextView) dialog.findViewById(R.id.tv_masach);
        tvTieude = (TextView) dialog.findViewById(R.id.tv_tieude);
        tvTacgia = (TextView) dialog.findViewById(R.id.tv_tacgia);
        tvMatheloai = (TextView) dialog.findViewById(R.id.tv_matheloai);
        tvSoLuong = (TextView) dialog.findViewById(R.id.tv_soLuong);
        tvGiaBia = (TextView) dialog.findViewById(R.id.tv_giaBia);
        tvNxb = (TextView) dialog.findViewById(R.id.tv_nxb);
        btn_ok = dialog.findViewById(R.id.btn_ok);

    }

    public void getData() {
        tieuDe = edTieuDe.getText().toString();
        giaBia = edGiaBia.getText().toString();
        soLuong = edSoLuong.getText().toString();
        nxb = edNXB.getText().toString();
        tacGia = edTacgia.getText().toString();
    }

}
