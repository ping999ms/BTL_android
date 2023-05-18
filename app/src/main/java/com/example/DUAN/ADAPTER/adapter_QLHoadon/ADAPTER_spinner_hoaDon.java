package com.example.DUAN.ADAPTER.adapter_QLHoadon;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.DUAN.DAO.DAO_hoaDon;
import com.example.DUAN.DTO.DTO_hoaDon;
import com.example.DUAN.R;

import java.util.ArrayList;

public class ADAPTER_spinner_hoaDon extends BaseAdapter {
    ArrayList<DTO_hoaDon> list;
    private TextView idHd;

    public ADAPTER_spinner_hoaDon(ArrayList<DTO_hoaDon> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        DTO_hoaDon obj= list.get(i);
        return obj;
    }

    @Override
    public long getItemId(int i) {
        DTO_hoaDon obj= list.get(i);
        return obj.getMaHD();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row;
        if(view==null){
            row= View.inflate(viewGroup.getContext(), R.layout.spn_hoadon ,null);
        }else{
            row=view;
        }
        idHd = (TextView) row.findViewById(R.id.tv_idHd);
        final int index=i;
        DTO_hoaDon obj= list.get(index);
        idHd.setText("ID: "+obj.getMaHD()+" - "+obj.toString());
        return row;
    }
}
