package com.example.DUAN.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.DUAN.R;

public class viewholder_HoaDon extends RecyclerView.ViewHolder {
    public TextView tvIdHoadon;
    public TextView tvNgayMua;
    public ImageView imgEdit;
    public ImageView imgDelete;
    public viewholder_HoaDon(@NonNull View itemView) {
        super(itemView);
        tvIdHoadon = (TextView) itemView.findViewById(R.id.tv_idhoadon);
        imgEdit = (ImageView) itemView.findViewById(R.id.img_edit);
        imgDelete = (ImageView) itemView.findViewById(R.id.img_delete);
        tvNgayMua= itemView.findViewById(R.id.tv_ngaymua);
    }
}
