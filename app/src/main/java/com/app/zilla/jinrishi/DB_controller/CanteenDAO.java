package com.app.zilla.jinrishi.DB_controller;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
        BmobQuery<Canteen> query = new BmobQuery<>();

        JrsUser currentUser=JrsUser.getCurrentUser(JrsUser.class);

        //System.out.println(currentUser.getSchool().getObjectId());

        query.addWhereEqualTo("campusIn",currentUser.getSchool().getObjectId());

        query.findObjects(new FindListener<Canteen>() {
            @Override
            public void done(List<Canteen> list, BmobException e) {
                if (e == null) {
                    Message message = handler.obtainMessage();
                    //以消息为载体
                    message.obj = list;//这里的list就是查询出list
                    //向handler发送消息
                    handler.sendMessage(message);

                } else {
                    Log.e("bmob", "" + e);
                }
            }
        });
    }
}
