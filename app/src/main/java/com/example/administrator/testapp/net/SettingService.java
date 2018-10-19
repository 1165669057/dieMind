package com.example.administrator.testapp.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Path;

/**
 * Created by Administrator on 2018/9/28.
 */
public interface SettingService {
    @GET("getModelMenus")
    Call getModelMenus(@Path("loginId") int loginId);
}
