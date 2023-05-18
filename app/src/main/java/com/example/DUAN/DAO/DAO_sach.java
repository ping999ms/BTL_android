package com.example.DUAN.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.DUAN.DBHELPER.dbhelper_bookmanager;
import com.example.DUAN.DTO.DTO_sach;

import java.util.ArrayList;

public class DAO_sach {
    SQLiteDatabase db;
    dbhelper_bookmanager dbhelper;

    public DAO_sach(Context context) {
        dbhelper = new dbhelper_bookmanager(context);
        db = dbhelper.getWritableDatabase();
    }

    public ArrayList<DTO_sach> selectAll() {
        ArrayList list = new ArrayList();
        String select = "select * from TB_SACH";
        Cursor c = db.rawQuery(select, null);
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                DTO_sach obj = new DTO_sach();
                obj.setMaSach(c.getInt(0));
                obj.setMaTheLoai(c.getInt(1));
                obj.setTieuDe(c.getString(2));
                obj.setTacGia(c.getString(3));
                obj.setNxb(c.getString(4));
                obj.setGiaBia(c.getDouble(5));
                obj.setSoLuong(c.getInt(6));
                list.add(obj);
                c.moveToNext();
            }
        }
        return list;
    }

    public long insert(DTO_sach obj) {
        ContentValues values = new ContentValues();
        values.put("TIEUDE_SACH", obj.getTieuDe());
        values.put("TACGIA_SACH", obj.getTacGia());
        values.put("NHAXUATBAN_SACH", obj.getNxb());
        values.put("GIABAN_SACH", obj.getGiaBia());
        values.put("SOLUONG_SACH", obj.getSoLuong());
        values.put("ID_THELOAISACH", obj.getMaTheLoai());
        return db.insert("TB_SACH", null, values);
    }

    public int delete(DTO_sach obj) {
        return db.delete("TB_SACH", "ID_SACH=?", new String[]{obj.getMaSach() + ""});
    }

    public long update(DTO_sach obj) {
        ContentValues values = new ContentValues();
        values.put("TIEUDE_SACH", obj.getTieuDe());
        values.put("TACGIA_SACH", obj.getTacGia());
        values.put("NHAXUATBAN_SACH", obj.getNxb());
        values.put("GIABAN_SACH", obj.getGiaBia());
        values.put("SOLUONG_SACH", obj.getSoLuong());
        values.put("ID_THELOAISACH", obj.getMaTheLoai());
        return db.update("TB_SACH", values, "ID_SACH=?", new String[]{obj.getMaSach() + ""});
    }

    public DTO_sach showChiTiet(int id) {
        String[] args = new String[]{id + ""};
        String sql = "select ID_SACH, TB_THELOAI.ID_THELOAISACH,TIEUDE_SACH, TACGIA_SACH, NHAXUATBAN_SACH, GIABAN_SACH, SOLUONG_SACH " +
                "from TB_SACH inner join TB_THELOAI on TB_SACH.ID_THELOAISACH = TB_THELOAI.ID_THELOAISACH where ID_SACH=?";
        DTO_sach obj = new DTO_sach();
        Cursor c = db.rawQuery(sql, args);
        if (c.moveToFirst()) {
            obj.setMaSach(c.getInt(0));
            obj.setMaTheLoai(c.getInt(1));
            obj.setTieuDe(c.getString(2));
            obj.setTacGia(c.getString(3));
            obj.setNxb(c.getString(4));
            obj.setGiaBia(c.getDouble(5));
            obj.setSoLuong(c.getInt(6));
        }
        return obj;
    }

    public DTO_sach getSachById(int ID) {
        DTO_sach obj = null;
        String[] args = new String[]{ID + ""};
        String selection = "ID_SACH=?";
        Cursor c = db.query("TB_SACH", null, selection, args, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            obj = new DTO_sach();
            obj.setMaSach(c.getInt(0));
            obj.setMaTheLoai(c.getInt(1));
            obj.setTieuDe(c.getString(2));
            obj.setTacGia(c.getString(3));
            obj.setNxb(c.getString(4));
            obj.setGiaBia(c.getDouble(5));
            obj.setSoLuong(c.getInt(6));
        }
        return obj;
    }

}
