package com.example.DUAN.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.DUAN.DBHELPER.dbhelper_bookmanager;
import com.example.DUAN.DTO.DTO_sach;
import com.example.DUAN.DTO.DTO_thongKe;

import java.util.ArrayList;

public class DAO_thongKe {
    SQLiteDatabase db;
    SQLiteOpenHelper dbHelper;
    DAO_sach dao_sach;

    public DAO_thongKe(Context context) {
        dbHelper= new dbhelper_bookmanager(context);
        db= dbHelper.getWritableDatabase();
    }

    public ArrayList<DTO_thongKe> getTop10(Context context){
        dao_sach= new DAO_sach(context);
        ArrayList<DTO_thongKe> list= new ArrayList<>();
        String sqlTop="select TB_SACH.TACGIA_SACH, TB_SACH.TIEUDE_SACH, sum(TB_HDCT.SOLUONG) as SOLUONGBAN from TB_HDCT " +
                "inner join TB_SACH ON TB_HDCT.ID_SACH=TB_SACH.ID_SACH group by TB_HDCT.ID_SACH " +
                "order by SOLUONGBAN desc limit 10";
        Cursor c= db.rawQuery(sqlTop,null);
        while (c.moveToNext()){
            DTO_thongKe obj= new DTO_thongKe();
            obj.setTACGIA(c.getString(0));
            obj.setTIEUDE(c.getString(1));
            obj.setSOLUONG(c.getInt(2));
            list.add(obj);
        }
        return list;
    }

    public double getDoanhThuTheoNgay() {
        double doanhThu = 0;
        String sql = "SELECT SUM(tongtien) from (SELECT SUM(TB_SACH.GIABAN_SACH * TB_HDCT.SOLUONG) as 'tongtien' FROM TB_HOADON INNER JOIN TB_HDCT on TB_HOADON.ID_HOADON = \n" +
                "TB_HDCT.ID_HOADON INNER JOIN TB_SACH on TB_HDCT.ID_SACH = TB_SACH.ID_SACH where TB_HOADON.NGAYMUA_HOADON = date('now') GROUP BY TB_HDCT.ID_SACH)tmp";
        Cursor c= db.rawQuery(sql,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            doanhThu=c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }
    public double getDoanhThuTheoThang() {
        double doanhThu = 0;
        String sql = "SELECT SUM(tongtien) from (SELECT SUM(TB_SACH.GIABAN_SACH * TB_HDCT.SOLUONG) as 'tongtien' FROM TB_HOADON INNER JOIN TB_HDCT on TB_HOADON.ID_HOADON = \n" +
                "TB_HDCT.ID_HOADON INNER JOIN TB_SACH on TB_HDCT.ID_SACH = TB_SACH.ID_SACH where strftime('%m',TB_HOADON.NGAYMUA_HOADON) = strftime('%m','now') GROUP BY TB_HDCT.ID_SACH)tmp";
        Cursor c= db.rawQuery(sql,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            doanhThu=c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }
    public double getDoanhThuTheoNam() {
        double doanhThu = 0;
        String sql = "SELECT SUM(tongtien) from (SELECT SUM(TB_SACH.GIABAN_SACH * TB_HDCT.SOLUONG) as 'tongtien' FROM TB_HOADON INNER JOIN TB_HDCT on TB_HOADON.ID_HOADON = \n" +
                "TB_HDCT.ID_HOADON INNER JOIN TB_SACH on TB_HDCT.ID_SACH = TB_SACH.ID_SACH where strftime('%Y',TB_HOADON.NGAYMUA_HOADON) = strftime('%Y','now') GROUP BY TB_HDCT.ID_SACH)tmp";
        Cursor c= db.rawQuery(sql,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            doanhThu=c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }
}
