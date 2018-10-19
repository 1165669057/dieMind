package com.example.administrator.testapp.bean;

/**
 * Created by Administrator on 2018/10/4.
 *  公共数据bean
 *
 */
public abstract class Data {
    private int code;
    private String message;
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Data{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
