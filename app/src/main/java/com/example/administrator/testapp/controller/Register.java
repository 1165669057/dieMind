package com.example.administrator.testapp.controller;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.testapp.R;
import com.example.administrator.testapp.bean.RegisterBeanOk;
import com.example.administrator.testapp.customView.CustomEdiText;
import com.example.administrator.testapp.listener.RequestResult;
import com.example.administrator.testapp.model.UserModal;
import com.example.administrator.testapp.model.UserModalImpl;
import com.example.administrator.testapp.util.ConstData;
import com.example.administrator.testapp.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Register extends AppCompatActivity implements RequestResult<RegisterBeanOk>{
    @BindView(R.id.toolbar_register)
    Toolbar toolbar_register;
    @BindView(R.id.phone)
    CustomEdiText phone;
    @BindView(R.id.pwd)
    CustomEdiText pwd;
    @BindView(R.id.repwd)
    CustomEdiText repwd;
    @BindView(R.id.rstBtn)
    Button rstBtn;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        init();
    }
    private void init() {
        setSupportActionBar(toolbar_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_register.setNavigationIcon(R.mipmap.back_arrow);
        toolbar_register.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        progressDialog = new ProgressDialog(Register.this);//1.创建一个ProgressDialog的实例
        progressDialog.setMessage("正在加载中，请稍等......");//3.设置显示内容
        progressDialog.setCancelable(true);//4.设置可否用back键关闭对话框
    }
    private void register(){
        Map<String,Object> params=new HashMap<>();
        String uname=phone.getText().toString();
        String curpwd=pwd.getText().toString();
        String currepwd=repwd.getText().toString();
        if(StringUtil.isNoValue(uname)){
            Toast.makeText(this,"手机号和邮箱不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        if(StringUtil.isNoValue(curpwd)){
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        if(StringUtil.checkEmail(uname)||StringUtil.checkMobile(uname)){
            if(StringUtil.isNoValue(currepwd)){
                Toast.makeText(this,"请确认密码",Toast.LENGTH_LONG).show();
                return;
            }
            if(!currepwd.equals(curpwd)){
                Toast.makeText(this,"两次密码不一致",Toast.LENGTH_LONG).show();
                return;
            }
            if(StringUtil.strLength(curpwd)){
                Toast.makeText(this,"密码必须为6-16位字符",Toast.LENGTH_LONG).show();
                return;
            }
            params.put("uname",uname);
            params.put("pwd",curpwd);
            params.put("phone",uname);
            UserModal userModal=new UserModalImpl();
            progressDialog.show();
            userModal.userRegister(params,Register.this);
        }else{
            Toast.makeText(this,"请输入正确手机号和邮箱",Toast.LENGTH_LONG).show();
        }
    }
    //注册
    @OnClick(R.id.rstBtn)
    public void registerClick(){
        register();
    }
    @Override
    public void onSuccess(RegisterBeanOk registerBeanOk) {
        progressDialog.cancel();
        Log.e("---------------->",registerBeanOk.toString());
        if(registerBeanOk.getCode()== ConstData.CODE_SUCCESS){//注册成功
           double num=(double)registerBeanOk.getData().get("stu");
           if(num!=0){
               Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show();
               finish();
           }
        }else{
            Toast.makeText(this,registerBeanOk.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onFailed(String msg) {
        progressDialog.cancel();
        Log.e("---------------->",msg+"");
    }
}
