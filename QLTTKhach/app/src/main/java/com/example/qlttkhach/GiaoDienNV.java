package com.example.qlttkhach;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GiaoDienNV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_nv);
        Button ThemKhach=findViewById(R.id.ThemKhachMoibt);
        Button Chitth= findViewById(R.id.ChiTietThuebt);
        Button HoaDon= findViewById(R.id.HoaDonbt);
        Button Logout= findViewById(R.id.logoutbt);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.namsao);    //Icon muốn hiện thị
        actionBar.setDisplayUseLogoEnabled(true);
        ThemKhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myapp= new Intent(GiaoDienNV.this, ThemKhachMoi.class);
                startActivity(myapp);
            }
        });
        Chitth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myapp= new Intent(GiaoDienNV.this, ChiTietThue.class);
                startActivity(myapp);
            }
        });
        HoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myapp= new Intent(GiaoDienNV.this, HoaDonGDChinh.class);
                startActivity(myapp);
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myapp= new Intent(GiaoDienNV.this, MainActivity.class);
                startActivity(myapp);
            }
        });
    }
}