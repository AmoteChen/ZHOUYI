package com.zhouyi.zhouyi.activity.mine;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhouyi.zhouyi.R;

/**
 * Created by ChenSiyuan on 2018/9/20.
 */

public class item_view extends LinearLayout {
    public static boolean LOGIN = false;
    private ImageView imageView;//item的图标
    private TextView textView;//item的文字
    private ImageView bottomview;
    private boolean isbootom=true;//是否显示底部的下划线
    private boolean islogin=true;
    public item_view(Context context) {
        this(context,null);
    }

    public item_view(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public item_view(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.item_view,this);
       /* LayoutInflater mInflater = LayoutInflater.from(context);
        View myView = mInflater.inflate(R.layout.item_view, null);
        addView(myView);*/
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.item_view);
        isbootom=ta.getBoolean(R.styleable.item_view_show_bottomline,true);
        islogin=ta.getBoolean(R.styleable.item_view_show_login,true);
        bottomview=findViewById(R.id.item_bottom);
        imageView=findViewById(R.id.item_img);
        textView=findViewById(R.id.item_text);

        textView.setText(ta.getString(R.styleable.item_view_show_text));
        imageView.setBackgroundResource(ta.getResourceId(R.styleable.item_view_show_leftimg, R.drawable.setting));

        ta.recycle();
        initview();
    }


    public static void setState(boolean state) {
        item_view.LOGIN = state;
    }

    private void initview() {
        if(LOGIN||islogin){
            this.setVisibility(View.VISIBLE);
        }else{
            this.setVisibility(View.GONE);
        }
    }


}
