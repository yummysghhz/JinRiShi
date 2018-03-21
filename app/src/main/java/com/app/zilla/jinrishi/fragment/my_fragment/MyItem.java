package com.app.zilla.jinrishi.fragment.my_fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.zilla.jinrishi.R;

public class MyItem extends LinearLayout {
    private ImageView imageView;//item的图标
    private TextView textView;//item的文字
    private ImageView bottomview;
    private boolean isbottom=true;//是否显示底部的下划线
    public MyItem(Context context) {
        this(context,null);
    }

    public MyItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public MyItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(getContext()).inflate(R.layout.my_item_view,this);
       /* LayoutInflater mInflater = LayoutInflater.from(context);
        View myView = mInflater.inflate(R.layout.MyItem, null);
        addView(myView);*/
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.my_item_view);
        initTypedArray(ta);
        initview();
    }

    private void initTypedArray(TypedArray ta) {
        isbottom=ta.getBoolean(R.styleable.my_item_view_show_bottomline,true);
        bottomview=findViewById(R.id.item_bottom);
        imageView=findViewById(R.id.item_img);
        textView=findViewById(R.id.item_text);
        textView.setText(ta.getString(R.styleable.my_item_view_show_text));
        imageView.setBackgroundResource(ta.getResourceId(R.styleable.my_item_view_show_leftimg,R.drawable.setting));

        ta.recycle();
    }

    private void initview() {
        if(isbottom){
            bottomview.setVisibility(View.VISIBLE);
        }else{
            bottomview.setVisibility(View.GONE);
        }
    }

}