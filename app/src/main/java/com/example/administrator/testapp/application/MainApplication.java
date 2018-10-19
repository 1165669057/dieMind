package com.example.administrator.testapp.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.administrator.testapp.net.Server;
import com.example.administrator.testapp.util.ConstData;

import java.util.HashMap;
import java.util.Map;

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

    public static Map<String,String> getDataMap(Context context){
     SharedPreferences sharedPreferences=context.getSharedPreferences(ConstData.FILE_FERENCES_NAME,Context.MODE_PRIVATE);
     Map<String,String> map=new HashMap<>();
     map.put("token",sharedPreferences.getString("token",null));
      map.put("loginId",sharedPreferences.getString("loginId",null));
        return map;
    }
}
