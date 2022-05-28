package com.example.qlttkhach;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

public class AdapterChiTietThue extends ArrayAdapter<Grid_ChiTiet> {

    //Dữ liệu liên kết bởi Adapter là một mảng các sản phẩm

    private Context mContext;
    int resource;
    public AdapterChiTietThue( @NonNull Context context, int resource) {
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
        ((TextView) customView.findViewById(R.id.textKhach)).setText(chiTiet.Khach);
        ((TextView) customView.findViewById(R.id.textPhong)).setText(chiTiet.Phong);
        Button Xemchitiet = customView.findViewById(R.id.xemchitiet);
        Xemchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(mContext, EditChiTietThue.class);
                intent.putExtra("child", chiTiet.getKhach());
                mContext.startActivity(intent);
            }
        });
        Button Edit = customView.findViewById(R.id.editgrid);
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(mContext, EditChiTietThue.class);
                edit.putExtra("child", chiTiet.getKhach());
                mContext.startActivity(edit);
            }
        });
        return customView;
    }
}
