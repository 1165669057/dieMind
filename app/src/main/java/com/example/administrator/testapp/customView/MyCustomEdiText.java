package com.example.administrator.testapp.customView;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.administrator.testapp.R;

/**
 * Created by Administrator on 2018/10/5.
 */

public class MyCustomEdiText extends LinearLayout{
    public MyCustomEdiText(Context context) {
        super(context);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public MyCustomEdiText(Context context, @Nullable AttributeSet attrs) {
       // super(context, attrs);
        this(context,attrs,android.R.attr.editTextStyle);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public MyCustomEdiText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(getContext(),attrs,defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void init(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        CustomEdiText customEdiText=new CustomEdiText(context,attrs,defStyleAttr);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT,1.0f);
        params.gravity= Gravity.CENTER_VERTICAL;
        customEdiText.setLayoutParams(params);
        //--------------
        customEdiText.setBackground(getResources().getDrawable(R.drawable.border));
        customEdiText.setHintTextColor(getResources().getColor(R.color.grey_deep));
        customEdiText.setHint("input your name");
        //--------------
        this.addView(customEdiText);
        Button btn=new Button(context);
        btn.setText("fffffffff");
        this.addView(btn);
    }
}
