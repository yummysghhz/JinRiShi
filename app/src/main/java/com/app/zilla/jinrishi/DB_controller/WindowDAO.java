package com.app.zilla.jinrishi.DB_controller;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.app.zilla.jinrishi.BmobObj.Canteen;
import com.app.zilla.jinrishi.BmobObj.JrsUser;
import com.app.zilla.jinrishi.BmobObj.Window;
import com.app.zilla.jinrishi.activity.RegisterActivity;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class WindowDAO {
    //查找：某食堂所有窗口
    public static void getWindowWindow(final Handler handler) {
        BmobQuery<Window> query = new BmobQuery<>();

        JrsUser currentUser=JrsUser.getCurrentUser(JrsUser.class);

        System.out.println(currentUser.getSchool().getObjectId());

        query.addWhereEqualTo("canteenIn",currentUser.getSchool().getObjectId());

        query.findObjects(new FindListener<Window>() {
            @Override
            public void done(List<Window> list, BmobException e) {
                if (e == null) {
                    Message message = handler.obtainMessage();
                    //以消息为载体
                    message.obj = list;//这里的list就是查询出list
                    //向handler发送消息
                    handler.sendMessage(message);

                } else {
                    //Toast.makeText(mContext,"您的网络走丢了= =",Toast.LENGTH_SHORT);
                    Log.e("bmob", "" + e);
                }
            }
        });
    }
}
