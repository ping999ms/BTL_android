package com.example.DUAN.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.DUAN.DBHELPER.dbhelper_bookmanager;
import com.example.DUAN.DTO.DTO_user;

import java.util.ArrayList;

public class DAO_user {
    SQLiteDatabase db;
    dbhelper_bookmanager dbhelper;

    public DAO_user(Context context) {
        dbhelper = new dbhelper_bookmanager(context);
        db = dbhelper.getWritableDatabase();
    }

    public ArrayList<DTO_user> selectAll() {
        ArrayList list = new ArrayList();
        String select = "select * from TB_USER";
        Cursor c = db.rawQuery(select, null);
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                DTO_user obj = new DTO_user();
                obj.setId_user(c.getInt(0));
                obj.setUsername_user(c.getString(1));
                obj.setPass_user(c.getString(2));
                obj.setSoDienThoai(c.getString(3));
                obj.setHoTen(c.getString(4));
                list.add(obj);
                c.moveToNext();
            }
        }
        return list;
    }

    public long insert(DTO_user obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME_USER", obj.getUsername_user());
        contentValues.put("PASS_USER", obj.getPass_user());
        contentValues.put("SDT_USER", obj.getSoDienThoai());
        contentValues.put("HOTEN_USER", obj.getHoTen());
        return db.insert("TB_USER", null, contentValues);
    }

    public int delete(DTO_user obj) {
        return db.delete("TB_USER", "ID_USER=?", new String[]{obj.getId_user() + ""});
    }

    public int update(DTO_user obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("PASS_USER", obj.getPass_user());
        return db.update("TB_USER", contentValues, "ID_USER=?", new String[]{obj.getId_user() + ""});
    }

    public boolean check(String username, String pass){
        String dieukien= "USERNAME_USER=? and PASS_USER=?";
        String [] getdata = {username.trim(), pass.trim()};
        String [] select = new String[]{"*"};
        Cursor c= db.query("TB_USER",select,dieukien,getdata,null,null,null);
        if(c.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public DTO_user showChiTiet_User(int id){
        String [] args= new String[]{id+""};
        String select= "select * from TB_USER where ID_USER=?";
        DTO_user obj= new DTO_user();
        Cursor c= db.rawQuery(select,args);
        if(c.moveToFirst()){
            obj.setId_user(c.getInt(0));
            obj.setUsername_user(c.getString(1));
            obj.setPass_user(c.getString(2));
            obj.setSoDienThoai(c.getString(3));
            obj.setHoTen(c.getString(4));
        }
        return obj;
    }

    public boolean checkPass(String pass){
        String dieukien= "PASS_USER=?";
        String [] getPass= {pass.trim()};
        String [] sql= new String[]{"*"};

        Cursor c= db.query("TB_USER",sql,dieukien,getPass, null, null, null);
        if(c.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
    public DTO_user layTaiKhoan(String username, String pass){
        DTO_user obj = new DTO_user();
        String dieukien= "USERNAME_USER=? and PASS_USER=?";
        String [] getdata = {username.trim(), pass.trim()};
        Cursor c= db.query("TB_USER",null,dieukien,getdata,null,null,null);
        if(c.moveToFirst()){
            obj.setId_user(c.getInt(0));
            obj.setUsername_user(c.getString(1));
            obj.setPass_user(c.getString(2));
            obj.setSoDienThoai(c.getString(3));
            obj.setHoTen(c.getString(4));
        }
        return obj;
    }

    //Kiểm Tra Tên Đang Nhập Để Đổi Mật Khẩu
    public boolean checkUserName(String username) {
        Cursor c = db.rawQuery("Select * from TB_USER WHERE USERNAME_USER=?", new String[]{username});
        if (c.getCount() != 0) {
            return true;
        }
        return false;
    }
}
