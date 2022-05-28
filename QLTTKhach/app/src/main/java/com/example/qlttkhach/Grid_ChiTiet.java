package com.example.qlttkhach;

public class Grid_ChiTiet {
    String Khach;
    String Phong;
    public Grid_ChiTiet(){

    }
    public void setPhong(String phong) {
        Phong = phong;
    }

    public void setKhach(String khach) {
        Khach = khach;
    }

    public String getKhach() {
        return Khach;
    }

    public String getPhong() {
        return Phong;
    }
    public Grid_ChiTiet(String khach, String phong){
        this.Khach=khach;
        this.Phong=phong;
    }
    @Override
    public String toString() {
        return "Grid_ChiTiet{" +
                "Khach='" + Khach + '\'' +
                ", Phong='" + Phong + '\'' +
                '}';
    }
}
