package com.example.administrator.testapp.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.administrator.testapp.MainActivity;
import com.example.administrator.testapp.R;
import com.example.administrator.testapp.application.MainApplication;
import com.example.administrator.testapp.bean.UserInfo;
import com.example.administrator.testapp.listener.RequestResult;
import com.example.administrator.testapp.model.UserModalImpl;
import com.example.administrator.testapp.net.UserServer;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener,RequestResult<UserInfo>{
    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.loginBtn)
    Button loginBtn;
     private UserModalImpl userModal;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginBtn.setOnClickListener(this);
        context=this;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginBtn:
                userModal=new UserModalImpl();
                 Map<String,Object> params=new HashMap<>();
                 String uname=userName.getEditableText().toString().trim();
                 String mpwd=pwd.getEditableText().toString().trim();
                 if(uname.equals("")&&mpwd.equals("")){
                     Toast.makeText(context,"请输入完整信息",Toast.LENGTH_LONG).show();
                 }else{
                     params.put("uname",uname);
                     params.put("pwd",mpwd);
                     userModal.userLogin(params,Login.this);
                 }
                break;
        }
    }
    @Override
    public void onSuccess(UserInfo userInfo) {
        Log.e("-------",userInfo.getError());
        if(userInfo.getError()==null){
            Intent intent=new Intent(context,MainActivity.class);
            intent.putExtra("uname",userInfo.getUname());
            startActivity(intent);
        }else{
            Toast.makeText(context,userInfo.getError(),Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onFailed(String msg) {

    }
}
