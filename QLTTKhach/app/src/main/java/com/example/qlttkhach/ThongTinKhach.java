package com.example.qlttkhach;

public class ThongTinKhach {
    String MaKhach;
    String HoTen;
    String CMND;
    String Phai;
    String DiaChi;
    String QuocTich;
    String Phong;
    String NguoiDD;
    String NVThemvao;

    public ThongTinKhach(){

    }

    public void setMaKhach(String maKhach) {
        MaKhach = maKhach;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public void setPhai(String phai) {
        Phai = phai;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public void setQuocTich(String quocTich) {
        QuocTich = quocTich;
    }

    public void setPhong(String phong) {
        Phong = phong;
    }

    public void setNguoiDD(String nguoiDD) {
        NguoiDD = nguoiDD;
    }

    public void setNVThemvao(String NVThemvao) {
        this.NVThemvao = NVThemvao;
    }

    public String getMaKhach() {
        return MaKhach;
    }

    public String getHoTen() {
        return HoTen;
    }

    public String getCMND() {
        return CMND;
    }

    public String getPhai() {
        return Phai;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getQuocTich() {
        return QuocTich;
    }

    public String getPhong() {
        return Phong;
    }

    public String getNguoiDD() {
        return NguoiDD;
    }

    public String getNVThemvao() {
        return NVThemvao;
    }

    public ThongTinKhach(String maKhach, String hoten, String cmnd, String phai, String diachi, String quocTich, String phong, String nguoidd, String nvthemvao){
        this.MaKhach=maKhach;
        this.HoTen=hoten;
        this.CMND=cmnd;
        this.Phai=phai;
        this.DiaChi=diachi;
        this.QuocTich=quocTich;
        this.Phong=phong;
        this.NguoiDD=nguoidd;
        this.NVThemvao= nvthemvao;
    }

    @Override
    public String toString() {
        return "ThongTinKhach{" +
                "MaKhach='" + MaKhach + '\'' +
                ", HoTen='" + HoTen + '\'' +
                ", CMND='" + CMND + '\'' +
                ", Phai='" + Phai + '\'' +
                ", DiaChi='" + DiaChi + '\'' +
                ", QuocTich='" + QuocTich + '\'' +
                ", Phong='" + Phong + '\'' +
                ", NguoiDD='" + NguoiDD + '\'' +
                ", NVThemvao='" + NVThemvao + '\'' +
                '}';
    }
}
