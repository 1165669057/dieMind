package com.example.administrator.testapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.testapp.R;
import com.example.administrator.testapp.controller.Mine;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/10/21.
 */

public class MineAdapter extends BaseAdapter {
    Mine context;
    int itemHeight;
    List<HashMap<String,Object>> listData;
    public MineAdapter(Mine context,  List<HashMap<String,Object>> listData){
        this.context=context;
        this.listData=listData;
    }
    public int getHeight(){
         return itemHeight;
    }
    @Override
    public int getCount() {
        return listData.size();
    }
    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
               view= context.getLayoutInflater().inflate(R.layout.mine_listlayout,null);
               viewHolder=new ViewHolder(view);
               view.setTag(viewHolder);

        }else{
            viewHolder=(ViewHolder)view.getTag();
        }
        return view;
    }
     class ViewHolder{
        @BindView(R.id.leftimg)
        ImageView  mimageView;
        @BindView(R.id.mytitle)
        TextView mTitleText;
        @BindView(R.id.content)
        TextView mContentText;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
