package com.example.haitran.xkch;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class menu_tk_tennhaxe extends AppCompatActivity {

    public static String[] tennhaxe = new String[500];
    public static String[] gioxuatben = new String[500];
    public  static String[] sodienthoai = new String[500];
    public  static String[] diemdi = new String[500];
    public  static String[] diemden = new String[500];
    public static String[] diqua = new String[500];
    public static int n=0;

    public static String[][] Nha_Xe_Tim;

    ImageButton imageButton;
    ImageButton imageButton1;
    EditText editText;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tk_tennhaxe);

        imageButton = (ImageButton) findViewById(R.id.id_back_timtennhaxe);
        imageButton1 = (ImageButton) findViewById(R.id.id_btn_tim_tennhaxe);
        editText = (EditText) findViewById(R.id.id_edt_tim_tennhaxe);
        listView = (ListView) findViewById(R.id.id_list_tim_tennhaxe);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mh = new Intent(menu_tk_tennhaxe.this,MainActivity.class);
                startActivity(mh);
            }
        });

        if(checkInternetConnection()==true)
            new ReadJSON().execute("https://script.google.com/macros/s/AKfycbxOLElujQcy1-ZUer1KgEvK16gkTLUqYftApjNCM_IRTL3HSuDk/exec?id=1jcKxhTfzjkiVuRiIuhjs_tRQBgxljIVTzypyZOsOaqE&sheet=trang1");
        else
            Toast.makeText(this, "Yêu cầu kết nối Internet", Toast.LENGTH_LONG).show();
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

            }

            timkiemtheodieukien();
        }

        public void timkiemtheodieukien(){

            imageButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(editText.getText().toString().trim() != null){
                        TextView bkqa = (TextView) findViewById(R.id.txt_bangketqua_tennhaxe);
                        Nha_Xe_Tim = new String[400][6];
                        String Tu_Khoa = editText.getText().toString().toLowerCase().trim();
                        int m=0;
                        for(int i=0;i<=n;i++){
                            if(tennhaxe[i].toLowerCase().trim().matches(".*"+Tu_Khoa+".*")){
                                Nha_Xe_Tim[m][0] = tennhaxe[i];
                                Nha_Xe_Tim[m][1] = sodienthoai[i];
                                Nha_Xe_Tim[m][2] = gioxuatben[i];
                                Nha_Xe_Tim[m][3] = diemdi[i];
                                Nha_Xe_Tim[m][4] = diemden[i];
                                Nha_Xe_Tim[m][5] = diqua[i];
                                m++;
                            }
                        }
                        if(m != 0){
                            String[] tnhaxe = new String[500];
                            String[] sdt = new String[500];
                            String[] gxb = new String[500];
                            String[] diem_di = new String[500];
                            String[] diem_den = new String[500];
                            for(int i=0;i<m;i++){
                                tnhaxe[i] = Nha_Xe_Tim[i][0];
                                sdt[i] = Nha_Xe_Tim[i][1];
                                gxb[i] = Nha_Xe_Tim[i][2];
                                diem_di[i] = Nha_Xe_Tim[i][3];
                                diem_den[i] = Nha_Xe_Tim[i][4];
                            }

                            bkqa.setText("Bảng kết quả");
                            bkqa.setTextColor(R.color.colorAccent);

                            MyAdapter adt = new MyAdapter(menu_tk_tennhaxe.this,tnhaxe,sdt,gxb,diem_di,diem_den,m);
                            listView.setAdapter(adt);
                        }else {
                            bkqa.setText("");
                            listView.setAdapter(null);
                            Toast.makeText(menu_tk_tennhaxe.this, "Không có kết quả", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(menu_tk_tennhaxe.this, "Nhập từ khóa", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final Dialog dialogt = new Dialog(menu_tk_tennhaxe.this);
                    dialogt.setContentView(R.layout.dialog_timkiem);

                    TextView tenx = (TextView) dialogt.findViewById(R.id.id_TenNhaXe_ChiTiet);
                    final TextView sodt = (TextView) dialogt.findViewById(R.id.id_SDT_ChiTiet);
                    TextView gdi = (TextView) dialogt.findViewById(R.id.id_GioXB_ChiTiet);
                    TextView ddi = (TextView) dialogt.findViewById(R.id.id_DiemDi_ChiTiet);
                    TextView dden = (TextView) dialogt.findViewById(R.id.id_DiemDen_ChiTiet);
                    TextView dqua = (TextView) dialogt.findViewById(R.id.id_DiQua_ChiTiet);

                    ImageButton goi = (ImageButton) dialogt.findViewById(R.id.btn_goidienthoai);
                    ImageButton thoat = (ImageButton) dialogt.findViewById(R.id.btn_thoat_dialog);

                    tenx.setText(Nha_Xe_Tim[position][0]);
                    sodt.setText(Nha_Xe_Tim[position][1]);
                    if(Integer.parseInt(Nha_Xe_Tim[position][2]) < 0 || Integer.parseInt(Nha_Xe_Tim[position][2]) > 23)
                    {
                        gdi.setText("--");
                    }else {
                        gdi.setText(Nha_Xe_Tim[position][2]);
                    }
                    ddi.setText(Nha_Xe_Tim[position][3]);
                    dden.setText(Nha_Xe_Tim[position][4]);
                    dqua.setText(Nha_Xe_Tim[position][5]);

                    goi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent goidien = new Intent(Intent.ACTION_CALL);
                            String number = sodt.getText().toString().trim();
                            goidien.setData(Uri.parse("tel:"+number));
                            if(ActivityCompat.checkSelfPermission(menu_tk_tennhaxe.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                                ActivityCompat.requestPermissions(menu_tk_tennhaxe.this,new String[]{android.Manifest.permission.CALL_PHONE},1);
                            }
                            else {
                                startActivity(goidien);
                            }
                        }
                    });

                    thoat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogt.cancel();
                        }
                    });

                    dialogt.show();
                }
            });
        }
    }
}
