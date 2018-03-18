package com.app.zilla.jinrishi.DB_controller;

import android.os.Handler;
import android.os.Message;
import android.text.style.MaskFilterSpan;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.app.zilla.jinrishi.BmobObj.Campus;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 74434 on 2018/3/9.
 */

public class CampusDAO {
    public static void getAllCampus(final Handler handler) {
        BmobQuery<Campus> query = new BmobQuery<Campus>();
        query.findObjects(new FindListener<Campus>() {
            @Override
            public void done(List<Campus> list, BmobException e) {
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
