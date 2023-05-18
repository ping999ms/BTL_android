package com.example.DUAN.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.DUAN.R;

public class viewholder_TheLoaiSach extends RecyclerView.ViewHolder {
    public TextView tvTieuDe;
    public ImageView imgEdit;
    public ImageView imgDelete;
    public ImageView img_showchitiet;
    public viewholder_TheLoaiSach(@NonNull View itemView) {
        super(itemView);
        tvTieuDe = (TextView) itemView.findViewById(R.id.tv_tieuDe);
        imgEdit = (ImageView) itemView.findViewById(R.id.img_edit);
        imgDelete = (ImageView) itemView.findViewById(R.id.img_delete);
        img_showchitiet= itemView.findViewById(R.id.tv_showchitiet);
    }
}
