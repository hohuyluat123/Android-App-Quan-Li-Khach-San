package com.example.qlttkhach;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HoaDonTong extends AppCompatActivity {

    GridView gridview;
    List<Table_HoaDon> listhoadon;
    private DatabaseReference fireapp;
    Adapter_HoaDonTong adapter_hoaDonTong;
    String child;
    String maphong;
    Integer tongtien=0;
    Integer ConNo=0;
    int pos;
    EditText makhachtxt;
    EditText maphongtxt;
    EditText tongtientxt;
    EditText connotxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_tong);
        gridview= findViewById(R.id.gridviewHDT);
        adapter_hoaDonTong= new Adapter_HoaDonTong( HoaDonTong.this,  R.layout.activity_grid_hoa_don_tong);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.namsao);    //Icon muốn hiện thị
        actionBar.setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        makhachtxt=findViewById(R.id.textmakhachHDTong);
        maphongtxt=findViewById(R.id.textmaphongHDTong);
        tongtientxt=findViewById(R.id.textTongTienHDTong);
        connotxt=findViewById(R.id.textConNoHDTong);
        Intent intent= getIntent();
        child=intent.getStringExtra("child");
        maphong=intent.getStringExtra("MaPhong");
        FloatingActionButton Add= findViewById(R.id.floatingActionButtonHoaDon);
        listhoadon= new ArrayList<>();
        fireapp= FirebaseDatabase.getInstance("https://doancuoiky-2bd47-default-rtdb.firebaseio.com/").getReference("HoaDon");
        fireapp.addChildEventListener(contactListener);
        gridview.setAdapter(adapter_hoaDonTong);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pos = i;

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_chitietthue, menu);

        // Initialise menu item search bar
        // with id and take its object
        MenuItem searchViewItem
                = menu.findItem(R.id.search_bar);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        Intent myapp;
        switch(item.getItemId()){
            case R.id.themkhachmoi:
                myapp= new Intent(HoaDonTong.this, ThemKhachMoi.class);
                startActivity(myapp);
                break;
            case R.id.chitietthue:
                myapp= new Intent(HoaDonTong.this, ChiTietThue.class);
                startActivity(myapp);
                break;
            case R.id.hoadon:
                myapp= new Intent(HoaDonTong.this, HoaDonGDChinh.class);
                startActivity(myapp);
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void XuLiSetText(Integer tongtien, Integer conno){
        makhachtxt.setText(child);
        maphongtxt.setText(maphong);
        tongtientxt.setText(tongtien.toString());
        connotxt.setText(conno.toString());
    }
    final ChildEventListener contactListener =new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            Table_HoaDon newContact = snapshot.getValue(Table_HoaDon.class);
            //adapter_hoaDonTong.add(newContact);
            if( newContact.MaKh.equals(child)){
                adapter_hoaDonTong.add(newContact);
                tongtien=tongtien + Integer.parseInt(newContact.ThanhTien);
                ConNo= ConNo + Integer.parseInt(newContact.ConNo);
            }
            XuLiSetText(tongtien, ConNo);
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            Grid_ChiTiet newContact = snapshot.getValue(Grid_ChiTiet.class);
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