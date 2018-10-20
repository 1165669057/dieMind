package com.example.administrator.testapp.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.debug.hv.ViewServer;
import com.example.administrator.testapp.R;
import com.example.administrator.testapp.application.GlideApp;
import com.example.administrator.testapp.util.DisplayUtil;
import com.example.administrator.testapp.util.GlideAppUtil;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Mine extends AppCompatActivity {
   @BindView(R.id.mineBar)
    Toolbar mineBar;
   @BindView(R.id.headimg)
    ImageView headimg;
   @BindView(R.id.collapsingToolbarlayout)
    CollapsingToolbarLayout collapsingToolbarlayout;
   @BindView(R.id.headTitle)
   TextView headTitle;
   @BindView(R.id.myNestedScrollView)
    NestedScrollView myNestedScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ButterKnife.bind(this);
        setSupportActionBar(mineBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mineBar.setNavigationIcon(R.mipmap.back_arrow);
        init();
        mineBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    Context context;
    private void init() {
        ViewServer.get(this).addWindow(this);
        Intent intent=getIntent();
        context=this;
        String imgurl=intent.getStringExtra("userimg");
        /*GlideApp.with(this).load(imgurl)
                .transition(withCrossFade())
                .placeholder(R.mipmap.openmind)
                .error(R.mipmap.openmind)
                .fallback(R.mipmap.openmind).into(headimg);*/
        GlideAppUtil.loadCircle(context,headimg,imgurl,R.mipmap.openmind,R.mipmap.openmind);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            myNestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    if(DisplayUtil.px2dip(context,i1)>=(180)){
                        headTitle.setText("我的资料");
                    }else{
                        headTitle.setText("");
                    }
                }
            });
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
    }
}
