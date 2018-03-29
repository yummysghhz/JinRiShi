package com.app.zilla.jinrishi.DB_controller;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.accessibility.CaptioningManager;
import android.widget.Toast;

import com.app.zilla.jinrishi.BmobObj.Campus;
import com.app.zilla.jinrishi.BmobObj.Canteen;
import com.app.zilla.jinrishi.BmobObj.JrsUser;
import com.app.zilla.jinrishi.activity.RegisterActivity;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class CanteenDAO {
    //查找：某校所有食堂
    public static void getCampusCanteen(final Handler handler) {
        BmobQuery<Canteen> query=new BmobQuery<>();
        JrsUser currentUser=JrsUser.getCurrentUser(JrsUser.class);
        Campus campus=currentUser.getSchool();
        query.addWhereEqualTo("campusIn",campus);
        //query.include("campusIn");
        query.findObjects(
                new FindListener<Canteen>() {
            @Override
            public void done(List<Canteen> list, BmobException e) {
                if(e==null){
                    Message message = handler.obtainMessage();
                    //以消息为载体
                    message.what=2;
                    message.obj = list;

                    //这地方我打断点进不来怎么肥四。。。之前是可以取到值的啊


                    //向handler发送消息
                    handler.sendMessage(message);
                    Log.d("bmob",list.toString());
                }else {
                    e.printStackTrace();
                }
            }
        });
    }

}
