package com.example.qlttkhach;

public class Table_XemCT {
    String Khach;
    String Phong;
    String TongTien;
    String NhanVien;
    String NgayTra;
    String NgayThue;
    String DaTra;
    String ConLai;
    public Table_XemCT(){

    }

    public void setKhach(String khach) {
        Khach = khach;
    }

    public void setPhong(String phong) {
        Phong = phong;
    }

    public void setConLai(String conLai) {
        ConLai = conLai;
    }

    public void setDaTra(String daTra) {
        DaTra = daTra;
    }

    public void setNgayThue(String ngayThue) {
        NgayThue = ngayThue;
    }

    public void setNgayTra(String ngayTra) {
        NgayTra = ngayTra;
    }

    public void setNhanVien(String nhanVien) {
        NhanVien = nhanVien;
    }

    public void setTongTien(String tongTien) {
        TongTien = tongTien;
    }

    public String getPhong() {
        return Phong;
    }

    public String getKhach() {
        return Khach;
    }

    public String getConLai() {
        return ConLai;
    }

    public String getDaTra() {
        return DaTra;
    }

    public String getNgayThue() {
        return NgayThue;
    }

    public String getNgayTra() {
        return NgayTra;
    }

    public String getNhanVien() {
        return NhanVien;
    }

    public String getTongTien() {
        return TongTien;
    }
    public Table_XemCT(String khach, String phong, String tongtien, String nv, String ngtra, String ngthue, String datra, String conlai){
        this.ConLai=conlai;
        this.DaTra= datra;
        this.Khach=khach;
        this.Phong=phong;
        this.NgayThue= ngthue;
        this.NgayTra=ngtra;
        this.NhanVien=nv;
        this.TongTien= tongtien;
    }

    @Override
    public String toString() {
        return "Table_XemCT{" +
                "Khach='" + Khach + '\'' +
                ", Phong='" + Phong + '\'' +
                ", TongTien='" + TongTien + '\'' +
                ", NhanVien='" + NhanVien + '\'' +
                ", NgayTra='" + NgayTra + '\'' +
                ", NgayThue='" + NgayThue + '\'' +
                ", DaTra='" + DaTra + '\'' +
                ", ConLai='" + ConLai + '\'' +
                '}';
    }
}
