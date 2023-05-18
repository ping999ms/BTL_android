package com.example.DUAN.ADAPTER.adapterThongKe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.DUAN.DAO.DAO_sach;
import com.example.DUAN.DAO.DAO_thongKe;
import com.example.DUAN.DTO.DTO_thongKe;
import com.example.DUAN.R;
import com.example.DUAN.viewHolder.viewholder_ThongKe;

import java.util.List;

public class ADAPTER_ThongKe extends RecyclerView.Adapter<viewholder_ThongKe> {
    List<DTO_thongKe> list;
    DAO_thongKe dao_thongKe;
    Context context;

    public ADAPTER_ThongKe(List<DTO_thongKe> list, DAO_thongKe dao_thongKe) {
        this.list = list;
        this.dao_thongKe = dao_thongKe;
    }

    @NonNull
    @Override
    public viewholder_ThongKe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context= parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row=inflater.inflate(R.layout.item_sachtop, parent, false);
        return new viewholder_ThongKe(row);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder_ThongKe holder, int position) {
        final int id= position;
        list= dao_thongKe.getTop10(context);
        DTO_thongKe obj= list.get(id);
        holder.tvSoluongban.setText("Số Lượng Bán: "+obj.getSOLUONG()+"");
        holder.tvTacGia.setText("Tác Giả: "+obj.getTACGIA());
        holder.tvTieuDe.setText("Tiêu Đề: "+obj.getTIEUDE());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
