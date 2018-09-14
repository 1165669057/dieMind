package com.example.administrator.testapp.model;

import com.example.administrator.testapp.application.MainApplication;
import com.example.administrator.testapp.bean.UserInfo;
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
        Call<UserInfo> call=userServer.userLogin(params);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                 if(response.isSuccessful()&&response.body()!=null){
                     rst.onSuccess(response.body());
                 }else{
                     rst.onFailed(response.message());
                 }
            }
            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                        rst.onFailed(t.getMessage());
            }
        });
    }


}
