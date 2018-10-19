package com.example.administrator.testapp.net;

import com.example.administrator.testapp.bean.HomeDataUser;
import com.example.administrator.testapp.bean.PubData;
import com.example.administrator.testapp.bean.RegisterBeanOk;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/8/12.
 */
public interface UserServer {
      @POST("login")
      @FormUrlEncoded
      Call<PubData> userLogin(@FieldMap Map<String,Object> param);

      @POST("register")
      @FormUrlEncoded
      Call<RegisterBeanOk> userRegister(@FieldMap Map<String,Object> param);
      @POST("getUserInfo")
      @FormUrlEncoded
      Call<HomeDataUser> getUserInfo(@FieldMap Map<String,Object> param);
}