package com.example.qlttkhach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.TextUtils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference fireapp;


    private static final int MY_REQUEST_PERMISSION= 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Login= findViewById(R.id.login);
        EditText nametxt= findViewById(R.id.textName);
        EditText password= findViewById(R.id.password);
        RadioButton NhanVien= findViewById(R.id.radioNhanvien);
        RadioButton admin= findViewById(R.id.radioAdmin);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.namsao);    //Icon muốn hiện thị
        actionBar.setDisplayUseLogoEnabled(true);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* String name= nametxt.getText().toString();
                String pass= password.getText().toString();
                String vaitro= "";
                if( NhanVien.isChecked()){
                    vaitro="NhanVien";
                }else{
                    vaitro="Admin";
                }
*/
                Intent myapp= new Intent(MainActivity.this, GiaoDienNV.class);
                startActivity(myapp);
            }
        });
        Button DangKy=findViewById(R.id.dangky);
        DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myapp= new Intent(MainActivity.this, DangKyTaiKhoan.class);
                startActivity(myapp);
            }
        });
        fireapp= FirebaseDatabase.getInstance("https://doancuoiky-2bd47-default-rtdb.firebaseio.com/").getReference("QuanLyKhachSan");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        Intent myapp;
        switch(item.getItemId()){
            case R.id.Lienhe:
                Call();
                break;
            case R.id.thongtin:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void Call(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_REQUEST_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_REQUEST_PERMISSION:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Intent call= new Intent(Intent.ACTION_CALL);
                    Uri number= Uri.parse("tel: 0901883555");
                    call.setData(number);
                    startActivity(call);
                }else{
                    Toast a = Toast.makeText(MainActivity.this, "PERMISSION DENY", Toast.LENGTH_SHORT);
                    a.show();
                }
        }
    }
}