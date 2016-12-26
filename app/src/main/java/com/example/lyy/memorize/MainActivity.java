package com.example.lyy.memorize;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.util.Collections.shuffle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private GridLayout GameView;
    private TextView start;
    private Button stopBtn;
    private Button[] gameBtn = new Button[16];
    private ArrayList<Integer> gameList = new ArrayList<Integer>();
    private int counter = 0;
    private int[] btnID = new int[2];
    private int[] btnValue = new int[2];
    private int count=16;
    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        bind();
    }

    private void bind(){

        GameView= (GridLayout) findViewById(R.id.GameView);
        chronometer= (Chronometer) findViewById(R.id.chronometer);

        start= (TextView) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime());//计时器清零
                chronometer.start();
                init();
            }
        });

        gameBtn[0]= (Button) findViewById(R.id.button1);
        gameBtn[1]= (Button) findViewById(R.id.button2);
        gameBtn[2]= (Button) findViewById(R.id.button3);
        gameBtn[3]= (Button) findViewById(R.id.button4);
        gameBtn[4]= (Button) findViewById(R.id.button5);
        gameBtn[5]= (Button) findViewById(R.id.button6);
        gameBtn[6]= (Button) findViewById(R.id.button7);
        gameBtn[7]= (Button) findViewById(R.id.button8);
        gameBtn[8]= (Button) findViewById(R.id.button9);
        gameBtn[9]= (Button) findViewById(R.id.button10);
        gameBtn[10]= (Button) findViewById(R.id.button11);
        gameBtn[11]= (Button) findViewById(R.id.button12);
        gameBtn[12]= (Button) findViewById(R.id.button13);
        gameBtn[13]= (Button) findViewById(R.id.button14);
        gameBtn[14]= (Button) findViewById(R.id.button15);
        gameBtn[15]= (Button) findViewById(R.id.button16);
    }

    private void init() {
        for (int i = 0; i < gameBtn.length; i++) {
            gameBtn[i].setTextColor(Color.BLACK);
            gameBtn[i].setOnClickListener(this);
        }
        setArrayListText();
    }

    public void setArrayListText() {
        for (int i = 0; i < 2; i++) {
            for (int ii = 1; ii < (gameBtn.length / 2) + 1; ii++) {
                gameList.add(ii);
            }
        }
        shuffle(gameList);

        int newLine = 0;
        for (int a = 0; a < gameList.size(); a++) {
            newLine++;
            System.out.print(" " + gameList.get(a));
            if (newLine == 4) {
                System.out.println();
                newLine = 0;
            }
        }
    }

    public boolean sameValues() {
        if (btnValue[0] == btnValue[1]) {
            return true;
        }
        return false;
    }

    public  static String getChronometerSeconds(Chronometer cmt) {
        int totalss = 0;
        String string = cmt.getText().toString();
        if(string.length()==7){
            String[] split = string.split(":");
            String string2 = split[0];
            int hour = Integer.parseInt(string2);
            int Hours =hour*3600;
            String string3 = split[1];
            int min = Integer.parseInt(string3);
            int Mins =min*60;
            int  SS =Integer.parseInt(split[2]);
            totalss = Hours+Mins+SS;
            return String.valueOf(totalss);
        }
        else if(string.length()==5){

            String[] split = string.split(":");
            String string3 = split[0];
            int min = Integer.parseInt(string3);
            int Mins =min*60;
            int  SS =Integer.parseInt(split[1]);

            totalss =Mins+SS;
            return String.valueOf(totalss);
        }
        return String.valueOf(totalss);


    }

    @Override
    public void onClick(View view) {
        for (int i = 0; i < gameBtn.length; i++) {
            if (gameBtn[i] == view) {
                gameBtn[i].setText("" + gameList.get(i));
                gameBtn[i].setEnabled(false);
                counter++;
                if (counter == 3) {
                    if (sameValues()) {
                        gameBtn[btnID[0]].setEnabled(false);
                        gameBtn[btnID[0]].setBackgroundColor(Color.parseColor("#ADD8E6"));
                        gameBtn[btnID[0]].setText(" ");
                        gameBtn[btnID[1]].setEnabled(false);
                        gameBtn[btnID[1]].setBackgroundColor(Color.parseColor("#ADD8E6"));
                        gameBtn[btnID[1]].setText(" ");
                        count=count-2;
                        if(count==2){
                            chronometer.stop();
                            String time=getChronometerSeconds(chronometer);
                            Toast.makeText(getApplicationContext(),"游戏结束",Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),"用时为："+time+"秒",Toast.LENGTH_SHORT).show();

                            //将此次用时存储
                            SharedPreferences.Editor editor=getSharedPreferences("Score",MODE_PRIVATE).edit();
                            editor.putString("time",time);
                            editor.apply();
                        }
                    } else {
                        gameBtn[btnID[0]].setEnabled(true);
                        gameBtn[btnID[0]].setText("");
                        gameBtn[btnID[1]].setEnabled(true);
                        gameBtn[btnID[1]].setText("");
                    }
                    counter = 1;
                }
                if (counter == 1) {
                    btnID[0] = i;
                    btnValue[0] = gameList.get(i);
                }
                if (counter == 2) {
                    btnID[1] = i;
                    btnValue[1] = gameList.get(i);
                }
            }
        }
    }
}
