package com.example.DUAN.DTO;

public class DTO_thongKe {
    private int ID_SACH,SOLUONG;
    private String TACGIA, TIEUDE;
    private Double doanhThu;

    public DTO_thongKe() {
    }

    public int getID_SACH() {
        return ID_SACH;
    }

    public void setID_SACH(int ID_SACH) {
        this.ID_SACH = ID_SACH;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public String getTACGIA() {
        return TACGIA;
    }

    public void setTACGIA(String TACGIA) {
        this.TACGIA = TACGIA;
    }

    public String getTIEUDE() {
        return TIEUDE;
    }

    public void setTIEUDE(String TIEUDE) {
        this.TIEUDE = TIEUDE;
    }

    public Double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(Double doanhThu) {
        this.doanhThu = doanhThu;
    }
}
