package com.app.zilla.jinrishi.DB_controller;

import android.util.Log;

import com.app.zilla.jinrishi.BmobObj.Dish;
import com.app.zilla.jinrishi.BmobObj.Window;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 74434 on 2018/3/9.
 */

public class DishDAO {
    //增删改查
    // 增删改直接在bmob图形化界面做，暂不写
    //主要写 findDishInWindow(window）
    //查找 用户收藏的菜：User表的likes（类型：List<Dish>） findDishByID？？？
    public static void getDishInWindow(){
        BmobQuery<Dish> query=new BmobQuery<>();
        Window window=new Window();
        String objectID="mNBacccl";
        window.setObjectId(objectID);
        query.addWhereEqualTo("windowIn",window);
        query.include("flavor_description");
        query.findObjects(new FindListener<Dish>() {
            @Override
            public void done(List<Dish> list, BmobException e) {
                if (e == null) {
                    list.toString();
                } else {
                    //Toast.makeText(mContext,"您的网络走丢了= =",Toast.LENGTH_SHORT);
                    Log.e("bmob", "QUERY FAILED" + e);
                }
            }
        });
    }

    public static void getDishByID(List<String> likelist){

    }
}
