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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class XemChiTiet extends AppCompatActivity {

    List<Table_XemCT> listchitiet;
    private DatabaseReference fireapp;
    private DatabaseReference fireapp01;
    EditText Khach;
    EditText Phong;
    EditText NhanVien;
    EditText NgThue;
    EditText NgTra;
    EditText DaTra;
    EditText TongTien;
    EditText ConLai;
    EditText TienNhan;
    DatePicker datethue;
    DatePicker datetra;
    Spinner MaKhach;
    Button chonthue;
    Button chontra;
    Button Songay;
    EditText song;
    List<String> LayMaKH= new ArrayList<>();
    List<Table_DichVu> list= new ArrayList<>();
    List<ThongTinKhach> listLayMaKH= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_chi_tiet);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.namsao);    //Icon muốn hiện thị
        actionBar.setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Khach= findViewById(R.id.textmakhach);
        Phong= findViewById(R.id.textmaphong);
        NhanVien=findViewById(R.id.textNhanVienNhan);
        TongTien= findViewById(R.id.texttongtien);
        DaTra= findViewById(R.id.textDaTra);
        ConLai= findViewById(R.id.textConLai);
        NgThue= findViewById(R.id.textngaythue);
        NgTra=findViewById(R.id.textngaytra);
        TienNhan= findViewById(R.id.textSoTien);
        datethue= findViewById(R.id.datepickerthueCT);
        datetra= findViewById(R.id.datepickertraCT);
        chonthue= findViewById(R.id.buttonngthueCT);
        chontra= findViewById(R.id.buttonngtraCT);
        Songay=findViewById(R.id.DateCT);
        song=findViewById(R.id.songayCT);
        MaKhach= findViewById(R.id.spinMaKhach);
        fireapp= FirebaseDatabase.getInstance("https://doancuoiky-2bd47-default-rtdb.firebaseio.com/").getReference("ChiTietThue");
        fireapp01= FirebaseDatabase.getInstance("https://doancuoiky-2bd47-default-rtdb.firebaseio.com/").getReference("ThongTinKhach");
        fireapp01.addChildEventListener(contactListener02);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month  = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        datethue.init( year, month , day , new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                datePickerChange(  datePicker,   year,   month,   dayOfMonth);
            }
        });
        chonthue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });
        datetra.init( year, month , day , new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                datePickerChange01(  datePicker,   year,   month,   dayOfMonth);
            }
        });
        chontra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTra();
            }
        });
        Songay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ngthue= NgThue.getText().toString();
                SimpleDateFormat format01= new SimpleDateFormat("dd/MM/yyyy");
                String ngtra= NgTra.getText().toString();
                Date dayThue= null;
                Date dayTra=null;
                try {
                    dayThue = format01.parse(ngthue);//catch exception
                    dayTra= format01.parse(ngtra);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                long NumberDay= calculateDays(dayThue, dayTra);
                int sotien= (int) NumberDay*200;
                song.setText(String.valueOf(NumberDay));
                TongTien.setText(String.valueOf(sotien));
            }
        });
        listchitiet= new ArrayList<>();

        Button Add= findViewById(R.id.XacNhan);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String khach= Khach.getText().toString();
                String phong= Phong.getText().toString();
                String nv= NhanVien.getText().toString();
                String ngthue= NgThue.getText().toString();
                String ngtra= NgTra.getText().toString();
                String tongtien=TongTien.getText().toString();
                String tiennhan= TienNhan.getText().toString();
                String Datra= DaTra.getText().toString();
                Integer conlai;
                Integer sotiennhan= Integer.parseInt(tiennhan);
                Integer tongtra= Integer.parseInt(tongtien);
                if(sotiennhan>tongtra){
                    Datra=tongtra.toString();
                    Integer tienthoi= sotiennhan-tongtra;
                    conlai=0;
                    Toast.makeText(XemChiTiet.this, "Đã thanh toán, Tiền thói lại: "+ tienthoi +"VNĐ", Toast.LENGTH_SHORT).show();
                }else if( sotiennhan< tongtra){
                    Datra=sotiennhan.toString();
                    Integer nophi= tongtra- sotiennhan;
                    conlai=nophi;
                    Toast.makeText(XemChiTiet.this, "Còn nợ lại "+nophi , Toast.LENGTH_SHORT).show();
                }
                else{
                    conlai=0;
                    Toast.makeText(XemChiTiet.this, "Đã thanh toán, Tiền thói lại: 0 VNĐ", Toast.LENGTH_SHORT).show();
                }

                fireapp.child(khach).setValue(new Table_XemCT(khach, phong, tongtien, nv, ngtra, ngthue, Datra, conlai.toString()));
                Toast.makeText(XemChiTiet.this, "Hoàn Thành Chi Tiet Thue", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

    }
    public static long calculateDays(Date dateEarly, Date dateLater) {
        return (dateLater.getTime() - dateEarly.getTime()) / (24 * 60 * 60 * 1000);
    }
    private void datePickerChange(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Log.d("Date", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);
        NgThue.setText(dayOfMonth +"/" + (month + 1) + "/" + year);
    }
    private void datePickerChange01(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Log.d("Date", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);
        NgTra.setText(dayOfMonth +"/" + (month + 1) + "/" + year);
    }

    private void showDate()  {
        int year = datethue.getYear();
        int month = datethue.getMonth(); // 0 - 11
        int day = datethue.getDayOfMonth();

        Toast.makeText(this, "Date: " + day+"-"+ (month + 1) +"-"+ year, Toast.LENGTH_LONG).show();
    }
    private void showDateTra()  {
        int year = datetra.getYear();
        int month = datetra.getMonth(); // 0 - 11
        int day = datetra.getDayOfMonth();

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
                myapp= new Intent(XemChiTiet.this, ThemKhachMoi.class);
                startActivity(myapp);
                break;
            case R.id.chitietthue:
                myapp= new Intent(XemChiTiet.this, ChiTietThue.class);
                startActivity(myapp);
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }
    public String KiemTraKhach(String x){
        String flag="TRUE";
        for(String y: LayMaKH){
            if( y.equals(x)){
                flag="FALSE";
            }
        }
        return flag;
    }
    public void XuLiSpinnerKhach(List<String> dd ){
        SpinnerAdapter adap= new ArrayAdapter<String>(XemChiTiet.this, android.R.layout.simple_dropdown_item_1line,dd);
        MaKhach.setAdapter(adap);
        MaKhach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Khach.setText(MaKhach.getItemAtPosition(i).toString());
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
            listLayMaKH.add(newContact);
            for(ThongTinKhach x: listLayMaKH){
                if(KiemTraKhach(x.MaKhach)=="TRUE"){
                    LayMaKH.add(x.MaKhach);
                }
            }
            XuLiSpinnerKhach(LayMaKH);
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