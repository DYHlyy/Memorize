package com.example.lyy.memorize;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class Top extends AppCompatActivity {

    private TextView TopScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_top);

        //取消标题栏
        this.getSupportActionBar().hide();

        TopScore = (TextView) findViewById(R.id.TopScore);

        SharedPreferences preferences = getSharedPreferences("Score", MODE_PRIVATE);
        String time = preferences.getString("time", "");

        TopScore.setText("时间为:"+time+"s");


    }
}
