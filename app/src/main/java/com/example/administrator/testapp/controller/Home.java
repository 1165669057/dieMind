package com.example.administrator.testapp.controller;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.testapp.MainActivity;
import com.example.administrator.testapp.R;
import com.example.administrator.testapp.adapter.HomeViewAdapter;
import com.example.administrator.testapp.application.GlideApp;
import com.example.administrator.testapp.application.MyAppGlideModule;
import com.example.administrator.testapp.bean.HomeDataUser;
import com.example.administrator.testapp.bean.UserInfo;
import com.example.administrator.testapp.fragment.MyFragment1;
import com.example.administrator.testapp.fragment.MyFragment2;
import com.example.administrator.testapp.fragment.MyFragment3;
import com.example.administrator.testapp.fragment.MyFragment4;
import com.example.administrator.testapp.listener.RequestResult;
import com.example.administrator.testapp.model.UserModalImpl;
import com.example.administrator.testapp.util.ConstData;
import com.example.administrator.testapp.util.IntentEnterUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Home extends AppCompatActivity implements View.OnClickListener,DrawerLayout.DrawerListener,
        View.OnTouchListener,RequestResult<HomeDataUser>
{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.myDrawerLayout)
    DrawerLayout myDrawerLayout;
    @BindView(R.id.myViewPage)
    ViewPager myViewPage;
    @BindView(R.id.naview)
    NavigationView navigationView;
    @BindView(R.id.myCoordinatorLayout)
    CoordinatorLayout myCoordinatorLayout;
    @BindView(R.id.searchBtn)
    Button searchBtn;
    @BindView(R.id.fab)
    FloatingActionButton   fab;
    TextView nickName;
    ImageView headImage;
    //-------------------------------
    private boolean isDrawer=false;
    private List<Fragment> myFragments;
    private UserModalImpl userModal;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        nickName=(TextView)navigationView.getHeaderView(0).findViewById(R.id.nickName);
        headImage=(ImageView)navigationView.getHeaderView(0).findViewById(R.id.headimg);
        init();
        addTopText();
    }
    private void init() {
        setSupportActionBar(toolbar);//设置标题栏
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.menu);//设置toobal左侧图标
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDrawerLayout.openDrawer(GravityCompat.START);//打开侧滑栏
            }
        });
        myDrawerLayout.setDrawerListener(this);//监听侧滑菜单
        myCoordinatorLayout.setOnTouchListener(this);//监听myCoordinatorLayout触摸事件
        // 判断版本号是否大于5.1 设置虚拟键盘菜单背景颜色
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
        }
        myFragments=new ArrayList<Fragment>();
        myFragments.add(new MyFragment1());
        myFragments.add(new MyFragment2());
        myFragments.add(new MyFragment3());
        myFragments.add(new MyFragment4());
        myViewPage.setAdapter(new HomeViewAdapter(getSupportFragmentManager(),myFragments));
        myViewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (TextView curText:texts) {
                    curText.setTextSize(16);
                    curText.setTextColor(getResources().getColor(R.color.whitehalf));
                }
                texts[position].setTextSize(20);
                texts[position].setTextColor(getResources().getColor(R.color.white));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        userModal=new UserModalImpl();
        Map<String,Object> params=new HashMap();
        Intent mIntent=getIntent();
        params.put("id",mIntent.getStringExtra("id"));
        params.put("loginId",mIntent.getStringExtra("loginId"));
        params.put("token",mIntent.getStringExtra("token"));
        userModal.getUserInfo(params,Home.this);
        searchBtn.setOnClickListener(this);
        fab.setOnClickListener(this);
        headImage.setOnClickListener(this);
    }
     TextView [] texts;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addTopText() {
       String [] topTitles={
         "说", "学","练","用"
       };
        texts=new TextView[topTitles.length];
        Toolbar.LayoutParams lay=new Toolbar.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT,Toolbar.LayoutParams.WRAP_CONTENT,1);
        LinearLayout.LayoutParams textLay=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,1);
        LinearLayout mLinearLayout=new LinearLayout(this);
        mLinearLayout.setLayoutParams(lay);
        toolbar.addView(mLinearLayout);
        for (int i=0;i<topTitles.length;i++) {
            TextView mText=new TextView(this);
            mText.setText(topTitles[i]);
            mText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            mText.setLayoutParams(textLay);
            mText.setTextSize(16);
            texts[i]=mText;
            mText.setTextColor(getResources().getColor(R.color.whitehalf));
           if(i==0){
               mText.setTextSize(20);
               mText.setTextColor(getResources().getColor(R.color.white));
           }
            mLinearLayout.addView(mText);
            final int finalI = i;
            mText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView mtext=(TextView)view;
                    for (TextView curText:texts) {
                        curText.setTextSize(16);
                        curText.setTextColor(getResources().getColor(R.color.whitehalf));
                    }
                    mtext.setTextSize(20);
                    mtext.setTextColor(getResources().getColor(R.color.white));
                    myViewPage.setCurrentItem(finalI);
                }
            });
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homemenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //按钮点击事件
        return super.onOptionsItemSelected(item);
    }
    @SuppressLint("RestrictedApi")
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }

    @Override
    public void onClick(View view) {
        Intent mintent;
           switch (view.getId()){
               case R.id.fab:
                   break;
               case R.id.searchBtn:
                    mintent=new Intent(Home.this,Search.class);
                   startActivity(mintent);
                   break;
               case R.id.headimg:
                   IntentEnterUtil.showIntent(Home.this,Mine.class);
                   break;
           }
    }
  //---------------------------------监听侧滑事件-------------------------------
    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        isDrawer=true;
        //得到屏幕宽高
        WindowManager mWindowManager=(WindowManager)getSystemService(Context.WINDOW_SERVICE);
        Display display=mWindowManager.getDefaultDisplay();
        Log.e("--------->",""+navigationView.getRight());
        myCoordinatorLayout.layout(navigationView.getRight(),0,
                navigationView.getRight()+display.getWidth(),display.getHeight());
    }
    @Override
    public void onDrawerOpened(View drawerView) {
    }
    @Override
    public void onDrawerClosed(View drawerView) {

    }
    @Override
    public void onDrawerStateChanged(int newState) {

    }
    @Override
    public void onBackPressed() {
        //按返回时左边侧滑是否开启
        if(myDrawerLayout.isDrawerOpen(GravityCompat.START)){
            myDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean eventBool=false;
        switch (view.getId()){
            case R.id.myCoordinatorLayout:
                if(isDrawer){
                    eventBool=navigationView.dispatchTouchEvent(motionEvent);
                }
                break;
        }
        return eventBool;
    }
    @Override
    public void onSuccess(HomeDataUser homeDataUser) {
            if(homeDataUser.getCode()== ConstData.CODE_SUCCESS){
                UserInfo userInfo=homeDataUser.getData().get("user");
                nickName.setText(userInfo.getUname());
                GlideApp.with(navigationView.getHeaderView(0)).load(userInfo.getUserimg())
                        .placeholder(R.mipmap.openmind)
                        .error(R.mipmap.openmind)
                        .fallback(R.mipmap.openmind)
                        .into(headImage);

            }else if (homeDataUser.getCode()== ConstData.CODE_TIMEOVER){
                 Intent intent=new Intent(Home.this,Login.class);
                 startActivity(intent);
                 finish();
            }
    }
    @Override
    public void onFailed(String msg) {
        Log.e("----------<<<",msg);
    }
}
