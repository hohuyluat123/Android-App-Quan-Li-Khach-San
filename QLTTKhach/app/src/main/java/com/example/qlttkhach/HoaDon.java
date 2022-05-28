package com.example.qlttkhach;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HoaDon extends AppCompatActivity {

    List<Table_DichVu> listdichvu;
    List<Table_HoaDon> listhoadon;
    String[] phong={"101","102","103","104"};
    private DatabaseReference fireapp;
    private DatabaseReference fireapp01;
    private DatabaseReference fireapp02;
    Spinner spinMaDV;
    Spinner spinMaPh;
    Spinner spinMaKH;
    TextView textmaDV;
    TextView textmaPh;
    TextView textmaKH;
    DatePicker ngaySd;
    TextView ngsdtxt;
    TextView soluongtxt;
    TextView ttientxt;
    TextView connotxt;
    Button chon;
    Button XacNhanSL;
    Button Add;
    Button TongHD;
    Integer maHoaDon=0;
    List<String> dichvu= new ArrayList<>();
    List<String> khach= new ArrayList<>();
    List<Table_DichVu> list= new ArrayList<>();
    List<ThongTinKhach> listKhach= new ArrayList<>();
    Integer GiaItemDV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.namsao);    //Icon muốn hiện thị
        actionBar.setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        listdichvu= new ArrayList<>();
        listhoadon= new ArrayList<>();
        fireapp02= FirebaseDatabase.getInstance("https://doancuoiky-2bd47-default-rtdb.firebaseio.com/").getReference("ThongTinKhach");
        fireapp02.addChildEventListener(contactListener02);
        fireapp01= FirebaseDatabase.getInstance("https://doancuoiky-2bd47-default-rtdb.firebaseio.com/").getReference("DichVu");
        fireapp01.addChildEventListener(contactListener01);
        fireapp= FirebaseDatabase.getInstance("https://doancuoiky-2bd47-default-rtdb.firebaseio.com/").getReference("HoaDon");
        fireapp.addChildEventListener(contactListener);
        spinMaDV= findViewById(R.id.textMaDichVu);
        spinMaPh= findViewById(R.id.textMaPhong);
        spinMaKH=findViewById(R.id.spinMaKhach);
        textmaKH=findViewById(R.id.textMaKhach);
        textmaDV= findViewById(R.id.textMaDV);
        textmaPh= findViewById(R.id.textMaPh);
        ngaySd= findViewById(R.id.calendarSuDung);
        ngsdtxt= findViewById(R.id.textNgaySD);
        soluongtxt= findViewById(R.id.textSoLuong);
        ttientxt= findViewById(R.id.textThanhTien);
        connotxt= findViewById(R.id.textConno);
        chon= findViewById(R.id.buttonChonNgSD);
        Add= findViewById(R.id.buttonThemHD);
        XacNhanSL= findViewById(R.id.XacNhanSL);
        SpinnerAdapter adapter=new ArrayAdapter<String>(HoaDon.this, android.R.layout.simple_dropdown_item_1line,phong);
        spinMaPh.setAdapter(adapter);
        spinMaPh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id ){
                textmaPh.setText(spinMaPh.getItemAtPosition(position).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0){
                //abc
            }
        });
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month  = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        ngaySd.init( year, month , day , new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                datePickerChange(  datePicker,   year,   month,   dayOfMonth);
            }
        });
        chon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String makh= textmaKH.getText().toString();
                String madv= textmaDV.getText().toString();
                String maph= textmaPh.getText().toString();
                String ngsd= ngsdtxt.getText().toString();
                String sl= soluongtxt.getText().toString();
                String ttien= ttientxt.getText().toString();
                String conno= connotxt.getText().toString();
                fireapp.child(maHoaDon.toString()).setValue(new Table_HoaDon(maHoaDon.toString(), makh, madv, maph, ngsd, sl, ttien, conno));
                Intent intent= new Intent(HoaDon.this, HoaDonTong.class);
                intent.putExtra("child", makh);
                intent.putExtra("MaPhong", maph);
                startActivity(intent);
            }
        });

    }
    public static long calculateDays(Date dateEarly, Date dateLater) {
        return (dateLater.getTime() - dateEarly.getTime()) / (24 * 60 * 60 * 1000);
    }
    private void datePickerChange(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Log.d("Date", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);
        ngsdtxt.setText(dayOfMonth +"/" + (month + 1) + "/" + year);
    }
    private void showDate()  {
        int year = ngaySd.getYear();
        int month = ngaySd.getMonth(); // 0 - 11
        int day = ngaySd.getDayOfMonth();

        Toast.makeText(this, "Date: " + day+"-"+ (month + 1) +"-"+ year, Toast.LENGTH_LONG).show();
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
                myapp= new Intent(HoaDon.this, ThemKhachMoi.class);
                startActivity(myapp);
                break;
            case R.id.chitietthue:
                myapp= new Intent(HoaDon.this, ChiTietThue.class);
                startActivity(myapp);
                break;
            case R.id.hoadon:
                myapp= new Intent(HoaDon.this, HoaDonGDChinh.class);
                startActivity(myapp);
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }
    public String KiemTra(String x){
        String flag="TRUE";
        for(String y: dichvu){
            if( y.equals(x)){
                flag="FALSE";
            }
        }
        return flag;
    }
    public void XuLiSpinner(List<String> dd ){
        SpinnerAdapter adap= new ArrayAdapter<String>(HoaDon.this, android.R.layout.simple_dropdown_item_1line,dd);
        spinMaDV.setAdapter(adap);
        spinMaDV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textmaDV.setText(spinMaDV.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //abc
            }
        });
    }
    public void XuLiXacNhan(List<Table_DichVu> dichvu){
        XacNhanSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String madv= textmaDV.getText().toString();
                String sl= soluongtxt.getText().toString();
                for(int i=0;i<dichvu.size();i++){
                    if(dichvu.get(i).MaDV.equals(madv)){
                        String giadv=dichvu.get(i).Gia;
                        Integer tien= Integer.parseInt(sl)* Integer.parseInt(giadv);
                        ttientxt.setText(tien.toString());
                        break;
                    }
                }
            }
        });
    }
    final ChildEventListener contactListener01 =new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            Table_DichVu newContact = snapshot.getValue(Table_DichVu.class);
            list.add(newContact);
            for(Table_DichVu x: list){
                if(KiemTra(x.MaDV)=="TRUE"){
                    dichvu.add(x.MaDV);
                }
            }
            XuLiSpinner(dichvu);
            XuLiXacNhan(list);
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
    public String KiemTraKhach(String x){
        String flag="TRUE";
        for(String y: khach){
            if( y.equals(x)){
                flag="FALSE";
            }
        }
        return flag;
    }
    public void XuLiSpinnerKhach(List<String> dd ){
        SpinnerAdapter adap= new ArrayAdapter<String>(HoaDon.this, android.R.layout.simple_dropdown_item_1line,dd);
        spinMaKH.setAdapter(adap);
        spinMaKH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textmaKH.setText(spinMaKH.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //abc
            }
        });
    }
    final ChildEventListener contactListener02 =new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            ThongTinKhach newContact = snapshot.getValue(ThongTinKhach.class);
            listKhach.add(newContact);
            for(ThongTinKhach x: listKhach){
                if(KiemTraKhach(x.MaKhach)=="TRUE"){
                    khach.add(x.MaKhach);
                }
            }
            XuLiSpinnerKhach(khach);
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
    final ChildEventListener contactListener =new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            Table_HoaDon newContact = snapshot.getValue(Table_HoaDon.class);
            maHoaDon= Integer.parseInt(newContact.MaHD) +1;

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