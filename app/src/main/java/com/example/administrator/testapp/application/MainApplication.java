package com.example.administrator.testapp.application;

import android.app.Application;

import com.example.administrator.testapp.net.Server;

import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/9/12.
 */

public class MainApplication extends Application{
    private static  Retrofit retrofit;
    @Override
    public void onCreate() {
        super.onCreate();
        retrofit=Server.iniRetrofit();
    }
    public static Retrofit getRetrofit(){
        if(retrofit!=null){
            return retrofit;
        }else {
            throw new IllegalStateException("the object of Retrofit is null");
        }

    }
}
