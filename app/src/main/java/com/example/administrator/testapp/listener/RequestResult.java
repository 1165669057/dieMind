package com.example.administrator.testapp.listener;

/**
 * Created by Administrator on 2018/9/13.
 */

public interface RequestResult<T>{
   public  void onSuccess(T t);
   public void onFailed(String msg);
}
