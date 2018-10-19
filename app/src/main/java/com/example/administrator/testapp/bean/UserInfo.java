package com.example.administrator.testapp.bean;

/**
 * Created by Administrator on 2018/9/12.
 */

public class UserInfo{

    private Integer id;//用户id
    private String uname;//用户名
    private String pwd;//密码
    private String phone;//手机号码
    private String userimg;//用户头像地址
    private Integer money;//文学币 1文学币等于0.1元
    private String openid;//

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public Integer getMoney() {
        return money;
    }
    public void setMoney(Integer money) {
        this.money = money;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOpenid() {
        return openid;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUname() {
        return uname;
    }

    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }
    public String getUserimg() {
        return userimg;
    }
}
