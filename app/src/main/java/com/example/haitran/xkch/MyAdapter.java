package com.example.haitran.xkch;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Hai Tran on 10/30/2017.
 */

public class MyAdapter extends ArrayAdapter<String> {

    String[] ten_nha_xe;
    String[] so_dien_thoai;
    String[] gio_xuat_ben;
    String[] diem_di;
    String[] diem_den;
    int n;
    Context mcontex;


    public MyAdapter(@NonNull Context context, String[] tennhaxe, String[] sodienthoai, String[] gioxuatben, String[] diemdi, String[] diemden,int _n) {
        super(context, R.layout.activity_main);

        this.ten_nha_xe=tennhaxe;
        this.so_dien_thoai=sodienthoai;
        this.gio_xuat_ben=gioxuatben;
        this.diem_di=diemdi;
        this.diem_den=diemden;
        this.mcontex=context;
        this.n = _n;
    }

    @Override
    public int getCount() {
        return n;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder=new ViewHolder();
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mcontex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_listview_nhaxe, parent, false);

            viewHolder.tvTenXe = (TextView) convertView.findViewById(R.id.id_TenNhaXe);
            viewHolder.tvSDT = (TextView) convertView.findViewById(R.id.id_sdt);
            viewHolder.tvGioXuatBen = (TextView) convertView.findViewById(R.id.id_GioXuatBen);
            viewHolder.tvdiemdi = (TextView) convertView.findViewById(R.id.id_txt_diemdi);
            viewHolder.tvdiemden = (TextView) convertView.findViewById(R.id.id_txt_diemden);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }

        viewHolder.tvTenXe.setText(ten_nha_xe[position]);
        viewHolder.tvSDT.setText(so_dien_thoai[position]);

        if(Integer.parseInt(gio_xuat_ben[position]) < 0 || Integer.parseInt(gio_xuat_ben[position]) > 23)
        {
            viewHolder.tvGioXuatBen.setText("--");
        }else {
            viewHolder.tvGioXuatBen.setText(gio_xuat_ben[position]);
        }

        viewHolder.tvdiemdi.setText(diem_di[position]);
        viewHolder.tvdiemden.setText(diem_den[position]);
        return convertView;
    }

    private static class ViewHolder {
        TextView tvTenXe;
        TextView tvSDT;
        TextView tvGioXuatBen;
        TextView tvdiemdi;
        TextView tvdiemden;
        }
}
