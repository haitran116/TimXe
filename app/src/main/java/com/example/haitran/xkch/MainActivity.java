package com.example.haitran.xkch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        button1 = (Button) findViewById(R.id.id_btn1);
        button2 = (Button) findViewById(R.id.id_btn2);
        button3 = (Button) findViewById(R.id.btn_lienhe);
        button4 = (Button) findViewById(R.id.btn_timkiemtennhaxe);
        button5 = (Button) findViewById(R.id.btn_thoatapp);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mo = new Intent(MainActivity.this,menu_main.class);
                startActivity(mo);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mo1 = new Intent(MainActivity.this,menu_tim_vitri.class);
                startActivity(mo1);

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mo2 = new Intent(MainActivity.this,lienhe.class);
                startActivity(mo2);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mo3 = new Intent(MainActivity.this,menu_tk_tennhaxe.class);
                startActivity(mo3);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
    }
}

