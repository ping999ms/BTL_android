package com.example.DUAN.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.DUAN.R;

public class viewholder_HDCT extends RecyclerView.ViewHolder {
    public TextView tvIdHdct;
    public TextView tvMaHd;
    public TextView tvIdSach;
    public TextView tvSoluong;
    public TextView imgDelete;
    public TextView imgEdit;

    public viewholder_HDCT(@NonNull View itemView) {
        super(itemView);
        tvIdHdct = (TextView) itemView.findViewById(R.id.tv_idHdct);
        tvMaHd = (TextView) itemView.findViewById(R.id.tv_maHd);
        tvIdSach = (TextView) itemView.findViewById(R.id.tv_idSach);
        tvSoluong = (TextView) itemView.findViewById(R.id.tv_soluong);
        imgDelete = (TextView) itemView.findViewById(R.id.img_delete);
        imgEdit = (TextView) itemView.findViewById(R.id.img_edit);
    }
}
