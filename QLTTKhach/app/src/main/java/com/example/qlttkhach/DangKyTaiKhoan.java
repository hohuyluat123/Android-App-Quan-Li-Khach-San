package com.example.qlttkhach;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.os.Bundle;
import android.widget.EditText;

public class DangKyTaiKhoan extends AppCompatActivity {

    private DatabaseReference fireapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_tai_khoan);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.namsao);    //Icon muốn hiện thị
        actionBar.setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        EditText nametxt= findViewById(R.id.editTextTenTKDK);
        EditText matkhautxt= findViewById(R.id.editTextMatKhauDK);
        EditText rematkhaitxt= findViewById(R.id.editTextReMatKhauDK);
        nametxt.getText().toString();
        matkhautxt.getText().toString();
        rematkhaitxt.getText().toString();


    }
}