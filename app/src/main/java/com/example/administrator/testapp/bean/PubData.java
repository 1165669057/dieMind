package com.example.administrator.testapp.bean;

import java.util.Map;

/**
 * Created by Administrator on 2018/10/3.
 */
public class PubData extends Data{
    private LoginBeanOk data;
    public LoginBeanOk getData() {
        return data;
    }
    public void setData(LoginBeanOk data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "PubData{" +
                "data=" + data +
                '}';
    }
}
