package com.example.DUAN.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.DUAN.R;

public class viewholder_ThongTin extends RecyclerView.ViewHolder {
    public TextView tbNameUser;
    public ImageView imgDelete;
    public TextView tbCheckInfo;
    public viewholder_ThongTin(@NonNull View itemView) {
        super(itemView);

        tbNameUser = (TextView) itemView.findViewById(R.id.tb_nameUser);
        imgDelete = (ImageView) itemView.findViewById(R.id.img_delete);
        tbCheckInfo = (TextView) itemView.findViewById(R.id.tb_checkInfo);

    }
}
