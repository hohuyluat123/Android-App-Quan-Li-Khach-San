package com.example.qlttkhach;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class HoaDonGDChinh_Adapter  extends ArrayAdapter<Grid_ChiTiet> {

    //Dữ liệu liên kết bởi Adapter là một mảng các sản phẩm

    private Context mContext;
    int resource;

    public HoaDonGDChinh_Adapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.resource = resource;
        this.mContext = context;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View customView = inflater.inflate(this.resource, null);

        Grid_ChiTiet chiTiet = (Grid_ChiTiet) getItem(position);
        ((TextView) customView.findViewById(R.id.textKhachHD)).setText(chiTiet.Khach);
        ((TextView) customView.findViewById(R.id.textPhongHD)).setText(chiTiet.Phong);
        Button Xemchitiet = customView.findViewById(R.id.xemchitietHD);
        Xemchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, HoaDonTong.class);
                intent.putExtra("child", chiTiet.getKhach());
                intent.putExtra("MaPhong", chiTiet.getPhong());
                mContext.startActivity(intent);
            }
        });

        return customView;
    }
}
