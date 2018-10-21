package com.example.administrator.testapp.model;

import android.util.Log;

import com.example.administrator.testapp.application.MainApplication;
import com.example.administrator.testapp.bean.HomeDataUser;
import com.example.administrator.testapp.bean.PubData;
import com.example.administrator.testapp.bean.RegisterBeanOk;
import com.example.administrator.testapp.listener.RequestResult;
import com.example.administrator.testapp.net.UserServer;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/9/13.
 */
public class UserModalImpl implements UserModal {
    @Override
    public void userLogin(Map<String, Object> params,final RequestResult rst) {
        //创建网络接口请求的实例
        UserServer userServer= MainApplication.getRetrofit().create(UserServer.class);
        Call<PubData> call=userServer.userLogin(params);
        call.enqueue(new Callback<PubData>() {
            @Override
            public void onResponse(Call<PubData> call, Response<PubData> response) {
                 if(response.isSuccessful()&&response.body()!=null){
                     rst.onSuccess(response.body());
                 }else{
                     rst.onFailed(response.message());
                 }
            }
            @Override
            public void onFailure(Call<PubData> call, Throwable t) {
                        rst.onFailed(t.getMessage());
            }
        });
    }
    @Override
    public void userRegister(Map<String, Object> params,final RequestResult rst) {
        UserServer userServer= MainApplication.getRetrofit().create(UserServer.class);
        Call<RegisterBeanOk> call=userServer.userRegister(params);
        call.enqueue(new Callback<RegisterBeanOk>() {
            @Override
            public void onResponse(Call<RegisterBeanOk> call, Response<RegisterBeanOk> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    Log.e("------------------>>>>",response.toString());
                    rst.onSuccess(response.body());
                }else{
                  //  rst.onFailed(response.message());
                }
            }
            @Override
            public void onFailure(Call<RegisterBeanOk> call, Throwable t) {
                rst.onFailed(t.getMessage());
            }
        });
    }
    @Override
    public void getUserInfo(Map<String, Object> params,final RequestResult rst) {
        //创建网络接口请求的实例
        UserServer userServer= MainApplication.getRetrofit().create(UserServer.class);
        Call<HomeDataUser> call=userServer.getUserInfo(params);
        call.enqueue(new Callback<HomeDataUser>() {
            @Override
            public void onResponse(Call<HomeDataUser> call, Response<HomeDataUser> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    rst.onSuccess(response.body());
                }else{
                    rst.onFailed(response.message());
                }
            }
            @Override
            public void onFailure(Call<HomeDataUser> call, Throwable t) {
                rst.onFailed(t.getMessage());
            }
        });
    }

}
