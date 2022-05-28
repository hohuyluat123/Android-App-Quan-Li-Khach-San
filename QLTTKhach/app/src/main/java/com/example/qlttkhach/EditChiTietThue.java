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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EditChiTietThue extends AppCompatActivity {

    List<Table_XemCT> listchitiet;
    private DatabaseReference fireapp;
    EditText Khach;
    EditText Phong;
    EditText NhanVien;
    EditText NgThue;
    EditText NgTra;
    EditText DaTra;
    EditText TongTien;
    EditText ConLai;
    Button EDIT;
    String child;
    DatePicker datethue;
    DatePicker datetra;
    Button chonthue;
    Button chontra;
    Button Songay;
    EditText song;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_chi_tiet_thue);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.namsao);    //Icon muốn hiện thị
        actionBar.setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Khach= findViewById(R.id.textmakhachedit);
        Phong= findViewById(R.id.textmaphongedit);
        NhanVien=findViewById(R.id.textNhanVienNhanedit);
        TongTien= findViewById(R.id.texttongtienedit);
        DaTra= findViewById(R.id.textDaTraedit);
        ConLai= findViewById(R.id.textConLaiedit);
        NgThue= findViewById(R.id.textngaythueedit);
        NgTra=findViewById(R.id.textngaytraedit);
        EDIT=findViewById(R.id.Edit);
        datethue= findViewById(R.id.datepickerthue);
        datetra= findViewById(R.id.datepickertra);
        chonthue= findViewById(R.id.buttonngthue);
        chontra= findViewById(R.id.buttonngtra);
        Songay=findViewById(R.id.Date);
        song=findViewById(R.id.songay);
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
        Intent intent= getIntent();
        child=intent.getStringExtra("child");
        listchitiet= new ArrayList<>();
        fireapp= FirebaseDatabase.getInstance("https://doancuoiky-2bd47-default-rtdb.firebaseio.com/").getReference("ChiTietThue");
        fireapp.addChildEventListener(contactListener);

        EDIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String khach= Khach.getText().toString();
                String phong= Phong.getText().toString();
                String nv= NhanVien.getText().toString();
                String ngthue= NgThue.getText().toString();
                String ngtra= NgTra.getText().toString();
                String tongtien=TongTien.getText().toString();
                String datra= DaTra.getText().toString();
                Integer conlai= Integer.parseInt(tongtien)- Integer.parseInt(datra);
                fireapp.child(khach).setValue(new Table_XemCT(khach, phong, tongtien, nv, ngtra, ngthue, datra, conlai.toString()));
                Toast.makeText(EditChiTietThue.this, "Đã Update Chi Tiet Thue", Toast.LENGTH_SHORT).show();
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
                myapp= new Intent(EditChiTietThue.this, ThemKhachMoi.class);
                startActivity(myapp);
                break;
            case R.id.chitietthue:
                myapp= new Intent(EditChiTietThue.this, ChiTietThue.class);
                startActivity(myapp);
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }
    final ChildEventListener contactListener =new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            Table_XemCT newContact = snapshot.getValue(Table_XemCT.class);
            listchitiet.add(newContact);
            for (Table_XemCT x: listchitiet) {
                if(x.Khach.equals(child)){
                    Khach.setText(x.Khach);
                    Phong.setText(x.Phong);
                    NhanVien.setText(x.NhanVien);
                    NgThue.setText(x.NgayThue);
                    NgTra.setText(x.NgayTra);
                    TongTien.setText(x.TongTien);
                    DaTra.setText(x.DaTra);
                    ConLai.setText(x.ConLai);
                    break;
                }
            }
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