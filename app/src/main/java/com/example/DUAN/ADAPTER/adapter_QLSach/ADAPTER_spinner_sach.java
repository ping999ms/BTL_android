package com.example.DUAN.ADAPTER.adapter_QLSach;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.DUAN.DTO.DTO_sach;
import com.example.DUAN.DTO.DTO_sach;
import com.example.DUAN.R;

import java.util.ArrayList;

public class ADAPTER_spinner_sach extends BaseAdapter {
    ArrayList<DTO_sach> list;

    public ADAPTER_spinner_sach(ArrayList<DTO_sach> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        DTO_sach obj = list.get(i);
        return obj;
    }

    @Override
    public long getItemId(int i) {
        DTO_sach obj = list.get(i);
        return obj.getMaSach();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        if (view == null) {
            row = View.inflate(viewGroup.getContext(), R.layout.spn_sach, null);
        } else {
            row = view;
        }
        final int index = i;
        DTO_sach obj = list.get(index);

        TextView tv_idSach = row.findViewById(R.id.tv_idSach);
        tv_idSach.setText("ID: " + obj.getMaSach()+" - "+obj.toString());

        return row;
    }
}
