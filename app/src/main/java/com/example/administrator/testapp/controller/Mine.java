package com.example.administrator.testapp.controller;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.debug.hv.ViewServer;
import com.example.administrator.testapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Mine extends AppCompatActivity {
   @BindView(R.id.mineBar)
    Toolbar mineBar;

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
    private void init() {
        ViewServer.get(this).addWindow(this);
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
