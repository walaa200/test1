package com.example.myhccc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        splash=findViewById(R.id.splash);
       // splash.animate().translationY(-200).setDuration(800).setStartDelay(50);
       splash.animate().translationY(500).setDuration(900).setStartDelay(300);
        //splash.animate().rotation(50);
      // splash.animate().rotationBy(6);

        final ProgressDialog progressDialogspalsh=new ProgressDialog(MainActivity.this);
        progressDialogspalsh.setMessage("Welcome To HcCc");


        Handler sec =new Handler();

        sec.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountDownTimer a=new CountDownTimer(3000,1000) {
                    @Override
                    public void onTick(long l) {
                        progressDialogspalsh.show();
                    }

                    @Override
                    public void onFinish() {
                        progressDialogspalsh.cancel();
                        startActivity(new Intent(getApplicationContext(),log_in.class));
                        finish();
                    }
                }.start();
            }
        },3000);


    }
}