package com.example.DUAN.DBHELPER;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbhelper_bookmanager extends SQLiteOpenHelper {
    public dbhelper_bookmanager(Context context) {
        super(context, "sql_bookmanager", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Tạo bảng thu thu
        String sql_thuthu = "CREATE TABLE TB_USER (ID_USER INTEGER NOT NULL,USERNAME_USER TEXT NOT NULL,PASS_USER TEXT NOT NULL,SDT_USER TEXT NOT NULL,HOTEN_USER TEXT NOT NULL,PRIMARY KEY(ID_USER));";
        sqLiteDatabase.execSQL(sql_thuthu);
        //Tạo bảng loại sách
        String sql_theloaisach = "CREATE TABLE  TB_THELOAI (ID_THELOAISACH  INTEGER NOT NULL,TEN_THELOAISACH  TEXT NOT NULL,MOTA_THELOAISACH  TEXT,VITRI_THELOAISACH  TEXT not null,PRIMARY KEY( ID_THELOAISACH  AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sql_theloaisach);
        //Tạo bảng sách
        String sql_sach = "CREATE TABLE  TB_SACH (ID_SACH INTEGER NOT NULL,ID_THELOAISACH INTEGER NOT NULL REFERENCES TB_THELOAI ,TIEUDE_SACH TEXT NOT NULL,TACGIA_SACH TEXT NOT NULL,NHAXUATBAN_SACH TEXT NOT NULL,GIABAN_SACH FLOAT NOT NULL,SOLUONG_SACH INTEGER NOT NULL,PRIMARY KEY(ID_SACH AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sql_sach);
        //Tạo bảng hoa don
        String sql_hoadon = "CREATE TABLE  TB_HOADON  (ID_HOADON INTEGER NOT NULL,NGAYMUA_HOADON TEXT NOT NULL,PRIMARY KEY( ID_HOADON  AUTOINCREMENT));";
        sqLiteDatabase.execSQL(sql_hoadon);
        String sql_hdct = "CREATE TABLE TB_HDCT (ID_HDCT INTEGER NOT NULL, ID_HOADON INTEGER NOT NULL , ID_SACH integer not null,SOLUONG integer not null, FOREIGN KEY( ID_HOADON ) REFERENCES  TB_HOADON ( ID_HOADON ),FOREIGN KEY( ID_SACH ) REFERENCES  TB_SACH ( ID_SACH ), PRIMARY KEY( ID_HDCT  AUTOINCREMENT))";
        sqLiteDatabase.execSQL(sql_hdct);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
