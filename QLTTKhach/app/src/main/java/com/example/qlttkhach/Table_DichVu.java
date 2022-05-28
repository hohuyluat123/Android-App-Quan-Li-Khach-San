package com.example.qlttkhach;

public class Table_DichVu {
    String MaDV;
    String TenDV;
    String Gia;
    public Table_DichVu(){

    }

    public void setMaDV(String maDV) {
        MaDV = maDV;
    }

    public void setGia(String gia) {
        Gia = gia;
    }

    public void setTenDV(String tenDV) {
        TenDV = tenDV;
    }

    public String getMaDV() {
        return MaDV;
    }

    public String getGia() {
        return Gia;
    }

    public String getTenDV() {
        return TenDV;
    }
    public Table_DichVu(String madv, String tendv, String gia){
        this.MaDV=madv;
        this.TenDV=tendv;
        this.Gia=gia;
    }

    @Override
    public String toString() {
        return "Table_DichVu{" +
                "MaDV='" + MaDV + '\'' +
                ", TenDV='" + TenDV + '\'' +
                ", Gia='" + Gia + '\'' +
                '}';
    }
}
