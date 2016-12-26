package com.example.lyy.memorize;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Hello extends AppCompatActivity {

    private Button Start,Top,Help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_hello);
        this.getSupportActionBar().hide();

        Start= (Button) findViewById(R.id.Start_Btn);
        Top= (Button) findViewById(R.id.TopScore_Btn);
        Help= (Button) findViewById(R.id.Help_Btn);

        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Hello.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Hello.this,Top.class);
                startActivity(intent);
            }
        });

        Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Hello.this,Help.class);
                startActivity(intent);
            }
        });



    }

}
