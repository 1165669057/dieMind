package com.example.administrator.testapp.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.testapp.R;
import com.example.administrator.testapp.bean.PubData;
import com.example.administrator.testapp.customView.CustomEdiText;
import com.example.administrator.testapp.listener.RequestResult;
import com.example.administrator.testapp.model.UserModalImpl;
import com.example.administrator.testapp.util.ConstData;
import com.example.administrator.testapp.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity implements View.OnClickListener,RequestResult<PubData>{
    @BindView(R.id.userName)
    CustomEdiText userName;
    @BindView(R.id.pwd)
    CustomEdiText pwd;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    @BindView(R.id.register)
    TextView register;

     private UserModalImpl userModal;
    Context context;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginBtn.setOnClickListener(this);
        register.setOnClickListener(this);
        context=this;
        //设置成本app可以访问
        sharedPreferences=getSharedPreferences(ConstData.FILE_FERENCES_NAME,Context.MODE_PRIVATE);
        progressDialog = new ProgressDialog(Login.this);//1.创建一个ProgressDialog的实例
        progressDialog.setMessage("正在加载中，请稍等......");//3.设置显示内容
        progressDialog.setCancelable(true);//4.设置可否用back键关闭对话框

    }
    private void login(){ //登录
        userModal=new UserModalImpl();
        Map<String,Object> params=new HashMap<>();
        String uname=userName.getEditableText().toString().trim();
        String mpwd=pwd.getEditableText().toString().trim();
        if(StringUtil.isNoValue(uname)){
            Toast.makeText(context,"手机号和邮箱不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        if(StringUtil.isNoValue(mpwd)){
            Toast.makeText(context,"密码不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        if(StringUtil.checkEmail(uname)||StringUtil.checkPhone(uname)){
            params.put("uname",uname);
            params.put("pwd",mpwd);
            progressDialog.show();//5.将ProgessDialog显示出来
            userModal.userLogin(params,Login.this);
        }else{
            Toast.makeText(context,"请输入正确手机号和邮箱",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginBtn:
                login();
                break;
            case R.id.register:
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    public void onSuccess(PubData pubData) {
         progressDialog.cancel();
        if(pubData.getCode()== ConstData.CODE_SUCCESS){
            progressDialog.cancel();
            sharedPreferences.edit().putString("token",pubData.getData().getToken())
            .putString("loginId",pubData.getData().getLoginId()).commit();
            Intent intent=new Intent(context,Home.class);
            intent.putExtra("id",pubData.getData().getUser().getId());
            intent.putExtra("loginId",pubData.getData().getLoginId());
            intent.putExtra("token",pubData.getData().getToken());
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(context,pubData.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailed(String msg) {
        progressDialog.cancel();
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
}
