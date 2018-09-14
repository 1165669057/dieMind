package com.example.administrator.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.administrator.testapp.controller.Login;
import com.example.administrator.testapp.fragment.MyFragment1;
import com.example.administrator.testapp.fragment.MyFragment2;
import com.example.administrator.testapp.helpClass.BottomNavigationViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity implements View.OnClickListener{
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    FragmentManager fragmentManager;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragment(R.id.myLayout2,new MyFragment1(),"tag2");
                    return true;
                case R.id.navigation_dashboard:

                    return true;
                case R.id.navigation_notifications:
                    return true;
                case R.id.navigation_buy:
                    replaceFragment(R.id.myLayout2,new MyFragment2(),"tag3");
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }
    private void init() {
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fragmentManager=getSupportFragmentManager();//得到FragmentManager管理对象
        //初始化第一个Fragment;
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        MyFragment1 myFragment1=new MyFragment1();
        fragmentTransaction.add(R.id.myLayout2,myFragment1,"tag1");
        fragmentTransaction.commit();
    }
    //替换fragment
    protected void replaceFragment(int layoutId,Fragment frag,String tag){
         /*  FragmentManager 管理Activity中的fragment 会将fragment添加一个已经存在的ViewGroup
           FragmentTranscation 事务 对fragment进行添加 移除 替换 和其他事动作
          提交的一组fragment的变化叫做一个事务
          */
        FragmentTransaction mFragmentTransaction=fragmentManager.beginTransaction();
        mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        //自定义动画
        mFragmentTransaction.replace(layoutId,frag,tag);
        mFragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}