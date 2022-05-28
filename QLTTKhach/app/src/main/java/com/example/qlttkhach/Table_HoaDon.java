package com.example.qlttkhach;

public class Table_HoaDon {
    String MaDV;
    String MaHD;
    String MaPh;
    String MaKh;
    String NgaySD;
    String SoLuong;
    String ThanhTien;
    String ConNo;

    public Table_HoaDon(){

    }

    public void setMaHD(String maHD) {
        MaHD = maHD;
    }

    public void setMaDV(String maDV) {
        MaDV = maDV;
    }

    public void setMaKh(String maKh) {
        MaKh = maKh;
    }

    public void setMaPh(String maPh) {
        MaPh = maPh;
    }

    public void setNgaySD(String ngaySD) {
        NgaySD = ngaySD;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }

    public void setThanhTien(String thanhTien) {
        ThanhTien = thanhTien;
    }

    public void setConNo(String conNo) {
        ConNo = conNo;
    }

    public String getMaHD() {
        return MaHD;
    }

    public String getMaKh() {
        return MaKh;
    }

    public String getMaDV() {
        return MaDV;
    }

    public String getMaPh() {
        return MaPh;
    }

    public String getNgaySD() {
        return NgaySD;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public String getThanhTien() {
        return ThanhTien;
    }

    public String getConNo() {
        return ConNo;
    }

    public Table_HoaDon(String maHD,String makh,String madv, String maph, String ngaysd, String soluong, String thanhtien, String conno){
        this.MaHD= maHD;
        this.MaKh= makh;
        this.MaDV=madv;
        this.MaPh=maph;
        this.NgaySD=ngaysd;
        this.SoLuong=soluong;
        this.ThanhTien= thanhtien;
        this.ConNo=conno;
    }

    @Override
    public String toString() {
        return "Table_HoaDon{" +
                "MaDV='" + MaDV + '\'' +
                ", MaHD='" + MaHD + '\'' +
                ", MaPh='" + MaPh + '\'' +
                ", MaKh='" + MaKh + '\'' +
                ", NgaySD='" + NgaySD + '\'' +
                ", SoLuong='" + SoLuong + '\'' +
                ", ThanhTien='" + ThanhTien + '\'' +
                ", ConNo='" + ConNo + '\'' +
                '}';
    }
}
