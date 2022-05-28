package com.example.qlttkhach;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.annotation.NonNull;

public class Adapter_HoaDonTong  extends ArrayAdapter<Table_HoaDon> {

    //Dữ liệu liên kết bởi Adapter là một mảng các sản phẩm

    private Context mContext;
    int resource;
    private DatabaseReference fireapp;
    public Adapter_HoaDonTong(@NonNull Context context, int resource) {
        super(context, resource);
        this.resource = resource;
        this.mContext = context;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View customView = inflater.inflate(this.resource, null);
        fireapp= FirebaseDatabase.getInstance("https://doancuoiky-2bd47-default-rtdb.firebaseio.com/").getReference("HoaDon");
        Table_HoaDon hoadon = (Table_HoaDon) getItem(position);
        ((TextView) customView.findViewById(R.id.textmaKhachGridHDT)).setText(hoadon.MaKh);
        ((TextView) customView.findViewById(R.id.textMaHDGridHDT)).setText(hoadon.MaHD);
        ((TextView) customView.findViewById(R.id.textmaPhongGridHDT)).setText(hoadon.MaPh);
        ((TextView) customView.findViewById(R.id.textNgSDGridHDT)).setText(hoadon.NgaySD);
        ((TextView) customView.findViewById(R.id.textSlGridHDT)).setText(hoadon.SoLuong);
        ((TextView) customView.findViewById(R.id.textTTienGridHDT)).setText(hoadon.ThanhTien);
        ((TextView) customView.findViewById(R.id.textConNoGridHDT)).setText(hoadon.ConNo);
        ((TextView) customView.findViewById(R.id.textmaDvGridHDT)).setText(hoadon.MaDV);
        Button Edit = customView.findViewById(R.id.btEditGridHDT);
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String makh= ((TextView) customView.findViewById(R.id.textmaKhachGridHDT)).getText().toString();
                String maHD= ((TextView) customView.findViewById(R.id.textMaHDGridHDT)).getText().toString();
                String maph= ((TextView) customView.findViewById(R.id.textmaPhongGridHDT)).getText().toString();
                String madv= ((TextView) customView.findViewById(R.id.textmaDvGridHDT)).getText().toString();
                String ngsd= ((TextView) customView.findViewById(R.id.textNgSDGridHDT)).getText().toString();
                String sl= ((TextView) customView.findViewById(R.id.textSlGridHDT)).getText().toString();
                String ttien= ((TextView) customView.findViewById(R.id.textTTienGridHDT)).getText().toString();
                String conno= ((TextView) customView.findViewById(R.id.textConNoGridHDT)).getText().toString();
                fireapp.child(maHD).setValue(new Table_HoaDon(maHD,makh, madv, maph, ngsd, sl, ttien, conno));
                Toast.makeText(mContext, "Đã Update Hóa Đơn", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, HoaDonTong.class);
                intent.putExtra("child", hoadon.getMaKh());
                intent.putExtra("MaPhong", hoadon.getMaPh());
                mContext.startActivity(intent);
            }
        });

        return customView;
    }
}
