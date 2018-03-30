package com.app.zilla.jinrishi.DB_controller;

import android.os.Message;

import com.app.zilla.jinrishi.BmobObj.JrsUser;
import com.app.zilla.jinrishi.BmobObj.UserFlavor;
import com.app.zilla.jinrishi.activity.MyImageActivity;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by 74434 on 2018/3/9.
 */

public class UserInfoDAO {
    //User可用
    //修改个人信息：username password profile_img 手机号 邮箱
    //这个不急233333

    public static void findMyFlavorPref(final MyImageActivity.MyHandler handler){
        JrsUser currentUser=JrsUser.getCurrentUser(JrsUser.class);
        BmobQuery<UserFlavor> query=new BmobQuery<>();
        query.getObject(currentUser.getFlavor_pref().getObjectId(), new QueryListener<UserFlavor>() {
            @Override
            public void done(UserFlavor userFlavor, BmobException e) {
                if(e==null){
                    userFlavor.toString();
                    Message message=handler.obtainMessage();
                    message.obj=userFlavor;
                    handler.sendMessage(message);
                }else{
                    e.printStackTrace();
                }
            }
        });
    }
}
