package com.example.DUAN.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.DUAN.DBHELPER.dbhelper_bookmanager;
import com.example.DUAN.DTO.DTO_hoaDon;

import java.util.ArrayList;

public class DAO_hoaDon {
    SQLiteDatabase db;
    dbhelper_bookmanager dbhelper;

    public DAO_hoaDon(Context context){
        dbhelper= new dbhelper_bookmanager(context);
        db= dbhelper.getWritableDatabase();
    }

    public ArrayList<DTO_hoaDon> selectAll(){
        ArrayList list= new ArrayList();
        String select= "select * from TB_HOADON";
        Cursor c= db.rawQuery(select, null);
        if(c.moveToFirst()){
            while (!c.isAfterLast()){
                DTO_hoaDon obj = new DTO_hoaDon();
                obj.setMaHD(c.getInt(0));
                obj.setNgayMua(c.getString(1));
                list.add(obj);
                c.moveToNext();
            }
        }
        return  list;
    }

    public long insert(DTO_hoaDon obj){
        ContentValues contentValues= new ContentValues();
        contentValues.put("NGAYMUA_HOADON", obj.getNgayMua());
        return db.insert("TB_HOADON", null, contentValues);
    }
    public int delete(DTO_hoaDon obj){
        return db.delete("TB_HOADON", "ID_HOADON=?", new String[]{obj.getMaHD()+""});
    }
    public long update(DTO_hoaDon obj){
        ContentValues contentValues= new ContentValues();
        contentValues.put("NGAYMUA_HOADON", obj.getNgayMua());
        return db.update("TB_HOADON",contentValues ,"ID_HOADON=?", new String[]{obj.getMaHD()+""});
    }
}
