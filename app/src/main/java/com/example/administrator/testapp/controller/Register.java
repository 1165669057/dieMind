package com.example.administrator.testapp.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.testapp.R;
import com.example.administrator.testapp.bean.RegisterInfo;
import com.example.administrator.testapp.listener.RequestResult;
import com.example.administrator.testapp.model.UserModal;
import com.example.administrator.testapp.model.UserModalImpl;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Register extends AppCompatActivity implements RequestResult<RegisterInfo>{
    @BindView(R.id.toolbar_register)
    Toolbar toolbar_register;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.repwd)
    EditText repwd;
    @BindView(R.id.rstBtn)
    Button rstBtn;
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
    }
    //注册
    @OnClick(R.id.rstBtn)
    public void registerClick(){
        Map<String,Object> params=new HashMap<>();
        params.put("uname","zs");
        params.put("pwd","654321");
        params.put("phone","18268045145");
       UserModal userModal=new UserModalImpl();
       userModal.userRegister(params,Register.this);
    }

    @Override
    public void onSuccess(RegisterInfo registerInfo) {
        Log.e("---------------->",registerInfo.getMessage()+"");
    }


    @Override
    public void onFailed(String msg) {
        Log.e("---------------->",msg+"");
    }
}
