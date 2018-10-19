package com.example.administrator.testapp.model;

import com.example.administrator.testapp.listener.RequestResult;

import java.util.Map;

/**
 * Created by Administrator on 2018/9/12.
 */
public interface UserModal {
    void userLogin(Map<String, Object> params, RequestResult rst);
    void userRegister(Map<String,Object> params,RequestResult rst);
    void getUserInfo(Map<String,Object> params,RequestResult rst);
}
