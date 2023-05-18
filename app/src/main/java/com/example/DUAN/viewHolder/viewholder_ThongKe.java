package com.example.DUAN.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.DUAN.R;

public class viewholder_ThongKe extends RecyclerView.ViewHolder {
    public TextView tvTieuDe;
    public TextView tvTacGia;
    public TextView tvSoluongban;

    public viewholder_ThongKe(@NonNull View itemView) {
        super(itemView);
        tvTieuDe = (TextView) itemView.findViewById(R.id.tv_tieuDe);
        tvTacGia = (TextView) itemView.findViewById(R.id.tv_tacGia);
        tvSoluongban = (TextView) itemView.findViewById(R.id.tv_soluongban);
    }
}
