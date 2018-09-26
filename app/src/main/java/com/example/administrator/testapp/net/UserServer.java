package com.example.administrator.testapp.net;

import com.example.administrator.testapp.bean.RegisterInfo;
import com.example.administrator.testapp.bean.UserInfo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/8/12.
 */
public interface UserServer {
      @POST("login")
      @FormUrlEncoded
      Call<UserInfo> userLogin(@FieldMap Map<String,Object> param);

      @POST("register")
      @FormUrlEncoded
      Call<RegisterInfo> userRegister(@FieldMap Map<String,Object> param);
}