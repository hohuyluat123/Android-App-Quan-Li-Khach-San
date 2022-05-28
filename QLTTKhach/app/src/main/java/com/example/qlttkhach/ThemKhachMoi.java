package com.example.qlttkhach;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ThemKhachMoi extends AppCompatActivity {

    private DatabaseReference fireapp;
    String[] phong={"101","102","103","104"};
    Integer makhach=0;
    List<String> daidien= new ArrayList<>();
    List<ThongTinKhach> list= new ArrayList<>();
    Spinner daidientxt;
    TextView nguoiddtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_khach_moi);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.namsao);    //Icon muốn hiện thị
        actionBar.setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        EditText hotentxt=findViewById(R.id.textName);
        EditText cmndtxt= findViewById(R.id.textCMND);
        EditText diachitxt= findViewById(R.id.textDiaChi);
        EditText quoctichtxt= findViewById(R.id.textQuocTich);
        nguoiddtxt= findViewById(R.id.textNgDaiDien);
        EditText nvthemvaotxt= findViewById(R.id.textNhanVienLapPhieu);
        RadioButton namtxt= findViewById(R.id.radioNam);
        RadioButton nutxt= findViewById(R.id.radioNu);
        TextView textnhap= findViewById(R.id.textnhap);
        Spinner phongtxt= findViewById(R.id.textSpinerPhong);
        daidientxt= findViewById(R.id.spinnerNgDaiDien);
        Button Add= findViewById(R.id.Add);

        fireapp= FirebaseDatabase.getInstance("https://doancuoiky-2bd47-default-rtdb.firebaseio.com/").getReference("ThongTinKhach");
        fireapp.addChildEventListener(contactListener);
        SpinnerAdapter adapter=new ArrayAdapter<String>(ThemKhachMoi.this, android.R.layout.simple_dropdown_item_1line,phong);
        phongtxt.setAdapter(adapter);
        phongtxt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id ){
                textnhap.setText(phongtxt.getItemAtPosition(position).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0){
                //abc
            }
        });

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten=hotentxt.getText().toString();
                String cmnd= cmndtxt.getText().toString();
                String diachi= diachitxt.getText().toString();
                String quoctich=quoctichtxt.getText().toString();
                String ngdd= nguoiddtxt.getText().toString();
                String nvthemvao=nvthemvaotxt.getText().toString();
                String gioitinh="nam";
                if(nutxt.isChecked()){
                    gioitinh="nu";
                }
                fireapp.child(makhach.toString()).setValue(new ThongTinKhach(makhach.toString(),hoten,cmnd,gioitinh,diachi,quoctich,textnhap.getText().toString(),ngdd, nvthemvao));
                Toast.makeText(ThemKhachMoi.this, "Đã thêm thành công khách mới", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_khach, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        Intent myapp;
        switch(item.getItemId()){
            case R.id.themkhachmoi:
                myapp= new Intent(ThemKhachMoi.this, ThemKhachMoi.class);
                startActivity(myapp);
                break;
            case R.id.chitietthue:
                myapp= new Intent(ThemKhachMoi.this, ChiTietThue.class);
                startActivity(myapp);
                break;
            case R.id.hoadon:
                myapp= new Intent(ThemKhachMoi.this, HoaDonGDChinh.class);
                startActivity(myapp);
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }
    public String KiemTra(String x){
        String flag="TRUE";
        for(String y: daidien){
            if( y.equals(x)){
                flag="FALSE";
            }
        }
        return flag;
    }
    public void XuLiSpinner(List<String> dd ){
        SpinnerAdapter adap= new ArrayAdapter<String>(ThemKhachMoi.this, android.R.layout.simple_dropdown_item_1line,dd);
        daidientxt.setAdapter(adap);
        daidientxt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nguoiddtxt.setText(daidientxt.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //abc
            }
        });
    }
    final ChildEventListener contactListener =new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            ThongTinKhach newContact = snapshot.getValue(ThongTinKhach.class);
            list.add(newContact);
            for(ThongTinKhach x: list){
                if(KiemTra(x.MaKhach)=="TRUE"){
                    daidien.add(x.MaKhach);
                }
            }
            XuLiSpinner(daidien);
            makhach= Integer.parseInt(newContact.MaKhach) +1;
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            Table_XemCT newContact = snapshot.getValue(Table_XemCT.class);
        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}