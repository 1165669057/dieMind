package com.example.administrator.testapp.bean;

/**
 * Created by Administrator on 2018/9/26.
 */

public class RegisterInfo {
    private String error;//状态码
    private String message;

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
