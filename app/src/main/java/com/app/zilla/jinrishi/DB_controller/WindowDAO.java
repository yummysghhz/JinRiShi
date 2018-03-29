package com.app.zilla.jinrishi.DB_controller;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.app.zilla.jinrishi.BmobObj.Campus;
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
    public static void getCanteenWindow(final Handler handler, Canteen canteen, final int groupPosition) {
        BmobQuery<Window> query = new BmobQuery<>();
        query.addWhereEqualTo("canteenIn",canteen);
        query.include("canteenIn");
        query.findObjects(new FindListener<Window>() {
            @Override
            public void done(List<Window> list, BmobException e) {
                if (e == null) {
                    Message message=handler.obtainMessage();
                    message.what=1;
                    message.arg1=groupPosition;
                    message.obj=list;
                    handler.sendMessage(message);
                    list.toString();
                } else {
                    //Toast.makeText(mContext,"您的网络走丢了= =",Toast.LENGTH_SHORT);
                    Log.e("bmob", "QUERY FAILED" + e);
                }
            }
        });
    }
}
