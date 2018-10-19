package com.example.administrator.testapp.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/9/12.
 */
public class Server {
    private static String TestHost="http://192.168.31.148:8080/openMind/";//测试服务器
    private static String ImgHost="";//图片服务器
    private static Retrofit retrofit;
    public Server(){}
    public static Retrofit iniRetrofit(){
         retrofit=new Retrofit.Builder().baseUrl(TestHost)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       return retrofit;
    }
}

