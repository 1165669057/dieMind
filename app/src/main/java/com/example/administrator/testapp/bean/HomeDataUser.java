package com.example.administrator.testapp.bean;

import java.util.Map;

/**
 * Created by Administrator on 2018/10/4.
 */

public class HomeDataUser extends Data{
    private Map<String,UserInfo> data;

    public Map<String, UserInfo> getData() {
        return data;
    }

    public void setData(Map<String, UserInfo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HomeDataUser{" +
                "data=" + data +
                '}';
    }
}
