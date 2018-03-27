package com.app.zilla.jinrishi.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.zilla.jinrishi.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {
    private View view;
    private ImageView blurImageView;
    private ImageView avatarImageView;
    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            //初始化view
            view = inflater.inflate(R.layout.fragment_my, container, false);
            blurImageView = (ImageView) view.findViewById(R.id.iv_blur);
            avatarImageView = (ImageView) view.findViewById(R.id.iv_avatar);

            Glide.with(this).load(R.drawable.head)
                    .bitmapTransform(new BlurTransformation(getContext(), 25), new CenterCrop(getContext()))
                    .into(blurImageView);

            Glide.with(this).load(R.drawable.head)
                    .bitmapTransform(new CropCircleTransformation(getContext()))
                    .into(avatarImageView);
        }
        return view;
    }
}
