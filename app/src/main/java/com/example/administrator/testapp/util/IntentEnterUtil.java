package com.example.administrator.testapp.util;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Administrator on 2018/10/17.
 */

public class IntentEnterUtil {
    /**
     * 页面之间跳转
     */
    public static void showIntent(Activity context,Class<?> mclass,String[] keys, String[] values){
        Intent intent=new Intent(context,mclass);
        if(values!=null&&values.length>0){
            for(int i=0;i<values.length;i++){
               if(!StringUtil.isNoValue(keys[i])&&!StringUtil.isNoValue(values[i])){
                   intent.putExtra(keys[i],values[i]);
               }
            }
        }
        context.startActivity(intent);
    }
    public static void showIntent(Activity context,Class<?> mclass){
         showIntent(context, mclass,null,null);
    }


}
