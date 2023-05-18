package com.example.DUAN.ADAPTER.adapter_QLSach;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.DUAN.DTO.DTO_theLoai;
import com.example.DUAN.R;

import java.util.ArrayList;

public class ADAPTER_spinner_theloai extends BaseAdapter {
    ArrayList<DTO_theLoai> list;

    public ADAPTER_spinner_theloai(ArrayList<DTO_theLoai> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        DTO_theLoai obj = list.get(i);
        return obj;
    }

    @Override
    public long getItemId(int i) {
        DTO_theLoai obj = list.get(i);
        return obj.getMaTheLoai();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        if (view == null) {
            row = View.inflate(viewGroup.getContext(), R.layout.spn_matheloai, null);
        } else {
            row = view;
        }
        final int index = i;
        DTO_theLoai obj = list.get(index);

        TextView tv_maTheLoai = row.findViewById(R.id.tv_maTheLoai);
        tv_maTheLoai.setText("ID: " + obj.getMaTheLoai()+" - "+obj.toString());
        return row;
    }
}
