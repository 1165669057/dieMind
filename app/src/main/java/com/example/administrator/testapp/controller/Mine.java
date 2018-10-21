package com.example.administrator.testapp.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.debug.hv.ViewServer;
import com.example.administrator.testapp.R;
import com.example.administrator.testapp.adapter.MineAdapter;
import com.example.administrator.testapp.application.GlideApp;
import com.example.administrator.testapp.util.DisplayUtil;
import com.example.administrator.testapp.util.GlideAppUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Mine extends AppCompatActivity implements View.OnTouchListener{
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
   @BindView(R.id.myCoordinatorLayout)
    CoordinatorLayout myCoordinatorLayout;
   @BindView(R.id.mineListView)
    ListView mineListView;
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
                        if( motionEvent!=null){
                           // eventBool=myNestedScrollView.onInterceptTouchEvent(motionEvent);
                        }

                    }else{
                        headTitle.setText("");
                    }
                }
            });
        }
        List<HashMap<String,Object>> mlist =new ArrayList<>();
        for (int i=0;i<30;i++){
             mlist.add(new HashMap<String, Object>());
        }
       MineAdapter mineAdapter=new MineAdapter(this,mlist);
        mineListView.setAdapter(mineAdapter);
        setListViewHeight(mineListView);
        mineAdapter.notifyDataSetChanged();
        Log.e("aaaaaaaaaaaaaaaa", mineAdapter.getHeight()+"");
        myNestedScrollView.setOnTouchListener(this);
        mineListView.setOnTouchListener(this);
        myCoordinatorLayout.setOnTouchListener(this);

    }
    public void setListViewHeight(ListView listView) {
        //获取listView的adapter
        MineAdapter listAdapter = (MineAdapter) listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        //listAdapter.getCount()返回数据项的数目
        for (int i = 0,len = listAdapter.getCount(); i < len; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() *  (listAdapter .getCount() - 1));
        listView.setLayoutParams(params);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
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
    boolean eventBool=false;
    MotionEvent motionEvent;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
         //eventBool=false;
        switch (view.getId()){
            case R.id.mineListView :
                myNestedScrollView.requestDisallowInterceptTouchEvent(true);
                break;
        }
        return eventBool;
    }
}
