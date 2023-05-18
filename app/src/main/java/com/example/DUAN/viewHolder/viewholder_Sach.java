package com.example.DUAN.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.DUAN.R;

public class viewholder_Sach extends RecyclerView.ViewHolder {
    public TextView tvTieuDe;
    public ImageView imgEdit;
    public TextView tvTacGia;
    public ImageView imgDelete;
    public TextView tvShowChiTiet;
    public viewholder_Sach(@NonNull View itemView) {
        super(itemView);
        tvTieuDe = (TextView) itemView.findViewById(R.id.tv_tieuDe1);
        imgEdit = (ImageView) itemView.findViewById(R.id.img_edit);
        tvTacGia = (TextView) itemView.findViewById(R.id.tv_nxb);
        imgDelete = (ImageView) itemView.findViewById(R.id.img_delete);
        tvShowChiTiet= itemView.findViewById(R.id.tv_showchitiet);
    }
}
