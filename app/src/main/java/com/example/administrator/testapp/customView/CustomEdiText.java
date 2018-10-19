package com.example.administrator.testapp.customView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import android.widget.EditText;

import com.example.administrator.testapp.R;

/**
 * Created by Administrator on 2018/10/5.
 *  每个功能点  有它的实现思路
 *  做一个清除功能的输入框重点有以下几点，要用到的api 你所要熟悉的知识
 *   1.两个监听器 OnFocusChangeListener(焦点变化)  TextWatcher(输入监听)
 *   2.TextView---Editext  getCompoundDrawables()设置TextView的四周的图片（Drawable）
 *   3.手势识别 模拟单击事件  onTouchEvent
 */
@SuppressLint("AppCompatCustomView")
public class CustomEdiText extends EditText implements View.OnFocusChangeListener,TextWatcher{
    //删除按钮的图片
    private Drawable mClearDrawable;
    //控件是否有焦点
    private  boolean hasFoucs=false;
    //清除监听
    OnClearListener onClearListener;

    public CustomEdiText(Context context) {
        super(context);
    }
    public CustomEdiText(Context context, AttributeSet attrs) {
        //调用父类的构造方法，不然的话很多属性不能再xml定义
       this(context,attrs,android.R.attr.editTextStyle);
    }

    public CustomEdiText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();//初始化
    }
    private void init() {
        /*
           getCompoundDrawables()[0];可以获取textView的四周的图片
           这里获取的EditText的DrawableRight
         */
        mClearDrawable=getCompoundDrawables()[2];
        if(mClearDrawable==null){
            mClearDrawable=getResources().getDrawable(R.drawable.clear_selector);
        }
        /*
           getIntrinsicWidth mClearDrawable获取  固有的宽度
           getIntrinsicHeight mClearDrawable获取  固有的高度
         */
        mClearDrawable.setBounds(0,0,mClearDrawable.getIntrinsicWidth(),mClearDrawable.
        getIntrinsicHeight());
        setClearIconVisible(false);//默认隐藏
        setOnFocusChangeListener(this);//监听焦点改变
        addTextChangedListener(this);//设置输入框输入改变监听

    }
    /*
        手势
        不能直接给Editext设置点击事件，
        记住按下的位置来模拟点击事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_UP){//手指抬起时
            if(getCompoundDrawables()[2]!=null){
                //这里要解释一下 Editext
                boolean touchable=event.getX()>(getWidth()-getTotalPaddingRight()-getTotalPaddingLeft());
                if(touchable){
                   this.setText("");
                   if(null!=onClearListener){
                       onClearListener.onClear();
                   }
                }
            }
        }
        return super.onTouchEvent(event);
    }
    /*
           设置清除图标的显示与隐藏
           setCompoundDrawables为EditText绘制上去
         */
    public void setClearIconVisible(boolean visible) {
        Drawable left= getCompoundDrawables()[0];
        Drawable top=getCompoundDrawables()[1];
        Drawable right=visible?mClearDrawable:null;

        Drawable bottom=getCompoundDrawables()[3];
        //对应左上右下
        setCompoundDrawables(left,top,right,bottom);
    }

    public void setOnClearListener(OnClearListener onClearListener) {
        this.onClearListener = onClearListener;
    }
    /*
       焦点变化
     */
    @Override
    public void onFocusChange(View view, boolean b) {
          //当Editext焦点改变的时候，判断清除图标的显示和隐藏
          this.hasFoucs=b;
          if(b){
             setClearIconVisible(getText().length()>0);//获得焦点的时候
          }else{
              setClearIconVisible(false);//失去焦点的时候
          }
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }
    /*
     * 当输入框里面内容发生变化的时候回调的方法
     */
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               if(hasFoucs){//已经获得焦点
                   setClearIconVisible(charSequence.length()>0);
               }
    }
    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (null == text || text.toString()==null || text.toString().length()==0)
           return;
       this.setSelection(text.length());//全选
    }
    public interface OnClearListener{
        void onClear();
   }
}
