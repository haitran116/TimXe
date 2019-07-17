package com.example.haitran.xkch;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Handler;

import static com.example.haitran.xkch.menu_main.diemdi_tk_gio;


public class menu_main extends AppCompatActivity {

    public static String[] tennhaxe = new String[500];
    public static String[] gioxuatben = new String[500];
    public  static String[] sodienthoai = new String[500];
    public  static String[] diemdi = new String[500];
    public  static String[] diemden = new String[500];
    public static String[] diqua = new String[500];

    public static String[][] Nha_xe_tk_diem_den;
    public static String[][] Nha_xe_tk_gio;

    public static String[] tnx_tk;
    public static String[] sdt_tk;
    public static String[] gioxb_tk;
    public static String[] diemdi_tk;
    public static String[] diemden_tk;

    public static String[] tnx_tk_gio = new String[400];
    public static String[] sdt_tk_gio = new String[400];
    public static String[] gioxb_tk_gio = new String[400];
    public static String[] diemdi_tk_gio = new String[400];
    public static String[] diemden_tk_gio = new String[400];

    public  static int kt;
    public  static int kt1;
    public  static int k=0;
    public  static int p;

    public static int n=0;


    Spinner spin_diemden;
    Spinner spin_giodi;
    ListView listView;
    ImageButton button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listView = (ListView) findViewById(R.id.LV_Nha_xe);
        spin_diemden = (Spinner) findViewById(R.id.id_spDiemDen);
        spin_giodi = (Spinner) findViewById(R.id.id_spGioDi);
        button1 = (ImageButton) findViewById(R.id.id_btn_back1);

