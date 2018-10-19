package com.example.administrator.testapp.bean;

/**
 * Created by Administrator on 2018/10/3.
 */

public class LoginBeanOk{
    private String loginId;
    private String token;
    private UserInfo user;
    public String getLoginId() {
        return loginId;
    }
    public String getToken() {
        return token;
    }
    public UserInfo getUser() {
        return user;
    }
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public void setUser(UserInfo user) {
        this.user = user;
    }
    @Override
    public String toString() {
        return "LoginBeanOk{" +
                "loginId='" + loginId + '\'' +
                ", token='" + token + '\'' +
                ", user=" + user +
                '}';
    }
}
