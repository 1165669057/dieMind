package com.example.administrator.testapp.bean;

import java.util.Map;

/**
 * Created by Administrator on 2018/10/5.
 */

public class RegisterBeanOk extends Data {
   private Map<String,Object> data;

    @Override
    public void setCode(int code) {
        super.setCode(code);
    }

    public Map<String, Object> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "RegisterBeanOk{" +
                "data=" + data +
                '}';
    }
}
