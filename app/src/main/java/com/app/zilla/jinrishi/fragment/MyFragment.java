package com.app.zilla.jinrishi.fragment;

import android.content.Intent;
import android.icu.util.ValueIterator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.zilla.jinrishi.BmobObj.JrsUser;
import com.app.zilla.jinrishi.R;
import com.app.zilla.jinrishi.activity.MyHistoryActivity;
import com.app.zilla.jinrishi.activity.MyImageActivity;
import com.app.zilla.jinrishi.activity.MyLikesActivity;
import com.app.zilla.jinrishi.fragment.my_fragment.MyItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class MyFragment extends Fragment {
    private View view;
    private ImageView blurImageView;
    private ImageView iv_my_profile;
    private MyItem my_likes_btn;
    private MyItem my_img_btn;
    private MyItem my_history_btn;
    private TextView username_tv;
    private TextView user_level_tv;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            //初始化view
            view = inflater.inflate(R.layout.fragment_my, container, false);
            username_tv= view.findViewById(R.id.tv_user_name);
            user_level_tv=view.findViewById(R.id.tv_user_level);
            blurImageView = view.findViewById(R.id.iv_blur);
            iv_my_profile =view.findViewById(R.id.iv_my_profile);

            my_history_btn=view.findViewById(R.id.my_history);
            my_img_btn=view.findViewById(R.id.my_img);
            my_likes_btn=view.findViewById(R.id.my_likes);

            JrsUser currentUser=JrsUser.getCurrentUser(JrsUser.class);
            username_tv.setText(currentUser.getUsername());
            user_level_tv.setText(currentUser.getLevel());

            //File需要异步加载
            File my_profile=currentUser.getProfile_img();

            Glide.with(this).load(my_profile)
                    .bitmapTransform(new BlurTransformation(getContext(), 25), new CenterCrop(getContext()))
                    .into(blurImageView);

            Glide.with(this).load(my_profile)
                    .bitmapTransform(new CropCircleTransformation(getContext()))
                    .into(iv_my_profile);

            my_img_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(MyFragment.this.getContext(), MyImageActivity.class);
                    startActivity(intent);
                }
            });

            my_history_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(MyFragment.this.getContext(), MyHistoryActivity.class);
                    startActivity(intent);
                }
            });

            my_likes_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(MyFragment.this.getContext(), MyLikesActivity.class);
                    startActivity(intent);
                }
            });
        }
        return view;
    }
}
