package com.app.zilla.jinrishi.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.zilla.jinrishi.BmobObj.UserFlavor;
import com.app.zilla.jinrishi.DB_controller.UserInfoDAO;
import com.app.zilla.jinrishi.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rorbin.q.radarview.RadarData;
import rorbin.q.radarview.RadarView;

public class MyImageActivity extends AppCompatActivity {
/*
吃货形象
*/
    private RadarView radarView;
    private MyHandler handler=new MyHandler(this);
    private UserFlavor myFlavor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Bmob.initialize(this, AppInfo.APP_KEY);
        setContentView(R.layout.activity_my_image);
        radarView=findViewById(R.id.radarView);
        UserInfoDAO.findMyFlavorPref(handler);
        List<Integer> layerColor = new ArrayList<>();
        Collections.addAll(layerColor, 0x3300bcd4, 0x3303a9f4, 0x335677fc, 0x333f51b5, 0x33673ab7);
        radarView.setLayerColor(layerColor);

        List<String> vertexText = new ArrayList<>();
        Collections.addAll(vertexText, "酸", "甜", "辣", "咸", "猎奇", "家常");
        radarView.setVertexText(vertexText);

        List<Integer> res = new ArrayList<>();
        Collections.addAll(res, R.drawable.sour, R.drawable.sweet, R.drawable.spicy, R.drawable.salty,
                R.drawable.surprise, R.drawable.homely);
        radarView.setVertexIconResid(res);
    }




    public static class MyHandler extends Handler{
        WeakReference<MyImageActivity> mActivity;
        MyHandler(MyImageActivity myImageActivity){
            mActivity=new WeakReference(myImageActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            final MyImageActivity thisActivity=mActivity.get();
            thisActivity.myFlavor=(UserFlavor) msg.obj;
            UserFlavor flavor=thisActivity.myFlavor;
            List<Float> values = new ArrayList<>();
            Collections.addAll(values,flavor.getSour(),flavor.getSweet(),flavor.getSpicy(),flavor.getSalty(),flavor.getSurprise(),flavor.getHomely());
            RadarData data = new RadarData(values);
            thisActivity.radarView.addData(data);
        }
    }
}
