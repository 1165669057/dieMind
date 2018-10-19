package com.example.administrator.testapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.testapp.application.MainApplication;
import com.example.administrator.testapp.controller.Home;
import com.example.administrator.testapp.controller.Login;
import com.example.administrator.testapp.util.ConstData;

import java.util.Map;
import java.util.Timer;

public class WelcomeActivity extends AppCompatActivity {
    Map<String,String> dataMap;
    String token;
    String loginId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        dataMap= MainApplication.getDataMap(this);
        token=dataMap.get("token");
        loginId=dataMap.get("loginId");
        new Handler().postDelayed(new Thread(){
            public  void run(){
                Intent mintent=null;
                if(token!=null&&loginId!=null&&!token.equals("")&&!loginId.equals("")){
                    mintent= new Intent(WelcomeActivity.this, Home.class);
                    mintent.putExtra("id",loginId);
                    mintent.putExtra("loginId",loginId);
                    mintent.putExtra("token",token);
                }else{
                     mintent = new Intent(WelcomeActivity.this, Login.class);
                }
                startActivity(mintent);
                finish();

            }
        },1000);
    }
}
