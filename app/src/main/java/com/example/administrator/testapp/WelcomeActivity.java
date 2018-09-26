package com.example.administrator.testapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.testapp.controller.Home;
import com.example.administrator.testapp.controller.Login;

import java.util.Timer;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Thread(){
          public  void run(){
              Intent mintent = new Intent(WelcomeActivity.this, Login.class);
              startActivity(mintent);
              finish();
            }
        },1000);
    }
}
