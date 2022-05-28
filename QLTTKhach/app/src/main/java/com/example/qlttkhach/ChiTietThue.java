package com.example.qlttkhach;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.MenuItemCompat;

import android.Manifest;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChiTietThue extends AppCompatActivity {

    List<Grid_ChiTiet> listchitiet;
    private DatabaseReference fireapp;
    AdapterChiTietThue adapterChiTietThue;
    int pos;
    private static final int CALL_PERMISSION_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_thue);
        adapterChiTietThue= new AdapterChiTietThue( ChiTietThue.this,  R.layout.activity_grid_chi_tiet);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.namsao);    //Icon muốn hiện thị
        actionBar.setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        FloatingActionButton Add= findViewById(R.id.floatingActionButton);
        listchitiet= new ArrayList<>();
        fireapp= FirebaseDatabase.getInstance("https://doancuoiky-2bd47-default-rtdb.firebaseio.com/").getReference("ChiTietThue");
        fireapp.addChildEventListener(contactListener);
        GridView gridView= findViewById(R.id.gridview);
        gridView.setAdapter(adapterChiTietThue);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent insert= new Intent(ChiTietThue.this, XemChiTiet.class);
                startActivity(insert);

            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                myapp= new Intent(ChiTietThue.this, ThemKhachMoi.class);
                startActivity(myapp);
                break;
            case R.id.chitietthue:
                myapp= new Intent(ChiTietThue.this, ChiTietThue.class);
                startActivity(myapp);
                break;
            case R.id.hoadon:
                myapp= new Intent(ChiTietThue.this, HoaDonGDChinh.class);
                startActivity(myapp);
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }
    final ChildEventListener contactListener =new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            Grid_ChiTiet newContact = snapshot.getValue(Grid_ChiTiet.class);
            adapterChiTietThue.add(newContact);
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