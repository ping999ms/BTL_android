package com.example.DUAN.ADAPTER.adapter_BAITHI;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.DUAN.DAO.DAO_sach;
import com.example.DUAN.DTO.DTO_sach;
import com.example.DUAN.R;

import java.util.ArrayList;

public class adapter_cau3 extends BaseAdapter {
    ArrayList<DTO_sach> list;
    DAO_sach dao_sach;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getMaSach();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        if(view == null){
            row= View.inflate(viewGroup.getContext(), R.layout.item_cau3, null);
        }else{
            row= view;
        }

        final int index = 1;

        return row;
    }
}
