package com.example.administrator.testapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.testapp.R;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/9/13.
 */

public class MyFragment4 extends Fragment{
    View mview;
    @BindView(R.id.myfbtn4)
    Button  myfbtn4;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // ButterKnife.bind(getActivity());
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mview == null) {
            mview=inflater.inflate(R.layout.fr_layout4,container,false);
        }
        ViewGroup view = (ViewGroup) mview.getParent();
        if (view != null) {
            view.removeView(mview);
        }
        return mview;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
