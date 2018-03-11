package com.app.zilla.jinrishi.DB_controller;

import android.widget.Toast;

import com.app.zilla.jinrishi.BmobObj.JrsUser;
import com.app.zilla.jinrishi.activity.RegisterActivity;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 74434 on 2018/3/9.
 */

public class UserManageDAO {
    public static void registerUser(String username,String password,String school){
        final JrsUser user = new JrsUser();
        user.setUsername(username);
        user.setPassword(password);
        user.signUp(new SaveListener<JrsUser>() {
            @Override
            public void done(JrsUser jrsUser, BmobException e) {
                if(e==null){
                    Toast.makeText(RegisterActivity.mContext,"注册成功~",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.mContext,"注册失败= =",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
