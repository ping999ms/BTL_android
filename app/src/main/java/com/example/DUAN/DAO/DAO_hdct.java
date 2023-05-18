package com.example.DUAN.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.DUAN.DBHELPER.dbhelper_bookmanager;
import com.example.DUAN.DTO.DTO_hdct;
import com.example.DUAN.DTO.DTO_sach;

import java.util.ArrayList;

public class DAO_hdct {
    SQLiteDatabase db;
    dbhelper_bookmanager dbhelper;

    DAO_sach dao_sach;

    public DAO_hdct(Context context) {
        dbhelper = new dbhelper_bookmanager(context);
        db = dbhelper.getWritableDatabase();
        dao_sach = new DAO_sach(context);

    }

    public ArrayList<DTO_hdct> selectAll() {
        ArrayList list = new ArrayList();
        String select = "select * from TB_HDCT";
        Cursor c = db.rawQuery(select, null);
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                DTO_hdct obj = new DTO_hdct();
                obj.setMaHDCT(c.getInt(0));
                obj.setMaHD(c.getInt(1));
                obj.setMaSach(c.getInt(2));
                obj.setSoLuongMua(c.getInt(3));
                list.add(obj);
                c.moveToNext();
            }
        }
        return list;
    }

    public long insert(DTO_hdct obj) {

        DTO_sach dto_sach = dao_sach.showChiTiet(obj.getMaSach());
        if(dto_sach.getSoLuong() > obj.getSoLuongMua()){
            dto_sach.setSoLuong(dto_sach.getSoLuong() - obj.getSoLuongMua());
            long update_sach = dao_sach.update(dto_sach);
            if(update_sach > 0){
                ContentValues contentValues = new ContentValues();
                contentValues.put("ID_HOADON", obj.getMaHD());
                contentValues.put("ID_SACH", obj.getMaSach());
                contentValues.put("SOLUONG", obj.getSoLuongMua());
                return db.insert("TB_HDCT", null, contentValues);
            }
            else {
                return 0;
            }
        }
        else {
            return 0;
        }

    }

    public int delete(DTO_hdct obj) {
        return db.delete("TB_HDCT", "ID_HDCT=?", new String[]{obj.getMaHDCT() + ""});
    }

    public long update(DTO_hdct obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID_HOADON", obj.getMaHD());
        contentValues.put("ID_SACH", obj.getMaSach());
        contentValues.put("SOLUONG", obj.getSoLuongMua());
        return db.update("TB_HDCT", contentValues, "ID_HDCT=?", new String[]{obj.getMaHDCT() + ""});
    }
}
