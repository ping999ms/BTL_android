package com.example.DUAN.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.DUAN.DBHELPER.dbhelper_bookmanager;
import com.example.DUAN.DTO.DTO_theLoai;

import java.util.ArrayList;

public class DAO_theLoai {
    SQLiteDatabase db;
    dbhelper_bookmanager dbhelper;

    public DAO_theLoai(Context context) {
        dbhelper = new dbhelper_bookmanager(context);
        db = dbhelper.getWritableDatabase();
    }

    public ArrayList<DTO_theLoai> selectAll() {
        ArrayList<DTO_theLoai> list = new ArrayList<>();
        String select = "select * from TB_THELOAI";
        Cursor c = db.rawQuery(select, null);
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                DTO_theLoai obj = new DTO_theLoai();
                obj.setMaTheLoai(c.getInt(0));
                obj.setTenTheLoai(c.getString(1));
                obj.setMoTa(c.getString(2));
                obj.setViTri(c.getString(3));
                list.add(obj);
                c.moveToNext();
            }
        }
        return list;
    }

    public long insert(DTO_theLoai obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TEN_THELOAISACH", obj.getTenTheLoai());
        contentValues.put("MOTA_THELOAISACH", obj.getMoTa());
        contentValues.put("VITRI_THELOAISACH", obj.getViTri());
        return db.insert("TB_THELOAI", null, contentValues);
    }

    public int delete(DTO_theLoai obj) {
        return db.delete("TB_THELOAI", "ID_THELOAISACH=?", new String[]{obj.getMaTheLoai() + ""});
    }

    public long update(DTO_theLoai obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TEN_THELOAISACH", obj.getTenTheLoai());
        contentValues.put("MOTA_THELOAISACH", obj.getMoTa());
        contentValues.put("VITRI_THELOAISACH", obj.getViTri());
        return db.update("TB_THELOAI", contentValues, "ID_THELOAISACH=?", new String[]{obj.getMaTheLoai() + ""});
    }

    public DTO_theLoai showChiTiet(int id) {
        String[] args = new String[]{id + ""};
        String sql = "select * from TB_THELOAI where ID_THELOAISACH=?";
        DTO_theLoai obj = new DTO_theLoai();
        Cursor c = db.rawQuery(sql, args);
        if (c.moveToFirst()) {
            obj.setMaTheLoai(c.getInt(0));
            obj.setTenTheLoai(c.getString(1));
            obj.setMoTa(c.getString(2));
            obj.setViTri(c.getString(3));
        }
        return obj;
    }
}
