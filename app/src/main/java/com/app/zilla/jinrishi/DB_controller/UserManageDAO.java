package com.app.zilla.jinrishi.DB_controller;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.app.zilla.jinrishi.BmobObj.Campus;
import com.app.zilla.jinrishi.BmobObj.JrsUser;


import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class UserManageDAO {
    //用户注册
    public static void registerUser(String username, String password, Campus school, final Context mContext){
        final JrsUser user = new JrsUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setSchool(school);
        user.signUp(new SaveListener<JrsUser>() {
            @Override
            public void done(JrsUser jrsUser, BmobException e) {
                if(e==null){
                    Toast.makeText(mContext,"注册成功~",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext,"注册失败= =",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }
}
