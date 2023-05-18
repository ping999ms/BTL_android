package com.example.DUAN.DTO;

public class DTO_hoaDon {
    private int maHD;
    private String ngayMua;

    public DTO_hoaDon() {
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    @Override
    public String toString() {
        return "Ngày: " +ngayMua;
    }
}