        if(checkInternetConnection()==true)
            new ReadJSON().execute("https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1jcKxhTfzjkiVuRiIuhjs_tRQBgxljIVTzypyZOsOaqE&sheet=trang1");
        else
            Toast.makeText(this, "Yêu cầu kết nối Internet", Toast.LENGTH_LONG).show();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mh1 = new Intent(menu_main.this,MainActivity.class);
                startActivity(mh1);
            }
        });

    }

    private boolean checkInternetConnection() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
        // ARE WE CONNECTED TO THE NET
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {
            return true;
        }else {
            return false;
        }
    }

    public class ReadJSON extends AsyncTask<String, Void, String> {

        StringBuilder content = new StringBuilder();

        @Override
        protected String doInBackground(String... params) {

            try {
                URL url = new URL(params[0]);

                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                Toast.makeText(menu_main.this, "Lỗi ...", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
            }

            return content.toString();
        }

        private String VietHoa(String input){
            String result = "";
            input = input.toLowerCase();
            String[] arr = input.split(" ");
            for(String s : arr)
                result += String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " ";
            return result;
        }



        @Override
        protected void onPostExecute(String s) {
            int y = s.length();

            try {
                JSONArray jsonArray=new JSONArray(s.substring(10,y));
                int l=0;

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    tennhaxe[l] = VietHoa(jsonObject.getString("tenxe"));
                    sodienthoai[l] = jsonObject.getString("sodienthoai");
                    gioxuatben[l] = jsonObject.getString("giodi");
                    diemdi[l] = jsonObject.getString("diemdi");
                    diemden[l] = jsonObject.getString("diemden");
                    diqua[l] = jsonObject.getString("diqua");
                    l++;
                }
                n=l-1;

            } catch (JSONException e) {
                Toast.makeText(menu_main.this, "Lỗi ...", Toast.LENGTH_SHORT).show();
            }

            timkiemtheodieukien();
        }
    }


    public void timkiemtheodieukien() {

        final ArrayList<String> arrayList = new ArrayList<>();

        final String[] gioVN = {"Tất cả","0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};

        final String[] TinhThanhVN = {
                "An Giang" ,
                "Bà Rịa – Vũng Tàu" ,
                "Bạc Liêu" ,
                "Bắc Kạn" ,
                "Bắc Giang" ,
                "Bắc Ninh" ,
                "Bến Tre" ,
                "Bình Dương" ,
                "Bình Định" ,
                "Bình Phước" ,
                "Bình Thuận" ,
                "Cà Mau" ,
                "Cao Bằng" ,
                "Cần Thơ" ,
                "Đà Nẵng" ,
                "Đắk Lắk" ,
                "Đắk Nông" ,
                "Điện Biên" ,
                "Đồng Nai" ,
                "Đồng Tháp" ,
                "Gia Lai" ,
                "Hà Giang" ,
                "Hà Nam" ,
                "Hà Nội" ,
                "Hà Tĩnh" ,
                "Hải Dương" ,
                "Hải Phòng" ,
                "Hòa Bình" ,
                "Hồ Chí Minh" ,
                "Hậu Giang" ,
                "Hưng Yên" ,
                "Khánh Hòa" ,
                "Kiên Giang" ,
                "Kon Tum" ,
                "Lai Châu" ,
                "Lào Cai" ,
                "Lạng Sơn" ,
                "Lâm Đồng" ,
                "Long An" ,
                "Nam Định" ,
                "Nghệ An" ,
                "Ninh Bình" ,
                "Ninh Thuận" ,
                "Phú Thọ" ,
                "Phú Yên" ,
                "Quảng Bình" ,
                "Quảng Nam" ,
                "Quảng Ngãi" ,
                "Quảng Ninh" ,
                "Quảng Trị" ,
                "Sóc Trăng" ,
                "Sơn La" ,
                "Tây Ninh" ,
                "Thái Bình" ,
                "Thái Nguyên" ,
                "Thanh Hóa" ,
                "Thừa Thiên – Huế" ,
                "Tiền Giang" ,
                "Trà Vinh" ,
                "Tuyên Quang" ,
                "Vĩnh Long" ,
                "Vĩnh Phúc" ,
                "Yên Bái"
        };

        arrayList.add("Tất cả");

        for(int i=0;i<=62;i++){
            arrayList.add(TinhThanhVN[i]);
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(menu_main.this,android.R.layout.simple_spinner_dropdown_item,arrayList);
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(menu_main.this,android.R.layout.simple_spinner_dropdown_item,gioVN);


        spin_diemden.setAdapter(adapter);
        spin_giodi.setAdapter(adapter1);


        spin_diemden.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spin_giodi.setSelection(0);
                if(!arrayList.get(position).equals(arrayList.get(0)))
                {
                    kt = 2;
                    kt1 = 2;
                    int m=0;
                    Nha_xe_tk_diem_den = new String[500][6];
                    for(int i=0;i<=n;i++){
                        if(diemden[i].toLowerCase().trim().matches("(.*)"+arrayList.get(position).toLowerCase().trim()+"(.*)")
                                || diemdi[i].toLowerCase().trim().matches("(.*)"+arrayList.get(position).toLowerCase().trim()+"(.*)")) {
                            Nha_xe_tk_diem_den[m][0] = tennhaxe[i];
                            Nha_xe_tk_diem_den[m][1] = sodienthoai[i];
                            Nha_xe_tk_diem_den[m][2] = gioxuatben[i];
                            Nha_xe_tk_diem_den[m][3] = diemdi[i];
                            Nha_xe_tk_diem_den[m][4] = diemden[i];
                            Nha_xe_tk_diem_den[m][5] = diqua[i];
                            m++;
                        }
                    }

                    if(m != 0)
                    {
                        tnx_tk = new String[500];
                        sdt_tk = new String[500];
                        gioxb_tk = new String[500];
                        diemdi_tk = new String[500];
                        diemden_tk = new String[500];
                        for(int i=0;i<m;i++)
                        {
                            tnx_tk[i] = Nha_xe_tk_diem_den[i][0];
                            sdt_tk[i] = Nha_xe_tk_diem_den[i][1];
                            gioxb_tk[i] = Nha_xe_tk_diem_den[i][2];
                            diemdi_tk[i] = Nha_xe_tk_diem_den[i][3];
                            diemden_tk[i] = Nha_xe_tk_diem_den[i][4];
                        }

                        p = m;

                        MyAdapter arrayAdapter1 = new MyAdapter(menu_main.this,tnx_tk,sdt_tk,gioxb_tk,diemdi_tk,diemden_tk,m);
                        listView.setAdapter(arrayAdapter1);
                    }else {
                        listView.setAdapter(null);
                        Toast.makeText(menu_main.this, "Không tìm thấy tuyến này, nếu có dữ liệu sẽ được cập nhập !", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    kt = 1;
                    kt1 = 1;
                    MyAdapter arrayAdapter = new MyAdapter(menu_main.this,tennhaxe,sodienthoai,gioxuatben,diemdi,diemden,n+1);
                    listView.setAdapter(arrayAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spin_giodi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {             // spin 2
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(gioVN[position].equals(gioVN[0])){
                            if(kt == 1)     // tat ca diem den + tat ca gio
                            {
                                kt1 = 1;
                                MyAdapter arrayAdapter3 = new MyAdapter(menu_main.this,tennhaxe,sodienthoai,gioxuatben,diemdi,diemden,n+1);
                                listView.setAdapter(arrayAdapter3);
                            }
                            if(kt == 2){
                                // da chon 1 diem den + tat ca gio
                                kt1 = 2;
                                MyAdapter arrayAdapter4 = new MyAdapter(menu_main.this,tnx_tk,sdt_tk,gioxb_tk,diemdi_tk,diemden_tk,p);
                                listView.setAdapter(arrayAdapter4);
                            }
                }
                else {
                    if(kt == 1)    // tat ca diem den + chon 1 gio nao do
                    {
                        kt1 = 3;
                        Nha_xe_tk_gio =new String[500][6];

                        int x=0;
                        int i=0;
                        while (i<=n)
                        {
                            if(Integer.parseInt(gioxuatben[i]) == Integer.parseInt(gioVN[position]))
                            {
                                Nha_xe_tk_gio[x][0] = tennhaxe[i];
                                Nha_xe_tk_gio[x][1] = sodienthoai[i];
                                Nha_xe_tk_gio[x][2] = gioxuatben[i];
                                Nha_xe_tk_gio[x][3] = diemdi[i];
                                Nha_xe_tk_gio[x][4] = diemden[i];
                                Nha_xe_tk_gio[x][5] = diqua[i];
                                x++;
                            }
                            i++;
                        }

                        if(x!=0){
                            for(int j=0;j<x;j++){
                                tnx_tk_gio[j] = Nha_xe_tk_gio[j][0];
                                sdt_tk_gio[j] = Nha_xe_tk_gio[j][1];
                                gioxb_tk_gio[j] = Nha_xe_tk_gio[j][2];
                                diemdi_tk_gio[j] = Nha_xe_tk_gio[j][3];
                                diemden_tk_gio[j] = Nha_xe_tk_gio[j][4];
                            }
                            MyAdapter arrayAdapter5 = new MyAdapter(menu_main.this,tnx_tk_gio,sdt_tk_gio,gioxb_tk_gio,diemdi_tk_gio,diemden_tk_gio,x);
                            listView.setAdapter(arrayAdapter5);
                        }
                        else {
                            listView.setAdapter(null);
                            Toast.makeText(menu_main.this, "Không tìm thấy !", Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(kt == 2)
                    {   // chon 1 diem den + chon 1 gio nao do
                        kt1 = 3;
                        Nha_xe_tk_gio =new String[200][6];
                        int x=0;
                        int i=0;
                        while (i<p)
                        {
                            if(Integer.parseInt(Nha_xe_tk_diem_den[i][2]) == Integer.parseInt(gioVN[position]))
                            {
                                Nha_xe_tk_gio[x][0] = Nha_xe_tk_diem_den[i][0];
                                Nha_xe_tk_gio[x][1] = Nha_xe_tk_diem_den[i][1];
                                Nha_xe_tk_gio[x][2] = Nha_xe_tk_diem_den[i][2];
                                Nha_xe_tk_gio[x][3] = Nha_xe_tk_diem_den[i][3];
                                Nha_xe_tk_gio[x][4] = Nha_xe_tk_diem_den[i][4];
                                Nha_xe_tk_gio[x][5] = Nha_xe_tk_diem_den[i][5];
                                x++;
                            }
                            i++;
                        }

                        if(x!=0){
                            for(i = 0; i<x; i++){
                                tnx_tk_gio[i] = Nha_xe_tk_gio[i][0];
                                sdt_tk_gio[i] = Nha_xe_tk_gio[i][1];
                                gioxb_tk_gio[i] = Nha_xe_tk_gio[i][2];
                                diemdi_tk_gio[i] = Nha_xe_tk_gio[i][3];
                                diemden_tk_gio[i] = Nha_xe_tk_gio[i][4];
                            }

                            MyAdapter arrayAdapter6 = new MyAdapter(menu_main.this,tnx_tk_gio,sdt_tk_gio,gioxb_tk_gio,diemdi_tk_gio,diemden_tk_gio,x);
                            listView.setAdapter(arrayAdapter6);
                        }
                        else {
                            listView.setAdapter(null);
                            Toast.makeText(menu_main.this, "Không tìm thấy !", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Dialog dialog = new Dialog(menu_main.this);
                dialog.setContentView(R.layout.dialog_timkiem);

                TextView tenx = (TextView) dialog.findViewById(R.id.id_TenNhaXe_ChiTiet);
                final TextView sodt = (TextView) dialog.findViewById(R.id.id_SDT_ChiTiet);
                TextView gdi = (TextView) dialog.findViewById(R.id.id_GioXB_ChiTiet);
                TextView ddi = (TextView) dialog.findViewById(R.id.id_DiemDi_ChiTiet);
                TextView dden = (TextView) dialog.findViewById(R.id.id_DiemDen_ChiTiet);
                TextView dqua = (TextView) dialog.findViewById(R.id.id_DiQua_ChiTiet);

                ImageButton goi = (ImageButton) dialog.findViewById(R.id.btn_goidienthoai);
                ImageButton thoat = (ImageButton) dialog.findViewById(R.id.btn_thoat_dialog);

                if(kt == 1){ // tất cả điểm đến
                    if(kt1 == 1)  // + tất cả giờ
                    {
                        tenx.setText(tennhaxe[position]);
                        sodt.setText(sodienthoai[position]);
                        ddi.setText(diemdi[position]);
                        dden.setText(diemden[position]);
                        dqua.setText(diqua[position]);
                        if(Integer.parseInt(gioxuatben[position]) < 0 || Integer.parseInt(gioxuatben[position]) > 23)
                        {
                            gdi.setText("--");
                        }else {
                            gdi.setText(gioxuatben[position]);
                        }
                    }
                    if(kt1 == 3) // + chọn 1 giờ nào đó
                    {
                        tenx.setText(Nha_xe_tk_gio[position][0]);
                        sodt.setText(Nha_xe_tk_gio[position][1]);
                        gdi.setText(Nha_xe_tk_gio[position][2]);
                        ddi.setText(Nha_xe_tk_gio[position][3]);
                        dden.setText(Nha_xe_tk_gio[position][4]);
                        dqua.setText(Nha_xe_tk_gio[position][5]);
                    }
                }

                if(kt == 2 ){  // đã chọn 1 điểm đến
                    if(kt1 == 2) // + tất cả giờ
                    {
                        tenx.setText(Nha_xe_tk_diem_den[position][0]);
                        sodt.setText(Nha_xe_tk_diem_den[position][1]);
                        ddi.setText(Nha_xe_tk_diem_den[position][3]);
                        dden.setText(Nha_xe_tk_diem_den[position][4]);
                        dqua.setText(Nha_xe_tk_diem_den[position][5]);
                        if(Integer.parseInt(Nha_xe_tk_diem_den[position][2]) < 0 || Integer.parseInt(Nha_xe_tk_diem_den[position][2]) > 23)
                        {
                            gdi.setText("--");
                        }else {
                            gdi.setText(Nha_xe_tk_diem_den[position][2]);
                        }
                    }
                    if(kt1 == 3) // + chọn 1 giờ nào đó
                    {
                        tenx.setText(Nha_xe_tk_gio[position][0]);
                        sodt.setText(Nha_xe_tk_gio[position][1]);
                        gdi.setText(Nha_xe_tk_gio[position][2]);
                        ddi.setText(Nha_xe_tk_gio[position][3]);
                        dden.setText(Nha_xe_tk_gio[position][4]);
                        dqua.setText(Nha_xe_tk_gio[position][5]);
                    }
                }

                goi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent call = new Intent(Intent.ACTION_CALL);
                        call.setData(Uri.parse("tel:"+sodt.getText().toString().trim()));
                        if(ActivityCompat.checkSelfPermission(menu_main.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(menu_main.this,new String[]{android.Manifest.permission.CALL_PHONE},1);
                        }
                        else {
                            startActivity(call);
                        }
                    }
                });


                thoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });

    }

}
