package com.example.DUAN.ADAPTER.adapter_QLHoadon;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.DUAN.DAO.DAO_hoaDon;
import com.example.DUAN.DTO.DTO_hoaDon;
import com.example.DUAN.R;
import com.example.DUAN.viewHolder.viewholder_HoaDon;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ADAPTER_HoaDon extends RecyclerView.Adapter<viewholder_HoaDon> {
    ArrayList<DTO_hoaDon> list;
    DAO_hoaDon dao;
    private ImageView no;
    private ImageView yes;
    private EditText edNgayMua;
    private Button btnSave;
    private Button btnExit;


    public ADAPTER_HoaDon(ArrayList<DTO_hoaDon> list, DAO_hoaDon dao) {
        this.list = list;
        this.dao = dao;
    }

    @NonNull
    @Override
    public viewholder_HoaDon onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View row= inflater.inflate(R.layout.item_hoadon, parent, false);
        return new viewholder_HoaDon(row);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder_HoaDon holder, int position) {
        final int index= position;
        DTO_hoaDon obj= list.get(index);
        holder.tvIdHoadon.setText("ID Hóa Đơn:"+obj.getMaHD());
        holder.tvNgayMua.setText("Ngày Mua: "+obj.getNgayMua());
        holder.imgDelete.setOnClickListener(view->{
            Dialog dialog = new Dialog(view.getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
            dialog.setContentView(R.layout.delete_dialog);
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.customdialog);
            dialog.show();
            anhxa(dialog);
            yes.setOnClickListener(view1 -> {
                deleteHoaDon(view.getContext(), obj, index);
                dialog.dismiss();
            });
            no.setOnClickListener(view2 -> {
                dialog.dismiss();
            });
        });
        holder.imgEdit.setOnClickListener(view->{
            showDiaLog_EditHD(view.getContext(), obj, index);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void showDiaLog_AddHD(Context context) {
        Dialog dialog= new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.add_hoadon);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.customdialog);
        dialog.show();
        anhxa(dialog);
        edNgayMua.setOnClickListener(view -> {
            calendarShow(context);
        });
        Date date=new Date(System.currentTimeMillis());
        android.text.format.DateFormat dateFormat=new DateFormat();
        SimpleDateFormat format= new SimpleDateFormat("dd/MM/yyyy");
        btnSave.setOnClickListener(view -> {
            DTO_hoaDon obj= new DTO_hoaDon();
            try {
                Date convert_date= format.parse(edNgayMua.getText().toString());
                String formDate_Standard = (String) dateFormat.format("yyyy-MM-dd", convert_date);
                obj.setNgayMua(formDate_Standard);
            }catch (Exception e){
                e.printStackTrace();
            }
            long res= dao.insert(obj);
            if (res>0){
                notifyDataSetChanged();
                list.clear();
                list.addAll(dao.selectAll());
                Toast.makeText(context, "Thêm Hóa Đơn Thành Công", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Thêm Hóa Đơn Thất Bại", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
        });
        btnExit.setOnClickListener(view -> {
            dialog.dismiss();
        });
    }

    private void showDiaLog_EditHD(Context context, DTO_hoaDon obj, int index) {
        Dialog dialog= new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.edit_hoadon);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.customdialog);
        dialog.show();
        anhxa(dialog);
        edNgayMua.setText(obj.getNgayMua());
        edNgayMua.setOnClickListener(view -> {
            calendarShow(context);
        });

        Date date=new Date(System.currentTimeMillis());
        android.text.format.DateFormat dateFormat=new DateFormat();
        SimpleDateFormat format= new SimpleDateFormat("dd/MM/yyyy");

        btnSave.setOnClickListener(view -> {
            try {
                Date convert_date= format.parse(edNgayMua.getText().toString());
                String formDate_Standard = (String) dateFormat.format("yyyy-MM-dd", convert_date);
                obj.setNgayMua(formDate_Standard);
            }catch (Exception e){
                e.printStackTrace();
            }
            long res= dao.update(obj);
            if (res>0){
                notifyDataSetChanged();
                list.set(index,obj);
                Toast.makeText(context, "Sửa Hóa Đơn Thành Công", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Sửa Hóa Đơn Thất Bại", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
        });
        btnExit.setOnClickListener(view -> {
            dialog.dismiss();
        });
    }

    private void deleteHoaDon(Context context, DTO_hoaDon obj, int index) {
        int res= dao.delete(obj);
        if(res>0){
            list.remove(index);
            notifyDataSetChanged();
            Toast.makeText(context, "Xóa Hóa Đơn Thành Công", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Xóa Hóa Đơn Thất Bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void calendarShow(Context context) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        DatePickerDialog dialog1=new DatePickerDialog(context,
                (datePicker, i, i1, i2) -> {
                    int nam=i;
                    int thang=i1+1;
                    int ngay=i2;
                    edNgayMua.setText(""+ngay+"/"+thang+"/"+nam);
                }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE)
        );
        dialog1.show();

    }

    private void anhxa(Dialog dialog) {
        no = (ImageView) dialog.findViewById(R.id.no);
        yes = (ImageView) dialog.findViewById(R.id.yes);
        edNgayMua = (EditText) dialog.findViewById(R.id.ed_ngayMua);
        btnSave = (Button) dialog.findViewById(R.id.btn_save);
        btnExit= dialog.findViewById(R.id.btn_exit);
    }
}
